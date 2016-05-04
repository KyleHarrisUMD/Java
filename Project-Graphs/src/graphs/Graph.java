package graphs;

import java.util.*;

/**
 * Implements a graph. We use two maps: one map for adjacency properties 
 * (adjancencyMap) and one map (dataMap) to keep track of the data associated 
 * with a vertex. 
 * 
 * @author cmsc132
 * 
 * @param <E>
 */
public class Graph<E> {



	/* You must use the following maps in your implementation */
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;


	/**
	 * Generic, parameter free constructor to init private data members. 
	 */
	public Graph(){
		this.adjacencyMap = new HashMap<String, HashMap<String, Integer>>();
		this.dataMap = new HashMap<String, E>();

	}
	/*
	 * Methods below are those that are required by the javadoc and 
	 * project specs : 
	 * 
	 * void addDirectedEdge(java.lang.String startVertexName, java.lang.String endVertexName, int cost)
	 * 
	 * void addVertex (java.lang.string vertexName)
	 * 
	 * void doBreadthFirstSearch(java.lang.String startVertexName, Callback <E> callback)
	 * 
	 * void doDepthFirstSearch(java.lang.String startVertexName, Callback<E> callback)
	 * 
	 * int doDijkstras(java.lang.String startVertex, java.lang.String endVertex)
	 * 
	 * Java.util.Map getAdjacentVerticies(java.lang.String vertexName)
	 * 
	 * int getCost(java.lang.String startVertexName, java.lang.String endVertexName) 
	 * 
	 * E getData(java.lang.String vertex)
	 * 	gets the data at the target vertex. 

	 * java.util.Set getVerticies()
	 * 
	 * 	returns all veriticies in the current map
	 * 
	 * Strign toString
	 * 
	 */


	/**
	 * Adds a directed edge between two verticies. 
	 * 
	 * Since the graph is directed, it adds an edge with the orientation of the first one 
	 * oritented towards the second vertex - so in effect this is what is going on : 
	 *            
	 *            cost                                      cost 
	 *    v1 ------------------ > v2       not  v1 <----------------------->v2
	 *    
	 *    unless, v2 has an associated edge with v2 where the orientation is swapped, then its valid
	 *    but it will have a different cost associated with it. 
	 *    
	 *    
	 *    
	 * 
	 * @param startVertexName
	 * @param endVertexName
	 * @param cost
	 * 
	 * @throws java.lang.illegalArgumentExeption - if one of the two vertex names are invalid.
	 */
	public void addDirectedEdge(java.lang.String startVertexName, java.lang.String endVertexName, int cost){
		// check if the two vertex names are valid, if not throw an exception 
		if(dataMap.containsKey(startVertexName) && dataMap.containsKey(endVertexName)){
			/*
			 * Really what needs to happen is the adjacencyMap needs to be modifed such that 
			 * the value with the startVertex in the adjacencyMap needs to point to 
			 * a new element - a modifed hashmap with a value for the end vertex and the 'cost'
			 * or weight to get there. 
			 */

			// its going to be a one-liner so hopefully its not to cryptic to follow, but then agian - it is code.
			this.adjacencyMap.get(startVertexName).put(endVertexName, cost);

			/*the code above says - okay so you know that vertex that exists in the adjancencyMap? Okay - yea 
			 its got a reference to a hashmap - so add that hashmap with contains the edges that it has connections
			 to a new edge with the orientation from the start to the end with the specific cost.
			 */

		}else{
			// one of the two verticies does not exist
			throw new java.lang.IllegalArgumentException();
		}

	}


	/**
	 * Method adds an entry to the adjacency map. However, rather 
	 * than passing in a map with values, it will be empty and the 
	 * data map will contain the data for which you should be concerned about.
	 * 
	 * 
	 * @param vertexName - name of the vertex to be associated with E data
	 * @param E data - data to be associated with the vertex, 'vertexName' 
	 * 
	 * @throws java.lang.IllegalArgumentException if the vertex already exists in the map
	 */
	public void addVertex(java.lang.String vertexName, E data){
		if(!dataMap.containsKey(vertexName)){
			dataMap.put(vertexName, data);
			// in addition, put a new entry in the adjacecy map with a null mapping..  
			adjacencyMap.put(vertexName, new HashMap<String, Integer>());
		}else{
			// its already in the data map
			throw new java.lang.IllegalArgumentException();
		}
	}

	/**
	 * 
	 * Method returns the cost going from one vertex to another. 
	 * This function will only be invoked on the set of elements returned by
	 *       getAdjacentVerticies() - that way only edges that exist will be queried. 
	 * 
	 * @param vertexName
	 * @param endVertexName
	 * @return int cost, if it exists in the adjacencyMap
	 * 
	 * @throws java.lang.IllegalArguementException 
	 * 	    if any of the verticies are not in the adjacency map
	 */
	public int getCost(java.lang.String startVertexName, java.lang.String endVertexName){
		if(this.dataMap.containsKey(startVertexName)&& this.dataMap.containsKey(endVertexName)){

			if(this.adjacencyMap.get(startVertexName).containsKey(endVertexName)){
				return this.adjacencyMap.get(startVertexName).get(endVertexName);
			}else{
				/* 
				 * Documentation doesn't have a suggestion on what to do in this situation
				 *  - so i'll return the conventional value for an unsupported, but supported 
				 *  operation. 
				 *  [ Provided that we won't have negative weights because we're not implementing
				 *  Bellman Ford. ]
				 */
				return -1; 

			}

		}else{
			throw new java.lang.IllegalArgumentException();
		}
	}

	/**
	 * Method returns a set of all verticies in the map . 
	 * 
	 * @return a set of all string representaions of verticies
	 * in the dataMap.
	 */
	public java.util.Set <java.lang.String> getVertices(){
		return this.dataMap.keySet();
	}

	/**
	 * Method will return the adjacent verticies in the adjacencyMap. 
	 * @param vertexName
	 * @return  -  a map of adjacent verticies with associated costs
	 */
	public java.util.Map<java.lang.String, java.lang.Integer> getAdjacentVertices(java.lang.String vertexName){
		return this.adjacencyMap.get(vertexName); 
	}


	/**
	 * Method returns the data associated with the given element. 
	 * @return E data - if the vertex exists
	 * @throws java.lang.IllegalArgumentException if the vertex isn't in the graph
	 */
	public E getData(java.lang.String vertexName){
		if(this.dataMap.containsKey(vertexName)){
			return this.dataMap.get(vertexName);
		}else{
			throw new java.lang.IllegalArgumentException();
		}
	}

	/**
	 * Method performs a depth first search from the start vertex. 
	 * DFS uses a stack rather than BFS which uses a queue. 	 * 
	 * @param startVertex
	 * @param callback
	 */
	public void doDepthFirstSearch(java.lang.String startVertex, CallBack<E>callback){

		Stack<java.lang.String> vertexStack = new Stack<java.lang.String>();
		// use a set to keep track of the visitied verticies
		Set<java.lang.String> visitedSet = new HashSet<java.lang.String>();

		// push the start vertex on to the stack
		vertexStack.push(startVertex);
		// now loop while the vertex stack has elements
		while(!vertexStack.isEmpty()){
			String currentVertex = vertexStack.pop();
			// if its not in the visited set yet, add it 
			if(!visitedSet.contains(currentVertex)){

				// now process using callback // 
				visitedSet.add(currentVertex);
				callback.processVertex(currentVertex, this.dataMap.get(currentVertex));
				System.out.println(currentVertex);

			}
			// now get the adjacent elements and add them to the stack
			Iterator <String> adjacentElements = this.getAdjacentVertices(currentVertex).keySet().iterator();
			// right about here we have to put them in sorted order. 
			ArrayList  <String> listedElement = new ArrayList<String>();

			while(adjacentElements.hasNext()){
				listedElement.add(adjacentElements.next()); // add to a linkedlist unsorted.
			}
			String [] unsorted = new String [listedElement.size()];  // it in an array
			// reset adjacentElements to equal a new iterator
			adjacentElements = this.getAdjacentVertices(currentVertex).keySet().iterator();

			int i = 0; // index counter; // 
			while(adjacentElements.hasNext()){
				unsorted[i] = adjacentElements.next(); // add to unsorted array
				i++; // increment the index.
			}

			// make a new sorted array using a quicksort.
			String [] sorted = this.sortedOrder(unsorted, 0, unsorted.length-1);

			// clear contents of listed elements 
			listedElement.clear(); 

			for(int z =0; z<sorted.length; z++){
				// add the elements IN ORDER. 
				listedElement.add(sorted[z]); 
			}

			/* Now all of the elements are going to be in order 
			 * and in an Arraylist.. */
			// set iterator to the arraylist's iterator
			adjacentElements = listedElement.iterator();

			while(adjacentElements.hasNext()){
				String tempAdjacent = adjacentElements.next();
				if(!visitedSet.contains(tempAdjacent)){
					// if its not in the set, push in onto the stack 
					vertexStack.push(tempAdjacent);
					// and boom. 
				}
			}
		}
	}

/**
	 * Method performs a breadth first search from the start vertex. 
	 * BFS uses a queue rather than DFS which uses a stack . 	 * 
	 * @param startVertex
	 * @param callback
	 */
	public void doBreadthFirstSearch(java.lang.String startVertex, CallBack<E>callback){

		Queue<java.lang.String> vertexQueue = new LinkedList<java.lang.String>();
		// use a set to keep track of the visitied verticies
		Set<java.lang.String> visitedSet = new HashSet<java.lang.String>();

		// offer to the queue
		vertexQueue.offer(startVertex);
		// now loop while the ver has elements
		while(!vertexQueue.isEmpty()){
			String currentVertex = vertexQueue.poll();
			// if its not in the visited set yet, add it 
			if(!visitedSet.contains(currentVertex)){

				// now process using callback // 
				visitedSet.add(currentVertex);
				callback.processVertex(currentVertex, this.dataMap.get(currentVertex));

			}
			// now get the adjacent elements and add them to the stack
			Iterator <String> adjacentElements = this.getAdjacentVertices(currentVertex).keySet().iterator();
			// right about here we have to put them in sorted order. 
			ArrayList  <String> listedElement = new ArrayList<String>();

			while(adjacentElements.hasNext()){
				listedElement.add(adjacentElements.next()); // add to a linkedlist unsorted.
			}
			String [] unsorted = new String [listedElement.size()];  // it in an array
			// reset adjacentElements to equal a new iterator
			adjacentElements = this.getAdjacentVertices(currentVertex).keySet().iterator();

			int i = 0; // index counter; // 
			while(adjacentElements.hasNext()){
				unsorted[i] = adjacentElements.next(); // add to unsorted array
				i++; // increment the index.
			}

			// make a new sorted array using a quicksort.
			String [] sorted = this.sortedOrder(unsorted, 0, unsorted.length-1);

			// clear contents of listed elements 
			listedElement.clear(); 

			for(int z =0; z<sorted.length; z++){
				// add the elements IN ORDER. 
				listedElement.add(sorted[z]); 
			}

			/* Now all of the elements are going to be in order 
			 * and in an Arraylist.. */
			// set iterator to the arraylist's iterator
			adjacentElements = listedElement.iterator();

			while(adjacentElements.hasNext()){
				String tempAdjacent = adjacentElements.next();
				if(!visitedSet.contains(tempAdjacent)){
					// if its not in the set, push in onto the stack 
					vertexQueue.offer(tempAdjacent);
					// and boom. 
				}
			}
		}
	}


	/**
	 * Method returns string information about the graph. 
	 * 
	 * The string is to be in alphabetical order with 
	 * relation to the adjacent verticies. 
	 * 
	 * This is going to delegate to a private auxillary 
	 * method that is going to essentailly take a linkedlist
	 * in random order and sort it and that method will return a 
	 * string to be used in the string builder in this current method. 
	 * 
	 * 
	 * An example of a correct toString is : 
	 * 
	 *  Vertices: [A, B, C, D, M, ST]
	 * 	Edges:
	 *  Vertex(A)--->{C=2}
	 *  Vertex(B)--->{A=4, D=3}
	 *	Vertex(C)--->{D=5}
	 *	Vertex(D)--->{C=7}
	 *	Vertex(M)--->{}
	 *	Vertex(ST)--->{A=11, B=6}
	 *	TERPSPUBLICTESTS
	 *
	 */

	public java.lang.String toString(){

		/*
		 * The code segment below adds the verticies 
		 * to an array of stringa which will be sorted. 
		 * Ide rather invoke a sort on an array than a set.
		 */
		String [] unsortedArray = new String [this.getVertices().size()]; 
		Set <String> unsortedVerticies = this.getVertices();
		Iterator<String> vertexItr = unsortedVerticies.iterator();

		int i = 0;
		while(vertexItr.hasNext()){
			unsortedArray[i] = vertexItr.next();
			i++;
		}

		String [] sortedArray = sortedOrder(unsortedArray, 0, unsortedArray.length-1);
		StringBuilder sb = new StringBuilder();
		sb.append(constructStringFromArray(sortedArray)+"\n"+"Edges:"+"\n");

		/* since we've got the elements sorted alphabetically we can make another
		 * private method that will take the verticies and make a new string
		 * representing the adjacent element listings. 
		 */

		// now loop it and modify the string builder
		for(int x = 0; x<sortedArray.length; x++){
			// throw a new line in there every time.. 
			if(x<sortedArray.length-1){
				sb.append(this.vertexString(sortedArray[x])+"\n");
			}else{
				sb.append(this.vertexString(sortedArray[x]));
			}
		}

		return sb.toString();
	}

	/**
	 * 
	 * Method to return a string representation
	 * of the vertex and the associated edges with their
	 * weights. 
	 * 
	 * @return a string for use in the string builder denoting the vertex name
	 * in the toString method.
	 * 
	 * it will have to follow the following form 
	 * 
	 *  Vertex(A)--->{C=2}
	 *  Vertex(B)--->{A=4, D=3}
	 *	Vertex(C)--->{D=5}
	 *	Vertex(D)--->{C=7}
	 */

	private String vertexString(String vertexName){
		String vertexString = new String("Vertex("+vertexName+")--->{");

		/* since were using a hashmap for the adjacent elements we're just going
		 * get the values and append them to a string.. All you have to do
		 * is get the key set and then query the values based upon the current key.
		 */ 
		Map<String, Integer> tempMap = this.getAdjacentVertices(vertexName);
		// now we want to do this fun thing right here
		Set <String> tempKeys = tempMap.keySet();


		String [] unsorted = new String [tempKeys.size()];
		Iterator<String> adjElemItr = tempKeys.iterator();

		int i = 0;
		while(adjElemItr.hasNext()){
			unsorted[i] = adjElemItr.next();
			i++;
		}
		if(!tempKeys.isEmpty()){
			String [] sorted = sortedOrder(unsorted, 0, unsorted.length-1);

			// now loop it. 
			for(int x = 0; x<sorted.length; x++){
				String tempAdj = sorted[x];
				int tempWeight = this.getCost(vertexName, tempAdj);
				// this logical case decision will make sure the vertex stirng isn't messed up.
				if(x<sorted.length-1){
					vertexString+=tempAdj+"="+tempWeight+", ";
				}else{
					vertexString+=tempAdj+"="+tempWeight;
				}
			}
		}
		vertexString+="}";
		return vertexString;
	}

	private String constructStringFromArray(String [] sorted){

		String vertexString = "Vertices: [";
		// loop to construct the string 
		int length = sorted.length;
		for(int i = 0; i<length-1; i++){
			vertexString+=sorted[i]+", ";
		}
		// cap off the string .. 
		vertexString += sorted[length-1]+"]";
		return vertexString;
	}

	/**
	 * This cute little no so little method invokes a quick sort on
	 * an array which contains the string names of the verticies. 
	 * 
	 * It uses the String's compareTo which is possible because 
	 * java.lang.String implemementts comparable and it does it 
	 * such that it does in a way that is very convientient for me. 
	 * Or in other words - it does it alphabetically. 
	 * 
	 * If this doesn't work with default behavior because of the lexographic
	 * differences, String also has compareToIgnoreCase which will work
	 * as well (it might be safer to just use that right off the bat)
	 * 
	 * @param unsorted
	 * @param low
	 * @param high
	 * @return
	 */
	private String [] sortedOrder(String [] unsorted, int low, int high){

		if(unsorted == null || unsorted.length == 0){
			return null;
		}else if(low>=high){
			// were just going to return the array
			return unsorted;
		}else{

			// just going to do this to pick the pivot value..
			int middle = low + (high - low) / 2;

			// Okay this is where is going to get weird
			String pivot = unsorted[middle];

			int i  = low;
			int j  = high; 

			while(i <= j){

				// since its a string, we can use compareTo because
				// string implements comparable.
				while(unsorted[i].compareToIgnoreCase(pivot)<0){
					i++;
				}

				// same thing with this
				while(unsorted[j].compareToIgnoreCase(pivot)>0){
					j--;
				}

				/* now if i<=j 
				 * 
				 * this is where we wan't to swap the 
				 * values so its put into sorted order
				 */
				if(i<=j){
					String tempString = unsorted[i];
					unsorted[i] = unsorted[j];
					unsorted[j] = tempString;
					// now move the respective values now that a swap has been made
					i++;
					j--;
				}
			}
			// still have to call sorts on the sub parts 
			if(low < j){
				// using the values i & j makes sure it calls it on the subarrays.
				sortedOrder(unsorted, low,j);
			} 

			if(high > i){
				// same thing : upper subarray. 
				sortedOrder(unsorted, i, high);
			}

		}
		return unsorted; // because java
	}

	/**
	 * Computes the shortest path based upon Dijkstra's algorithm. 
	 * 
	 * 
	 * @param startVertex
	 * @param endVertex 
	 * 
	 * @param shortestPath - an arraylist of vertex names. If there is no shortest path,
	 * it will have an entry of "None"
	 * 
	 * @return - the cost of the shortest path. If there is no path, return -1.
	 * 
	 * @throws java.lang.IllegalArgumentException - if either of the verticies are not in the graph. 
	 */
	public int doDijkstras(java.lang.String startVertex, java.lang.String endVertex, java.util.ArrayList<java.lang.String> shortestPath){

		if(this.dataMap.containsKey(startVertex) && this.dataMap.containsKey(endVertex)){

			DijkstrasTable pathTable = new DijkstrasTable(this.getVertices(), this.adjacencyMap);
			pathTable.computeShortestPath(startVertex, endVertex);
			ArrayList<String> sp =  pathTable.getShortestPathList();
			for(int x =0; x<sp.size(); x++){
				shortestPath.add(sp.get(x)); // interesting .. 
				System.out.println(sp.get(x));
			}
			return pathTable.pathCost;

		}else{
			throw new java.lang.IllegalArgumentException();
		}

	}
	/**
	 * Private class DijkstrasTable to aid in computation
	 * of Dijkstra's shortest path.	
	 */
	private class DijkstrasTable{

		/**
		 * Okay, now lets do something fun. Make a map were the vertex is 
		 * the key and the value is going to be a priority queue of adjacent elements
		 * and those are going to be calculuated upon construction of this class
		 */

		private HashMap <java.lang.String, Heap<Entry>> vertexMap; 
		private ArrayList<String>shortestPathList = new ArrayList<String>();
		private int pathCost = -1;


		public DijkstrasTable(Set<String>vertexNames,HashMap<String, HashMap<String, Integer>> adjacencyMap) {
			/* okay, now go thru, make a new entry for every key in adjacencyMap hashmap and
			 * have it point to a new priority queue HEAP TYPE ENTRY and use that 
			 * within the computation of DSP. (Put in array of entry before heapification)
			 */

			this.vertexMap = new HashMap<java.lang.String, Heap<Entry>>();
			// init the vertexMap 
			Iterator<String>vNames = vertexNames.iterator();
			// String array for quick sort then insert.. 
			String [] unsorted = new String[vertexNames.size()];
			int x = 0;
			while(vNames.hasNext()){
				unsorted[x] = vNames.next();
				x++;
			}

			String [] sortedVerticies = sortedOrder(unsorted,0 , unsorted.length-1);
			LinkedList<String>sortedList = new LinkedList<String>();
			for(int i =0; i<sortedVerticies.length; i++){
				vertexMap.put(sortedVerticies[i], new Heap<Entry>());
				sortedList.add(sortedVerticies[i]);
			}

			// get every string entry for a vertex map .. 
			Iterator<String>adjMapItr = sortedList.iterator();
			while(adjMapItr.hasNext()){
				String tempVertexName = adjMapItr.next();

				// now make a new array of entry types .. 
				HashMap<String, Integer> tempEdgeMap = adjacencyMap.get(tempVertexName);
				int tempSize = tempEdgeMap.size();
				if(tempSize>0){
					Comparable [] tempEdges = (Comparable[])new Comparable[tempSize];
					// iterate, add to the temp array
					int i = 0;
					Iterator <String> tempEdge = tempEdgeMap.keySet().iterator();
					while(tempEdge.hasNext()){
						// create a new entry element 
						String tempEntryString = tempEdge.next();
						int tempEdgeCost = tempEdgeMap.get(tempEntryString);
						tempEdges[i] = new Entry(tempVertexName,tempEntryString, tempEdgeCost);
						i++;
					}
					//System.out.println("I = "+i);
					// now heapify the new array that was made
					Heap tempEntryHeap = new Heap (tempEdges);
					tempEntryHeap.buildMinHeap();
					this.vertexMap.put(tempVertexName, tempEntryHeap);
				}
			}

		}

		// return the shortest path which was initalized in an arraylist...
		public ArrayList<String> getShortestPathList() {
			if(this.pathCost!=-1){
				return this.shortestPathList;
			}else{
				ArrayList<String> none = new ArrayList<String>();
				none.add("None");
				return none;
			}
		}


		/*
		 * Testing function to iterate all edges of the
		 * Dijksta table. This is going to be a nifty structure.
		 */
		public void iterateEdgeMapping(){
			// retrieve the set of verticies... 
			if(this.vertexMap.size()>0){
				Set<String>vertexNames = vertexMap.keySet();

				Iterator<String> vertString = vertexNames.iterator();

				while(vertString.hasNext()){
					String tempString = vertString.next();
					System.out.print(tempString+" : ");
					if(vertexMap.containsKey(tempString)){
						vertexMap.get(tempString).iterate();
					}else{
						System.out.print("[No Edges]");
					}

				}
			}
		}

		public Entry getLowestWeight(String vertexName){
			if(this.vertexMap.containsKey(vertexName)){
				Heap<Entry>tempHeap = this.vertexMap.get(vertexName);
				return (Graph<E>.Entry) tempHeap.removeFirstMinHeap();
			}else{
				return new Entry(vertexName,"None",-1);
			}
		}

		/**
		 * Method that implements Dijkstra's shortest path with the current strucutre
		 */

		public void computeShortestPath(String vertexOne, String vertexTwo){

			if(this.vertexMap.containsKey(vertexOne) && this.vertexMap.containsKey(vertexTwo)){

				HashMap <String, Integer> distanceMapping = new HashMap<String, Integer>();
				HashMap <String, ArrayList<String>> pathMap = new HashMap<String, ArrayList<String>>();
				// init the hashmap 


				Iterator<String>vNames = this.vertexMap.keySet().iterator();
				while(vNames.hasNext()){
					String tempName = vNames.next();
					distanceMapping.put(tempName, Integer.MAX_VALUE);
					ArrayList<String> tempPath = new ArrayList<String>();
					tempPath.add(vertexOne);
					pathMap.put(tempName, tempPath);

				}
				distanceMapping.put(vertexOne, 0); // always init to zero... no dist = no cost..


                // base initializations for dijkstra's
				Set<String>visited  = new HashSet<String>();
				visited.add(vertexOne);
				Heap<Entry> edgeHeap = this.vertexMap.get(vertexOne);
				Queue<Entry> edgeQueue = new LinkedList<Entry>();
				while(!edgeHeap.isEmpty()){
					edgeQueue.offer(edgeHeap.removeFirstMinHeap());
				}

				
				// while the queue is empty
				while(!edgeQueue.isEmpty()){
					Entry tempEntry = edgeQueue.poll();
					String tempString = tempEntry.vertexName;
					Heap<Entry>tempHeap = this.vertexMap.get(tempString);
					while(!tempHeap.isEmpty()){
						edgeQueue.offer(tempHeap.removeFirstMinHeap());
					}
					visited.add(tempString); // add to visited set

					String source = tempEntry.source; // source name
					String target = tempEntry.vertexName;// dest name
					int cost = tempEntry.cost;//edge cost


					// if the distance mapping hasn't been initialized...
					if(distanceMapping.get(source).equals(Integer.MAX_VALUE)){
						// distance mapping not set yet... 
						distanceMapping.put(target, cost);
						// modify the path to said node. 
						ArrayList <String>tempPath = new ArrayList<String>();
						tempPath.add(target);
						pathMap.put(target, tempPath);
					}else{

						// it has been visited, check the set value
						int calculatedValue = distanceMapping.get(source) + tempEntry.cost;

						// change it if its empty...
						if (calculatedValue < distanceMapping.get(tempEntry.vertexName)){
							// distance mapping not set yet... 
							distanceMapping.put(target, calculatedValue);
							// modify the path to said node. 
							// cpyctor for arraylist... because referential transparaency ...
							ArrayList <String>tempPath = new ArrayList<String>(pathMap.get(source));
							// add the modified list
							tempPath.add(target);
							// modify the path in the hashtable.. 
							pathMap.put(tempEntry.vertexName, tempPath); 
						}
					}
				}


				distanceMapping.put(vertexOne, 0); // remember zed .. 

				if(distanceMapping.get(vertexTwo)<Integer.MAX_VALUE){
					// select the correct arraylist 
					ArrayList<String>path = pathMap.get(vertexTwo);
					for(int i =0; i<path.size(); i++){
						this.shortestPathList.add(path.get(i));
					}
					this.pathCost = distanceMapping.get(vertexTwo);;
				}

			}else{
				throw new java.lang.IllegalArgumentException();
			}
		}

	}

	/**
	 *
	 * Function to remove an entry object from 
	 * the dijkstra table (A hashmap of binary min-heaps)
	 */
	public Entry removeFromDijkstaTable(String vertexName){
		DijkstrasTable testTable = new DijkstrasTable(this.getVertices(),adjacencyMap);
		return testTable.getLowestWeight(vertexName);
	}

	/**
	 * Since DijkstasTable is a private class, make a public testing method.. 
	 */

	public void testDijkstrasTable(){
		DijkstrasTable testTable = new DijkstrasTable(this.getVertices(),adjacencyMap);
		//testTable.iterateEdgeMapping();
		testTable.computeShortestPath("0", "5");
	}
	
	/**
	 * Private class to represent an edge and its associated values. 
	 * For use in computation of Dijkstra's shortest path. 
	 * This class just objectifies an edge for easier data
	 * encapsulation and cohesion purposes.
	 */
	private class Entry implements Comparable<Entry>{
		String vertexName;
		int cost;
		String source;
		Entry(String source, String vertexName, int cost){
			this.vertexName = vertexName;
			this.cost = cost;
			this.source = source;
		}
		Entry(Object o){
			if(o instanceof Graph.Entry){
				this.vertexName = ((Graph.Entry) o).vertexName;
				this.cost = ((Graph.Entry) o).cost;
			}
		}

		@Override
		public int compareTo(Graph<E>.Entry o) {
			Entry otherEntry = new Entry(o);
			Integer thisCost = this.cost;
			Integer otherCost = otherEntry.cost;
			return thisCost.compareTo(otherCost);
		}

		public String toString(){
			return new String(this.source+" -> "+this.vertexName+" weight : " + this.cost);
		}
	}

}