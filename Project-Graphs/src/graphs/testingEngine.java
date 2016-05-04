package graphs;

import java.util.ArrayList;

public class testingEngine {


	public static void main(String[] args) {

		boolean pathNotFound = true;
		while(pathNotFound){
			Graph<Integer> testingGraph = new Graph<Integer>(); 
			String [] edges = new String [10000];
			for(int x = 0; x<10000; x++){
				String tS = String.valueOf(x);
				testingGraph.addVertex(tS, x);
				edges[x] = tS;
			}

			for(int i =0; i<8760; i++){
				int randIndex= (int)(Math.random()*9900);
				int randIndex_2 =(int)(Math.random()*9900);
				int randWeight = (int)(Math.random()*100);
				testingGraph.addDirectedEdge(String.valueOf(randIndex), String.valueOf(randIndex_2) , randWeight);
			}

			String vertexOne = edges[1110];
			String vertexTwo = edges[5327];
			String vertexThree = edges[3167];
			String vertexFour = edges[9173];
			String vertex5 = edges[999];
			String vertex6 = edges[18];
			String vertex7 = edges[6452];
			String vertex8 = edges[4134];

			ArrayList<String>pathOne = new ArrayList<String>();
			ArrayList<String>pathTwo = new ArrayList<String>();
			ArrayList<String>path3 = new ArrayList<String>();
			ArrayList<String>path4 = new ArrayList<String>();


			testingGraph.doDijkstras(vertexOne, vertexTwo,pathOne);
			testingGraph.doDijkstras(vertexThree, vertexFour,pathTwo);
			testingGraph.doDijkstras(vertex5, vertex6,path3);
			testingGraph.doDijkstras(vertex7, vertex8,path4);
			

			if(pathOne.size()>1||pathTwo.size()>1|| path3.size()>1 || path4.size()>1){
				pathNotFound = false;
			}




			System.out.println("\n\n Testing shortest path : \n");

		}

	}
}
