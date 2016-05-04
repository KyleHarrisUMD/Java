package graphs;

public class Heap <T extends Comparable <T> >{


	/*
	 * Class represents a heap which has been implemented 
	 * as a max heap with the internal data representation
	 * as an integer array of length n. 
	 */


	private T internalData[]; // private integer array for internal data representation. 
	private int numElements;  // private int for elem count to check the need to resize.


	private int size;
	/*
	 * Functionality to aimplement : 
	 *     Buiding both a max heap from this class. 
	 *     
	 *     implement heap sort. 
	 *    int [] sort();
	 *     
	 *    int[] heapify
	 *    int [] buildMaxHeap..
	 *    int getMax
	 *     
	 *     
	 *     
	 */


	public Heap(){
		// need to create a new array of objects and use T[] to cast it
		this.internalData = (T[])new Comparable[2]; 
	}

	/**
	 * Constructor to take in an array and 
	 * use that to initalize values rather than
	 * continously adding. 
	 *
	 */
	public Heap(T[]data){
		this.internalData = data;
		this.numElements = data.length;
		this.size = data.length;
	}


	/*
	 * Function to construct a maxHeap based upon 
	 * the internal data within this class. 
	 * 
	 * It does not use referrential transpaerncy as its
	 * operating on this current instantiation of heapArray.
	 */
	public void buildMaxHeap(){

		/* Function will run from n/2 down to 0 setting 
		 * modifiying the data within the array. 
		 * 
		 * It operates from the 'bottom' and goes up to the root A[0] 
		 * and swaps values during those steps.. 
		 * 
		 * The reason it starts at i = n/2 is because after n/2, all
		 * elements will be leaves and will be considered in the
		 * comparison evaluations to determine if the subHeap at A[i]
		 * meets the necessary invariants. 
		 * 
		 * Its not as efficent to build a heap from the top down becuase then
		 * it will have to use a divide and conquer strategy to achive the same thing.. 
		 * this is because you'll have to check the children at each index and call
		 * the build heap function from there.. an example is as follows : 
		 * 
		 *    topDown(T[]A, int i){ 
		 *    for(int i = size /2; i>=0; i--){
		 *      topDown(A, (2*i)+1) // calls construction w / left
		 *      topDown(A, (2*i)+2)) // calls construction w/ right 
		 *      heapify(A, i);
		 *      }
		 *     }
		 *     
		 *     The efficency of doing it from the top down can be found by using the masters 
		 *     theorem. 
		 *     
		 *     Since there is a relation with the recurrances, a recursion tree can be derived 
		 *     from it and the work to arrrive at any level is the sum across all levels 
		 *     by the masters theorem is : 
		 *     
		 *     2T(n/2)+O(n)  = O(n log n)
		 *     
		 *     The efficency of bottom up construction can be found in a similar way and the 
		 *     asymptotic bound is O(n).
		 *     
		 *     O(n log(n)) > O(n) 
		 *     
		 *     
		 *     The efficency of buliding a max or min heap is dependant on how you do it.. 
		 *     prefereably you choose the bottom up approach. 
		 *     		 
		 * 
		 */
		int upperBound = (this.size/2);


		for(int i = upperBound; i>=0; i--){
			this.maxHeapify(internalData, i);
		}

	}



	/*
	 * Function constructs a minHeap based upon the internal data.
	 * 
	 * Like above, not operating on referrential transparency. 
	 */

	public void buildMinHeap(){
		int upperBound = this.size / 2; 
		for(int i = upperBound; i>=0; i--){
			this.minHeapify(this.internalData, i);
		}
	}

	/**
	 * Function works anaglously to the maxHeapify function. 
	 * The only difference is you're swapping values if any children
	 * of the current element are less than the parent (A[i]). 
	 * 
	 *  
	 * @param A
	 * @param i
	 */
	private void minHeapify(T[] A, int i){

		T rightChild = right(i);
		T leftChild  = left(i);

		T ithElement = A[i];
		int smallestIndex = i;

		if(leftChild!=null){

			if(leftChild.compareTo(ithElement)<0){
				smallestIndex = leftIndex(i);	
			}
		}

		if(rightChild!=null){
			T smallestElement = this.internalData[smallestIndex];
			if(rightChild.compareTo(smallestElement)<0){
				smallestIndex = rightIndex(i);
			}

		}

		if(smallestIndex!=i){
			// swap 
			swap(smallestIndex, i);
			minHeapify(A, smallestIndex);
		}
	}

	/*
	 * Private function to return an array 
	 * of Comparable objects for use in the 
	 * buildMaxHeap function. 
	 * 
	 * This function operates recursivley and it swaps elements
	 * with the index that it started at and traverses all the way 
	 * downwards or rightward to assure that the subheaps meet the 
	 * invariants defined by a maxHeap. 
	 */
	private void maxHeapify(T [] A, int i){

		// function swaps the elements if its doesn't meet the heap invaritants.. 

		T rightChild = right(i);
		T leftChild  = left(i); 
		T ithElement = A[i];

		int largestIndex = i;

		if(leftChild!=null){
			if(leftChild.compareTo(ithElement)>0){
				largestIndex = leftIndex(i);
			}
		}

		if(rightChild!=null){
			if(rightChild.compareTo(this.internalData[largestIndex])>0){
				largestIndex = rightIndex(i);
			}

		}

		if(largestIndex!=i){
			swap(i, largestIndex);
			maxHeapify(A, largestIndex);
		}



	}


	/**
	 * Function invokes a heapsort on the array in the 
	 * method signature. Works by using the invairants set forth
	 * by the heap structure and uses that to its advantage. 
	 * 
	 * Since buildMaxHeap / buildMinHeap have O(n) efficencies and
	 * heapify has O(log n), the efficency of a heapsort is O(n log n) 
	 * 
	 * 
	 * @param toBeSorted
	 */
	public void sort(){
		//	this.buildMaxHeap();
		int tempSize = this.size;
		for(int i = this.size-1; i>=0; i--){
			swap(0, i);
			this.size--;
			maxHeapify(this.internalData, 0);
		}
		this.size = tempSize;

	}

	/*
	 * By default I wrote this class as a max heap, but then I saw a couple questions on
	 * the google doc about minHeaps so, I'm going to leave this, this will put it in 
	 * reverse order... so there is another below that puts everything into natural ordering
	 */
	public void minSort(){
		int tempSize = this.size;
		for(int i = this.size-1; i>=1; i--){
			swap(0, i);
			this.size--;
			minHeapify(this.internalData, 0);
		}
		this.size = tempSize;
	}

	/*
	 * The removal from a heap isn't that tricky.. just replace the first element
	 * with what ever child element is larger.. 
	 */
	public T removeFirstMinHeap(){ 


		T min = this.internalData[0];
		this.internalData[0] = this.internalData[this.size-1];
		this.size--;
		this.buildMinHeap();
		return min;


	}

	public T removeFirstMaxHeap(){
		T max = this.internalData[0];
		this.internalData[0] = this.internalData[size-1];
		this.size--;
		this.buildMaxHeap();
		return max;
	}

	public T[] sort (T[]A){

		// constructs a new object with the data A.. 
		Heap tempHeap = new Heap(A);
		tempHeap.sort();
		return (T[])tempHeap.getData();
	}


	public T[] getData(){
		return this.internalData;
	}

	public int size(){
		return this.size;
	}


	/**
	 * Two functions below return a T value of 
	 * a given index in the internal data representation. 
	 * 
	 * @param i
	 * @return
	 */
	private T left(int i){
		// see if the bound is valid 
		int leftIndex = (2*i) + 1;
		if(leftIndex < this.size){
			return this.internalData[leftIndex];
		}else{
			return null;
		}
	}

	private T right(int i){
		// see if the right bound is valid 
		int rightIndex = (2*i)+2;
		if(rightIndex < this.size){
			return this.internalData[rightIndex];
		}else{
			return null;
		}
	}

	private void swap(int x, int y){
		T temp = this.internalData[x];
		this.internalData[x] = this.internalData[y];
		this.internalData[y] = temp;
	}

	private int leftIndex (int i){
		if(i == 0){
			return 1;
		}else{
			return (2*i)+1;
		}
	}

	private int rightIndex(int i){
		if(i == 0){
			return 2;
		}else{
			return (2*i)+2;
		}
	}



	/*
	 * Resize function to be used when there is dynamic addition
	 * taking place. 
	 */

	private void resize(){
		int currentSize = this.internalData.length; 
		int newSize = currentSize*2;
		T [] tempData = (T[])new Comparable [newSize];
		for(int i = 0; i<currentSize; i++){
			this.internalData[i] = null;
			tempData[i] = this.internalData[i];
		}

		// change internal data size
		this.internalData = (T[]) new Comparable[newSize];
		// loop to reinit the values.
		for(int x = 0; x<tempData.length; x++){
			internalData[x] = tempData[x];
		}
	}



	// code just for a pretty iteration function
	public void iterateInLine(){

		if(this.size>0){
			System.out.print("["+this.internalData[0]+", ");
			for(int i = 1; i<this.numElements-2; i++){
				System.out.print(this.internalData[i].toString()+", ");
			}
			System.out.print(this.internalData[this.numElements-1].toString()+"]");
		}else{
			System.out.print("{No Edges}");
		}
	}

	public void iterate(){
		System.out.print("{");
		boolean iterated = false;
		for(int x =0; x<this.size; x++){
			iterated = true;
			if(x<this.size-1){
				System.out.print(this.internalData[x].toString()+", ");
			}else{
				System.out.print(this.internalData[x].toString()+"}\n");
			}
		}
		if(!iterated){
			System.out.print("}\n");
		}


	}

	public boolean isEmpty(){
		if(this.size<=0){
			return true;
		}else{
			return false;
		}
	}

}
