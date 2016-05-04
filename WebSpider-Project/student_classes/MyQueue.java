package student_classes;

import java.util.LinkedList;
import java.util.List;

public class MyQueue<T> {

	
	// a linked list will work well for this since it implements queue
	private List <T> elementQueue = new LinkedList<T>();
	
	
	/**   public int size()
	 *   	returns number of items in the queue
	 */
	public synchronized int size(){
		return this.elementQueue.size();
	}
	
	/**
	 * void clear()
	 *   	removes all items from the queue
	 * */
	public synchronized void clear(){
		this.elementQueue.clear();
	}
	
	
	/*
	 * Method waits while the queue is empty because no elements
	 * will exist that can be removed. 
	 * 
	 * When there is an element, remove it.
	 */
	public synchronized T dequeue(){
		while(this.elementQueue.isEmpty()){
			try{
			   wait();
			}catch(InterruptedException e){
				// no op in this case.
				//System.out.println(e.getStackTrace());
			}
		}
		// it waited, thus there is an element 
		// return the first one
		T data = elementQueue.get(0);
		elementQueue.remove(0);
		return data;
	}
	
	
	public synchronized void enqueue(T o){
	
		if(o!=null){ // if not null, add and let others know its there.
		this.elementQueue.add(o);
		notifyAll();
		}
	}
}
