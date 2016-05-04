import java.util.Iterator;
import java.util.Stack;

import student_classes.CommonFunctions;

public class engine {

	public static void main(String[] args) {

		
		Stack<Integer> bigOldStack = new Stack<Integer>(); 
		for(int x = 45; x > 0; x--){
			bigOldStack.push(x);
			
			// just to be weird // 
			for(int i = 0; i<5; i++){
				bigOldStack.push((int)((int)1+Math.random()*69));
			}
		}
		
		CommonFunctions <Integer> minTest = new CommonFunctions<Integer>();
		
		
		Iterator<Integer>stackItr = bigOldStack.iterator();
		while(stackItr.hasNext()){
			System.out.println(stackItr.next());
		}
		System.out.println("Min in stack : " + minTest.min(bigOldStack));
		
 
		               //  l       l  l  r r r   3&3  
		String balanced = "(x^2 + (3x+3^(34)) (peenis) )";
		CommonFunctions<String> balancedStackThingFunctionMobob = new CommonFunctions<String>();
		System.out.println(balancedStackThingFunctionMobob.isBalanced(balanced));
		
		CommonFunctions<String> palin = new CommonFunctions<String>();
		System.out.println(palin.isPalindrome("hannah"));

		
		
		
	}
}
