package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import graphs.Graph;

public class StudentTests {

	@Test
	public void testGraph() {
	}
  
	@Test
	public void testAdditionOfVerticies(){
		Graph<Integer> testingGraph = new Graph<Integer>(); 
		testingGraph.addVertex("Vertex One", 1);
		testingGraph.addVertex("Vertex two", 2);
		testingGraph.addVertex("Vertex three", 3);
		testingGraph.addVertex("Vertex four", 4);
		testingGraph.addVertex("Vertex five", 5);
		testingGraph.addVertex("Vertex six", 6);
		testingGraph.addVertex("Vertex seven", 7);
		testingGraph.addVertex("Vertex eight", 8);
		
		// just wanna see what happens .. 
		
		Iterator <String> graphIterator = testingGraph.getVertices().iterator();
        while(graphIterator.hasNext()){
        	System.out.println(graphIterator.next());
        }
  	}
	
	@Test 
	public void testAdditionOfEdges(){
		
		Graph<Integer> testingGraph = new Graph<Integer>(); 
		testingGraph.addVertex("A", 1);
		testingGraph.addVertex("D", 2);
		testingGraph.addVertex("E", 3);
		testingGraph.addVertex("B", 4);
		testingGraph.addVertex("G", 5);
		testingGraph.addVertex("F", 6);
		testingGraph.addVertex("C", 7);
		testingGraph.addVertex("H", 8);
		
		
		testingGraph.addDirectedEdge("H", "A", 7);
		testingGraph.addDirectedEdge("C", "F", 4);
		testingGraph.addDirectedEdge("C", "E", 5);

		HashMap <java.lang.String, java.lang.Integer> vertexMap = (HashMap<String, Integer>) testingGraph.getAdjacentVertices("A");
		Set <String> verticies = vertexMap.keySet();
		Iterator<String> adjacentIterator = verticies.iterator();
		System.out.println("\n\n\n\n\nTesting for adjacent elements : ");
		while(adjacentIterator.hasNext()){
			System.out.println(adjacentIterator.next());
		}
		
	   assertEquals(testingGraph.getCost("H", "A"),7);
	   
	   System.out.println(testingGraph.toString());
		
	}
	
	
	
	
	
}
