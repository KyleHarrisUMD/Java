package utilities;

public class testingEngine {

	public static void main(String[] args) {
		
		
		FunctionalList<String> testingList = new FunctionalList<String>();
		testingList = testingList.add("This");
		testingList = testingList.add("element");
		testingList = testingList.add("should");
		testingList = testingList.add("be");
		testingList = testingList.add("last");

		
		FunctionalList<String> testingList_2 = new FunctionalList<String>();
		testingList_2 = testingList_2.add("This_2");
		testingList_2 = testingList_2.add("element_2");
		testingList_2 = testingList_2.add("should_2");
		testingList_2 = testingList_2.add("be_2");
		testingList_2 = testingList_2.add("last_2");

		
		testingList = testingList.append(testingList_2);
		System.out.println(testingList.nth(7));
		System.out.println(testingList.positionOf("should_2")+"\n");
		testingList.traverse();
		
		
		testingList = testingList.remove("This_2");
		System.out.println(" ---- blah -- \n");
		testingList = testingList.add("element");
		testingList = testingList.add("element");
		testingList = testingList.add("element");
		testingList = testingList.add("element");
		testingList = testingList.add("element");
		testingList = testingList.add("element");
		testingList = testingList.add("element");
		testingList = testingList.add("element");
		testingList = testingList.add("element");
		testingList = testingList.add("element");

		
		FunctionalList<String> replaceList = testingList.subst("element", "booger");
		testingList.traverse();
		System.out.println("\n see if replace modification works \n");
		replaceList.traverse();
		System.out.println("\n check : \n");

		
		FunctionalList<String>reversed = testingList.reverse();
		testingList.traverse();
		System.out.println("Reversed : \n");
		reversed.traverse();
		
		
		System.out.println("\nTesting copy ctor\n"); 
		
		FunctionalList<String>newList = new FunctionalList<String>(reversed);
		System.out.println("\nTesting iter of newList :\n");
		newList.traverse();
		
		
		System.out.println("Further testing of cpy ctr : ");
		FunctionalList<Integer> ints = new FunctionalList<Integer>();
		ints = ints.add(1);
		ints = ints.add(2);
		ints = ints.add(3);
		ints = ints.add(4); 
		

		System.out.println("\nPrint int list : \n");
		ints.traverse();
		
		FunctionalList<Integer> doesntHave5 = new FunctionalList<Integer>(ints);
		ints = ints.add(5);
		
		System.out.println("\nInt copy w / o  5 \n");
		doesntHave5.traverse();
		System.out.println("\nOriginal with 5  : \n");
		ints.traverse();
		
		
		
		System.out.println(ints.toString());
		System.out.println("\n Test array print \n");
		
		Object[] intsArray = ints.toArray();
		for(int i = 0; i<intsArray.length; i++){
			System.out.println("From array : " + (Integer)intsArray[i]);
		}

	}

}
