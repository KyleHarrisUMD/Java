package student_classes;
import java.net.*;
import java.io.*;

public class Crawler {

	public static void main(String[] args) {

		MyQueue<URL> linkQueue = new MyQueue<URL>();
		MyQueue<URL> picQueue = new MyQueue<URL>();
		MySet<URL> beenThere = new MySet<URL>();
		MySet<URL> doneThat = new MySet<URL>();

		final int MAX_NUM_EXTRACTORS = 5;  // Change this to whatever you want

		ExtractorThread[] extractors = new ExtractorThread[MAX_NUM_EXTRACTORS];

		new SlideShowGUI(picQueue);
		new CrawlerGUI(linkQueue, picQueue, beenThere, doneThat, extractors);

		URL url = null; // because java

		while(true) {

			// moving sleep up here to see if execution changes 

			try{
				Thread.sleep(400);
			}catch(InterruptedException e){
				e.printStackTrace();
			}


			/* okay, loop for number of elements contained in the ExtratorThread array
			 * they're all instances of Thread so we can perform needed ops in a loop;
			 */
			for(int i = 0; i<extractors.length; i++){

				if(extractors[i]==null || !extractors[i].isAlive()){
					// okay, this thread is valid to deal with now.. 

					// perform synchronized block so no concurrent modification is made
					synchronized(linkQueue){
						// check if there are links to process
						while(linkQueue.size() == 0){
							// while there isn't 
							// have the linkQueue wait 
							try{
								linkQueue.wait();
							}catch(InterruptedException e){
								e.printStackTrace();
							}
						}
					}


					/*
					 * So there needs to be another loop and 
					 * a connection is not be made within it if
					 * the url is valid... should probably have extractors in synchronized block
					 */
					synchronized(extractors){


						while(true){
							// the url is going do be the linkQeueues deque obj
							url = linkQueue.dequeue();
							// try to make a new url connection
							URLConnection  cxn; 
							// only is the url isnt empty from dequeueing it
							if(url!=null)
								try{

									// could fail if server unaviable. 
									cxn = url.openConnection();
									cxn.connect();


									/* in reality, we only want the html, 
									 * not the mumbojumbo you get from wget
									 * 
									 * .. you might even be able to use xpath for this..
									 */
									String returnedFormat = cxn.getContentType();
									if(returnedFormat == null){
										return;
									}
									
									// check if its good to go (aka it can be parsed for a href = )
									if(returnedFormat.contains("text/html")){
										// technically js can contain img tags and link objects.. 
										// init new obj here
										// create a new thread with the gathered data and initilizze it in the array
										extractors[i] = new ExtractorThread(url, linkQueue, picQueue, beenThere, doneThat);
										//	tempExtractor.run(); // start it so its now alive.
										extractors[i].start(); // start not run.. lol cant run if not started.									

										// break out of loop; 
										break;


									}
									/* java net tools can always throw something.. ie 404, 403, can't resolve DNS .\
									 * sometimes a firewall can even block outgoing connetions from java if its not trusted.
									 * Ig it depends on the attempted protocol, in this case its just HTTP so its 
									 * probably just a url request {Port 80 generally}
									 */
								}catch(IOException e){ 
									//System.out.println(e.getStackTrace()); // curiosity.

								}
						}
						

					}



				}
			}





			}
		}
	}


