package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import utilities.FunctionalList;
/* You may wish to implement any or none of these. Or, perhaps
 * you wish to design your own?
 */
public class StudentTests {

	@Test
	public void testFunctionalList() {
		
		FunctionalList<String> testingList = new FunctionalList<String>();
		testingList = testingList.add("This");
		testingList = testingList.add("element");
		
		FunctionalList<String>tList_2 = new FunctionalList<String>();
	    tList_2 = testingList.add("string 3");
		assertTrue(testingList.size() == 2);
		assertEquals(tList_2.size() ,3);
	}

	@Test
	public void testAdd() {
		
		FunctionalList<String> testingList = new FunctionalList<String>();
		testingList = testingList.add("This");
		testingList = testingList.add("element");
		testingList = testingList.add("should");
		testingList = testingList.add("be");
		testingList = testingList.add("last");

		assertTrue(testingList.size() == 5);
	}
	
	@Test
	public void testAppend() {
		
	  FunctionalList<Integer>first_5 = new FunctionalList<Integer>();
	  for(int i = 0; i<5; i++){
		  first_5 = first_5.add(i);
	  }
	  assertTrue(first_5.size() == 5);
	 
	  FunctionalList<Integer>fiveThru10 = new FunctionalList<Integer>();
	  for(int i = 5; i<=10; i++){
		  fiveThru10=fiveThru10.add(i);
	  }
	  
	  first_5 = first_5.append(fiveThru10); 
	  assertTrue(first_5.size() == (5+fiveThru10.size()));
	  
	  // append some elements to a new list
	}

	@Test
	public void testRemove() {
		
		FunctionalList<String> testingList = new FunctionalList<String>();
		testingList = testingList.add("This");
		testingList = testingList.add("element");
		testingList = testingList.add("should");
		testingList = testingList.add("be");
		testingList = testingList.add("last");

		testingList = testingList.add("element");//1
		testingList = testingList.add("element");//2
		testingList = testingList.add("element");//3
		testingList = testingList.add("element");//4
		testingList = testingList.add("element");//5
		testingList = testingList.add("element");//6
		testingList = testingList.add("element");//7
		testingList = testingList.add("element");//8/
		testingList = testingList.add("element");//9
		testingList = testingList.add("element");//10

		testingList = testingList.add("typefaster"); 
		
		int oldSize = testingList.size();
		testingList = testingList.remove("element");
		int newSize = testingList.size(); 
		
		assertTrue(oldSize>newSize);
	}

	@Test
	public void testReverse() {
		FunctionalList<String> testingList = new FunctionalList<String>();
		testingList = testingList.add("This");
		testingList = testingList.add("element");
		testingList = testingList.add("should");
		testingList = testingList.add("be");
		testingList = testingList.add("last");

		testingList = testingList.add("element");//1
		testingList = testingList.add("element");//2
		testingList = testingList.add("element");//3
		testingList = testingList.add("element");//4
		testingList = testingList.add("element");//5
		testingList = testingList.add("element");//6
		testingList = testingList.add("element");//7
		testingList = testingList.add("element");//8/
		testingList = testingList.add("element");//9
		testingList = testingList.add("element");//10

		testingList = testingList.add("typefaster"); 
		
		FunctionalList<String>reversed = testingList.reverse();
		
		assertEquals(reversed.size(), testingList.size());
		
		boolean equals = true;
		int xthIter = 0;
		for(int x = testingList.size()-1; x>0; x--){
			if(testingList.nth(x) != reversed.nth(xthIter)){
				System.out.println("False : " + testingList.nth(x) + " and  " +reversed.nth(xthIter)) ;
				equals = false;
			}
			xthIter++;
		}
		assertTrue(equals);
	}

	@Test
	public void testSize() {
		  FunctionalList<Integer>first_5 = new FunctionalList<Integer>();
		  for(int i = 0; i<5; i++){
			  first_5 = first_5.add(i);
		  }
		  assertTrue(first_5.size() == 5);
	}

	@Test
	public void testPositionOf() {

		  FunctionalList<Integer>first_5 = new FunctionalList<Integer>();
		  for(int i = 0; i<5; i++){
			  first_5 = first_5.add(i);
		  }
		  assertTrue(first_5.size() == 5);
		 
		  FunctionalList<Integer>fiveThru10 = new FunctionalList<Integer>();
		  for(int i = 5; i<=10; i++){
			  fiveThru10=fiveThru10.add(i);
		  }
		  
		  first_5 = first_5.append(fiveThru10); 
		  
		  assertTrue(first_5.positionOf(6) == 6);
	}

	@Test
	public void testNth() {
		
		
		  FunctionalList<Integer>first_5 = new FunctionalList<Integer>();
		  for(int i = 0; i<5; i++){
			  first_5 = first_5.add(i);
		  }
		  assertTrue(first_5.size() == 5);
		 
		  FunctionalList<Integer>fiveThru10 = new FunctionalList<Integer>();
		  for(int i = 5; i<=10; i++){
			  fiveThru10=fiveThru10.add(i);
		  }
		  
		  first_5 = first_5.append(fiveThru10); 
		  
		  assertTrue(first_5.nth(6) == 6);
		  
		  boolean exceptionCaught = false;
		  try{
			  int val = first_5.nth(18);
		  }catch(IllegalArgumentException e){
			  exceptionCaught = true;
		  }
		  assertTrue(exceptionCaught);
		  
		  boolean illegalAccess = false;
	      FunctionalList<String>nullList= new FunctionalList<String>();
	      try{
	    	  String cheese = nullList.nth(69);
	      }catch(Exception e){
	    	  
	    	 illegalAccess = true;
	      }
	      assertTrue(illegalAccess);
		 
	}

	@Test
	public void testToString() {
		FunctionalList<Integer> ints = new FunctionalList<Integer>();
		ints = ints.add(1);
		ints = ints.add(2);
		ints = ints.add(3);
		ints = ints.add(4); 
		
        assertEquals(ints.toString(),"[1, 2, 3, 4]");
	}

}
