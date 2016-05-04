package student_classes;
<<<<<<< CommonFunctions.java
import java.util.Stack;
=======
import java.util.*;

>>>>>>> 1.11

/*
 * You must implement the following methods using Java's Stack object to 
 * replace the iteration/recursion in the original functions with a series
 * of stack operations, including push, pop, peek and isEmpty. Use no other
 * special library functions or classes; in other words, your code should use
 * standard arithmetic operators and in the case of the reverse function, only
 * push and pop and the default constructor for whichever Java collection
 * class you choose to represent lists.
 */

/* ^^ I've read that paragraph a couple of times, I still have no idea what it is trying to say
 *  Thats my feeling about this documentation in general ..
 */

public class CommonFunctions <T> {



	/**
	 * What is a factorial anyways? 
	 * @param int n factorial? Right? 
	 * @return n! with enthusiasm. 
	 */

	public static int factorial( int n ) {

		// factorial 0 == 1, by definition 
		if(n == 0){ 
			return 1;
		}

		// case where n == 1
		if(n == 1){
			return 1;
		}


		Stack <Integer> factorialStack = new Stack<Integer>();

		int tempN = n; 
		while(tempN > 0 ){
			factorialStack.push(tempN);
			tempN --;
		}

		int factorialSum = 0;
		if(!factorialStack.isEmpty()){
			factorialSum += factorialStack.pop();
		}
		System.out.println("Factoial sum : " + factorialSum);

		while(!factorialStack.empty()){
			factorialSum*=factorialStack.pop();
		}



		return factorialSum;

	}


	/**
	 * Function returns the nth fibonacci number by using stacks. 
	 * The fibonacci sequence is a mathematical recursive sequence 
	 * where the current element = (n-2)+(n-1). 
	 * @param nth fibonacci number to compute. 
	 * 
	 *@return 
	 *	nth fibonacci number  
	 */
	public static int fibonacci( int n ) {

		if(n == 0){
			return 0;
		}

		if(n == 1 || n == 2){
			return 1;
		}

		Stack <Integer> fibbStack = new Stack<Integer>();

		// fibbonacci = (n-2) + (n-1) 
		// its also computable in closed form as (phi^n - (-phi^-n)) / sqrt(5)

		// count the iterations so we can reference where they are .. 
		int iteration = 0; 

		while(iteration<=n){

			// there will be two cases where the next element will have to equal one 
			if(iteration <=2){
				fibbStack.push(1);
			}else{ 

				// stack.get() is in java.stack so no crying. 
				int fibN = fibbStack.get(iteration-2) + fibbStack.get(iteration-1);
				fibbStack.push(fibN);
			}
			iteration++;
		}

		// return the element that we computed and previously pushed onto the stack
		return fibbStack.get(n);
	}


	/**
	 * Given a non-empty stack, returns the smallest item contained within.
	 *  Note, you may assume that this method is called with a non-empty stack of any Comparable objects.
	 *  Your implementation should use only the pop, push and isEmpty
	 *  methods (at most; you might not need all of these) on the Stack class to do this.
	 */

	public static <T extends Comparable< T> > T min( Stack< T > theStack ) {

		/*
		 * Code below is equivalent to finding the minimum element
		 * in an array. Regardless if the stack contains elements that are reversed,
		 * the order doesn't matter so the general approach is the same.
		 */
		T minObj = theStack.peek();
		while(!theStack.isEmpty()){
			T compObj = theStack.pop(); 
			// if the current object < current min
			if(compObj.compareTo(minObj) < 0){
				// set minObj to new min
				minObj = compObj;
			}
		}
		return minObj;
	}


	/**
	 * Function returns true is a string containing multiple parenthesis is
	 * is balanced. This is done with a stack because when an opening parenthesis
	 * is encountered, it is pushed onto the stack (or into). 
	 *  - When a closing parenthesis is encountered, then an element is popped of
	 * the front of the stack. By using this behavior, the stack will be empty
	 * if the parenthesis are balanced.
	 * 
	 *  
	 * 
	 * @param text string to see if it has balanced parenthesis
	 * @return boolean if its balanced or not
	 */
	public static boolean isBalanced(String text) {

		// no text can't be balanced. 
		if(text != null){
			Stack<Character>pStack = new Stack<Character>();
			// loop for length of text 
			for(int i = 0; i<text.length(); i++){
				char currentChar = text.charAt(i);
				if(i==0&&currentChar == ')'){
					return false;
				}
				// if currentChar = opening 
				if(currentChar == '('){
					pStack.push(currentChar);
					// if currentChar = closing
				}else if (currentChar == ')'){
					// pop the element to signify its met with a match
					if(!pStack.isEmpty()){
						pStack.pop();
					}else{
						return false;
					}
				}
			}
			// return if all elements had matches.
			return pStack.isEmpty();
		}
		// if null, return false
		return false;

	}

	/*
	 *A palindrome is a String that reads the same from both directions. For example: "abba" is a palindrome, so is "abcba".
	  An empty string or a string consisting of a single character is a palindrome. Now, collections of "words" can be palin-
	  dromes too: "a man a plan a canal panama" –is a well-known example.
	  Write the isPalindrome function to work on Strings that may contain spaces but will NOT contain punctuation marks nor
	  will they contain upper case letters. 
	 */
	public static boolean isPalindrome( String str ) {
		// case where not much computation is required.
		if((str==null)||str.length()<=1){
			return true;
		}

		StringBuilder noSpaces = new StringBuilder();
		for(int i = 0; i<str.length(); i++){
			char tempChar = str.charAt(i);
			if(tempChar!=' '){
				noSpaces.append(tempChar);
			}
		}

		str = noSpaces.toString();
		int iterationStop = str.length()/2;
		Stack<Character>charStack = new Stack<Character>();

		if(str.length() % 2 != 0){
			// not even so add one to iteration stop
			iterationStop++;


			for(int x =0; x<iterationStop; x++){
				charStack.push(str.charAt(x));
			}
			String secondHalf = str.substring(iterationStop-1, str.length());


			int iter  =0;
			while(!charStack.isEmpty()){

				char tempChar = charStack.pop();
				if(tempChar!=secondHalf.charAt(iter)){
					System.out.println("Failed at :" + tempChar +" and "+secondHalf.charAt(iter));
					return false;
				}
				iter++;
			}

		}else{
			
			for(int x =0; x<iterationStop; x++){
				charStack.push(str.charAt(x));
			}
			String secondHalf = str.substring(iterationStop, str.length());


			int iter  =0;
			while(!charStack.isEmpty()){

				char tempChar = charStack.pop();
				if(tempChar!=secondHalf.charAt(iter)){
					System.out.println("Failed at :" + tempChar +" and "+secondHalf.charAt(iter));
					return false;
				}
				iter++;
			}	
		}



		System.out.println("\n");
		return true;
	}
}
