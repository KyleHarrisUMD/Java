package utilities;


/**
 * <P>A \code>FunctionalList</code> object supports linked-list operations but, unlike the
 * "standard" implementation that performs \emph{in situ} modifications, e.g., destructively modifies
 * the original list when adding/removing elements, a "functional" list create copies of the original 
 * object with the desired modifications. This has several ramifications---some good, some not so good.
 * <P>
 * <BR>
 * <P>In this assignment, you will implement a functional linked list that provides a subset of useful
 * operations.
 * @author UMD CS Department
 *
 *
 * @param <T>
 */
public class FunctionalList<T> {
	/**
	 * Internal (hidden) class ... define a Node to keep track of values and links.
	 * @author UMD CS Department
	 *
	 * @param <T>
	 */
	class Node <T> {
		// properties here:
		Node <T> next;
		T data;

		public Node(T data){
			this.next = null;
			this.data = data;
		}

		void setNext(Node <T>next){
			if(this.next == null){
				this.next = next;
			}else{

				this.next = next;
			}
		}

		Node<T>getNext(){
			return this.next;
		}

	}

	// define additional properties here.
	private Node<T>head;
	private int size;


	// define ctor(s) here:
	public FunctionalList() {
		this.head = null;
		this.size = 0;
	}

	public FunctionalList<T> add( T element ) {
		FunctionalList<T> copyAdd = new FunctionalList<T>();
		copyAdd = copyList();
		copyAdd = copyAdd.recursiveAdd(copyAdd, element, copyAdd.head);
		return copyAdd;
	}

	/**
	 * Copy constructor for FunctionalList. This recursivley iterates through 
	 * the given list and appends elements to the current instance.
	 * 
	 * @param list to copy in to current list.
	 */
	public FunctionalList (FunctionalList<T>list){
		auxillaryCopyCtr(list, list.head);
	}
	private void auxillaryCopyCtr(FunctionalList<T>copyList, Node<T>currentNode){

		if(currentNode == null){
			// stop the copying because there are no more elements
			return;
		}else{
			/*add the element to this list and change currentNode to currentNode.next
			don't call this.add(currentNode.data), all it will do is create a variable 
			on the stack frame and not assign a vairble to the reference. */

			// use a different helper function to add the element w/ no return type.
			copyCtrAdd(this.head, currentNode.data);
			//System.out.println("Element added to current list");
			auxillaryCopyCtr(copyList, currentNode.next);
		}
	}
	/**
	 * Function appends an element at the end of the current list 
	 * rather than appending an element to the end of a copy of the 
	 * current list. 
	 */
	private void copyCtrAdd(Node<T>currentNode,T element){
		// check if the head is null 
		if(this.head == null){
			this.head = new Node<T>(element);
			this.size++;
			// check if currentNode.next == null, if so, add a new element
		}else if (currentNode.next == null){
			currentNode.next = new Node<T>(element);
			this.size++;
			// return to exit the method call 
			return;

			// if not, make a recursive call and set currentNode = currentNode.next;
		}else{
			copyCtrAdd(currentNode.next, element);
		}
	}



	public FunctionalList<T> append( FunctionalList<T> other ) {
		FunctionalList <T> appendedList = copyList();
		appendedList = auxillaryAppend(appendedList,other.head,other);
		return appendedList;
	}
	private FunctionalList<T> auxillaryAppend(FunctionalList<T>newList,Node<T>tempNode, FunctionalList<T>appendList){
		if(tempNode == null){
			return newList;
		}else{
			newList = newList.add(tempNode.data);
			return auxillaryAppend(newList,tempNode.next,appendList );
		}
	}

	private boolean isEmpty(){
		return this.size<0?true:false;
	}


	public FunctionalList<T> remove( T element ) {
		FunctionalList<T> copiedList = new FunctionalList<T>(); 
		copiedList = auxillaryRemove(copiedList, this.head, element);
		return copiedList;	
	}

	public FunctionalList<T> reverse() {
		/*
		  rather than doing a recursive reversal (which is a pain) and then adding it to 
		  a copied list, why don't we just add the new elements to the front of a new list?
		  that would be like adding a string to a stack and comparing it to the orginial.. thus 
		  it would indeed be reversed. 
		 */
		FunctionalList<T> reversedList = new FunctionalList<T>();
		FunctionalList<T> forwardList = copyList();
		reversedList = auxillaryReverse(forwardList, forwardList.head, reversedList);
		return reversedList;

	}
	/*
	 * Function will recusivley go through the list elements in the 
	 * parameter forwardList and effectlivley treat the FunctionalList reverseList
	 * as a stack, which will push the current element data(reference in forwardList)
	 * to the front of the reversed list.
	 */
	private FunctionalList<T>auxillaryReverse(FunctionalList<T>forwardList, Node<T>currentNode, FunctionalList<T>reversedList){
		if(currentNode == null){
			return reversedList;
		}else{
			reversedList = reversedList.pushToList(reversedList, currentNode.data);
			return auxillaryReverse(forwardList, currentNode.next, reversedList);
		}
	}

	private FunctionalList<T> pushToList(FunctionalList<T>original, T data){
		Node<T>newNode = new Node<T>(data);
		Node<T>tempHead = original.head;
		original.head = newNode;
		newNode.next = tempHead;
		size++;
		return original;
	}
	public int size() {
		return this.size;
	}

	public int positionOf( T element ) {
		return auxillaryPosOf(this, element, this.head,0);
	}

	private int auxillaryPosOf(FunctionalList<T>currentList, T element,Node<T>elem,int pos){
		if(elem.data == element){
			return pos;
		}else if(elem == null || pos >= currentList.size){
			return -1;
		}else{
			return auxillaryPosOf(currentList,element, elem.next, pos+1);
		}
	}

	public T nth( int index) throws IllegalArgumentException {
		if(this.isEmpty()){ 
			throw new IllegalAccessError();
		}else if (this.size < index || index<0){
			throw new IllegalArgumentException();
		}else{
			return auxillaryNth(this, this.head, 0, index);
		}
	}



	private T auxillaryNth(FunctionalList<T>list, Node<T>currentNode, int lower, int upper){
		if(lower == upper && lower<list.size){
			return currentNode.data;
		}else{
			if(currentNode.next!=null){
				return auxillaryNth(list, currentNode.next, lower+1, upper);
			}else{
				return null;
			}
		}
	}


	/**
	 * The two methods toString and toArray are allowed to be implemented iterativley, 
	 * however they can be done with recursion by putting some variables in the method
	 * signature to be kept on the stack and modifying their values accordinly, but
	 * I'm running out of time so I'm not going to fiddle around. 
	 * 
	 */

	/** Method toString 
	 *  Constructs a string representation using iteration and returns it. 
	 * @return string representation of the current FunctionalList object
	 */

	public String toString() {
		String functionalListString = "[";
		if(this.size>0){
			Node<T> currentNode = this.head;
			while(currentNode.next!=null){
				// append values to the stirng 
				functionalListString += (currentNode.data+", ");
				currentNode = currentNode.next;

			}
			// add the last node value to the string 
			functionalListString+=(currentNode.data+"]");
			// return the string 
			return functionalListString;
		}else
		{  // string signifiying an empty list
			return new String("[]");
		}
	}


	/**
	 * Method toArray creates and array of objects using an
	 * iterative approach and returns it.
	 * @return array of objects within the functional list
	 */
	public Object[] toArray() {
		// create a local variable elementArray with size of current indicies
		T elementArray []; 
		if(this.size>0){
			// only worth doing if it has elements
			elementArray = (T[])new Object[this.size];
			Node<T>currentNode = this.head;
			int iteration = 0;
			while(currentNode.next!=null){
				elementArray[iteration] = currentNode.data;
				// move down LL
				currentNode = currentNode.next;
				// increment array index to match constant of iteration 
				iteration++;
			}
			// at the last element 
			elementArray[iteration] = currentNode.data;

		}else{
			elementArray = (T[])new Object[0];
		}
		return elementArray;

	}


	/**
	 * 
	 * FunctionalList subst(k,v) and auxillarySubst work in conjunction with eachother
	 * in a way analoagous to the remove functions except, rather than omitting the 
	 * occurences of T element, it replaces the occurances of Key with value. 
	 */
	public FunctionalList<T> subst( T key, T value ) {
		FunctionalList<T> copiedList = new FunctionalList<T>(); 
		copiedList = auxillarySubst(copiedList, key,  value, this.head);
		return copiedList;	
	}

	/*
	 * Iterates through a copy and appends the appropriate values to a copy of the list
	 * so it doesnt modify the original.
	 */
	private FunctionalList<T> auxillarySubst(FunctionalList<T> listCopy,T key, T value, Node<T>currentNode){
		if(currentNode == null){
			return listCopy;
		}else{
			if(currentNode.data==key){
				listCopy = listCopy.recursiveAdd(listCopy, value, listCopy.head);
			}else{
				listCopy = listCopy.recursiveAdd(listCopy, currentNode.data, listCopy.head);
			}
			return auxillarySubst(listCopy, key, value, currentNode.next);
		}
	}





	/**
	 * 
	 *Recursive function to iterate through a list in the parameters and 
	 *add the elements at the end of the list.
	 */
	private FunctionalList<T> recursiveAdd(FunctionalList <T> list,T element, Node<T>temp){
		if(list.head == null){
			list.head = new Node<T>(element);
			list.size++;
			return list;
		}else if(temp.next == null){
			temp.next = new Node<T>(element);
			list.size++;
			return list;
		}else{
			return recursiveAdd(list ,element,temp.next);
		}
	}

	/**
	 * Function works by calling an auxillary function to create a new copy
	 * of the current list and returns said copy.
	 * @return copyList, a copy of this 
	 */
	private FunctionalList<T> copyList(){
		FunctionalList<T> copiedList = new FunctionalList<T>(); 
		copiedList = recursiveListCopy(copiedList, this.head);
		return copiedList;

	}

	private FunctionalList<T> recursiveListCopy(FunctionalList<T> listCopy, Node<T>currentNode){
		if(currentNode == null){
			return listCopy;
		}else{
			listCopy = listCopy.recursiveAdd(listCopy, currentNode.data, listCopy.head);
			return recursiveListCopy(listCopy, currentNode.next);

		}
	}


	/* 
	 * traverse and traverseAuxillary are two functions used in visual feedback of list implementation
	 */
	public void traverse(){
		if(this.head!=null){
			Node<T>tempNode = this.head;
			traverseAuxillary(tempNode);
		}
	}
	/**
	 * Goes thru  the list, printing and moving down the list if possible. 
	 * 
	 * @param tempNode current referece to node in list
	 */
	private void traverseAuxillary(Node<T>tempNode){
		if(tempNode.next==null){
			System.out.println(tempNode.data);
		}else{
			System.out.println(tempNode.data);
			if(tempNode.next!=null){
				traverseAuxillary(tempNode.next);
			}
		}
	}

	/**
	 * 
	 * Function is recursive and traverses through a original list while appending 
	 * elements to a new list in the function declaration (works because its on the stack frame)
	 * and then returns it. Logic is embedded to not add elements that match targetElement 
	 * to act as a removal 
	 * 
	 * 
	 * @param listCopy original list passed into recursive function
	 * @param currentNode reference to node in the original list
	 * @param targetElement element that is to be ommited
	 * 
	 * @return FunctionalList<T> copy of originalList however without any occurances of 
	 * targetElement
	 */
	private FunctionalList<T>auxillaryRemove(FunctionalList<T>listCopy, Node<T>currentNode, T targetElement){
		if(currentNode == null){
			return listCopy;
		}else{
			if(currentNode.data!=targetElement){
				listCopy = listCopy.recursiveAdd(listCopy, currentNode.data, listCopy.head);
			}
			return auxillaryRemove(listCopy,currentNode.next, targetElement);
		}
	}

}
