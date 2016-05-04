package student_classes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class ExtractorThread extends Thread {

	private URL url;
	private MyQueue<URL> linkQueue, picQueue;
	private MySet<URL> beenThere, doneThat;

	public ExtractorThread(URL url, MyQueue<URL> linkQueue, MyQueue<URL> picQueue, MySet<URL> beenThere, MySet<URL> doneThat) {
		this.url = url;
		this.linkQueue = linkQueue;
		this.picQueue = picQueue;
		this.beenThere = beenThere;
		this.doneThat = doneThat;
	}

	public String getCurrentURL() {
		return url.toString();
	}

	private static Pattern LINK_PATTERN = Pattern.compile("href *= *\"([^\"]*)\"", Pattern.CASE_INSENSITIVE);
	private static Pattern IMAGE_PATTERN = Pattern.compile("<( )*(img|IMG)( )+([^<>])*(src|SRC)( )*=( )*\"([^\"]+)\"[^>]*>");

	private static Set<URL> extractLinks(Pattern toMatch, String s, URL currentURL, int group) {
		Matcher m = toMatch.matcher(s);
		Set<URL> links = new HashSet<URL>();
		while ( m != null && s!= null && m.find()) {
			String found = m.group(group);
			try {
				links.add(new URL(currentURL, found));
			} catch (MalformedURLException e) {
				// just ignore
			}
		}
		return links;
	}

	private static Set<URL> getLinks(String s, URL currentURL) {
		return extractLinks(LINK_PATTERN, s, currentURL, 1);
	}

	private static Set<URL> getPicURLs(String s, URL currentURL) {
		return extractLinks(IMAGE_PATTERN, s, currentURL, 8);
	}


	/**
	 * Only public method in ExtractorThread to implement. 
	 * Must provide concurrent facility to extract pictures and links from
	 * an initial http source using both java's http protocol functionality 
	 * and provided functionality thru UMD. 
	 */
	public void run() {
		// going to need private methods to extract links and images and 
		// add them to their respective structures. 
		try{

			// declare new buff reader with a http inputstream. 
			BufferedReader linkReader =  null;
			try{
				linkReader = new BufferedReader(new InputStreamReader(url.openStream()));
			}catch(IOException e){
				// if theres an error, its an HTTP 403 
				return;
			}


			String currentLine = "";
			// now read line by line, rather than a tokenizer bufferedReader_linkss have readline
			try{
				while((currentLine = linkReader.readLine())!=null){
					/**
					 * code below is to extract the links 
					 */
					Set<URL> linkSet = this.getLinks(currentLine, this.url);


					Iterator<URL>urlItr = linkSet.iterator();
					while(urlItr.hasNext()){
						URL tempURL = urlItr.next();
						String tempProtocol = tempURL.getProtocol();
						// technically it should be http 8080. Maybe use xpath and examine header?
						if(tempProtocol.equals("http") || tempProtocol.equals("file")){
							// check if we've been there
							if(!beenThere.contains(tempURL)){
								// we haven't but we can say we have now
								beenThere.add(tempURL);
								// visit this url
								linkQueue.enqueue(tempURL);
							}
						}
					}
				}
			}catch(Exception e){
				// no op.. but either way
			}
		}catch(Exception e){
			// no op 
			e.printStackTrace();
		}



		/**
		 * Code is to read the html line by line and calls getPicURLs
		 * to see if there are any img tags in the current line being read. 
		 * 
		 * If there are, a set is returned and if we haven't processed that unique
		 * image tag and source, add it to the picture queue that should be enqueued. 
		 * 
		 * I don't know how you all are doing this, but I'm pretty sure DOM contents
		 * can be inspected wite some of java's built in libraries.. if thats the case
		 * all you have to do it naviagate the dom structure to where <img></img> 
		 * tags are located within the document. 
		 */
		try{

			// declare new buff reader with a http inputstream.
			BufferedReader  picReader = null;
			try{
			picReader = new BufferedReader(new InputStreamReader(url.openStream()));
			}catch(IOException e ){
				return;
			}

			String currentLine = "";
			// now read line by line, rather than a tokenizer bufferedReader_linkss have readline
			while((currentLine = picReader.readLine())!=null){
				/**
				 * code below is to extract the links 
				 */
				Set<URL> picSet = getPicURLs(currentLine, this.url);


				Iterator<URL>picItr = picSet.iterator();
				while(picItr.hasNext()){
					URL tempPicUrl = picItr.next();
					if(!doneThat.contains(tempPicUrl)){
						doneThat.add(tempPicUrl);
						picQueue.enqueue(tempPicUrl);
					}
				}
			}
			picReader.close();
		}catch(Exception e){
			// no op 
			e.printStackTrace();
		}





	}


}
