package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import student_classes.CommonFunctions;

public class StudentTests {

	@Test
	public void testBalanced(){
		String maths = "(x + 3((x^(3x) + 2)) + 2(667t))";
		assertTrue(CommonFunctions.isBalanced(maths));
	}

	@Test
	public void testPalindrome(){
	
		
		String name = "hannah";
		assertTrue(CommonFunctions.isPalindrome(name));

		String spaces = "a man a plan a canal panama";
		assertTrue(CommonFunctions.isPalindrome(spaces));

	}

	@Test 
	public void testFibonacci(){

     /*
	  * Fun fact of the day for which ever TA is reading this : 
	  * 
	  * The nth number fibonacci sequence can be computed via a closed 
	  * form formula and ima use that formuler to test my alga-rythm. 
	  * 
	  * Anyways, if you take the limit of the sequence an = (n-2)+(n-1) you
	  * may find that this is equal to phi. Or for the less nerdy of us, the golden ration 
	  * and for the less nerdy than the less nerdy, this value is  = (1+sqrt(5)) / 2. 
	  * 
	  * To compute the nth fibonacci the formual : ( (phi^n) - (-phi^(-n))) / sqrt(5)
	  * will produce the value. 
	  */
		
	 double sqrt5 = Math.sqrt(5.0);
	 double phi   =  (1+sqrt5) / 2;
	 int iterationsDone = 0;
	 
	 int [] fibonacciSequence = new int [100];
	 for(int x = 0; x<100; x++){
		 
		 int currentFib = (int) (((Math.pow(phi, x)) - ((Math.pow((-1*phi), (-1*x)))) ) / sqrt5);
		
		 fibonacciSequence[x] = currentFib; 
		 
		 if(currentFib == Integer.MAX_VALUE){
			 iterationsDone = x;
			 break;
		 }
	 }
	 //System.out.println("The " + iterationsDone + "th fibonacci number > 2*32-1");
	 // thats pretty cool. 
	 
	 // back to my life // 
	 int [] stackComputed = new int [iterationsDone];
	 for(int x = 0; x<iterationsDone; x++){
		 stackComputed[x] = CommonFunctions.fibonacci(x);
	 }
	 
	 for(int i = 0; i<iterationsDone; i++){
		 // make sure its correct FOR ANY INTEGER WHILE VAL < 2^32-1 
		 if(stackComputed[i]!=fibonacciSequence[i]){
			 fail("Code is wrong because mathematics says so.");
		 }
		 // it is. 
	 }
	 

	}


}
