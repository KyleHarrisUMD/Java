package student_classes;

import java.util.LinkedList;
import java.util.List;

public class MySet<T> {

	/**
	 * Implementation of a set to be concurrently accessed by one or more threads.
	 * Class implements a paradigm which safeguards against common thread shortcomings 
	 * like data races, deadlock, etc. 
	 */
	
	
	/*
	 * Required methods : 
	 *  
	 *  	public int size()
	 *  		returns number of items in the set 
	 *  
	 *  	public void clear()
	 *  		removes all items from the set
	 *  
	 *  	public boolean remove (T o)
	 *  		removes object o from the set. Returns boolean representation
	 *  		of action status
	 *  
	 *  	public boolean add(T o)
	 *  		adds an object to the set. Returns true if successful, false otherwise
	 *  
	 *  	public boolean contains(T o)
	 *  		returns true if the set contains the object, false if otherwise.
	 */
	
	
	    // internal data 
		private List <T> setList = new LinkedList<T>();
		
		
		/**
		 * This class has roughly the same strucutre as MyQueue, just some different
		 * methods but in general the methods and wrapper technique is the same.
		 */
		
		
		public synchronized int size(){
			return this.setList.size();
		}
		
		
		public synchronized void clear(){
			this.setList.clear();
		}
		
		
		public synchronized boolean remove(T o){
			// check if its there and the parameter is valid
			if(o!=null && this.contains(o)){
				this.setList.remove(o);
				return true;
			}else{
				return false;
			}
		}
		
		
		public synchronized boolean add(T o){
			// check if there is already and instance of o in the set. 
			if(setList.contains(o)){
				return false;
			}else{
				setList.add(o);
				return true;
			}
		}
		
		
		public synchronized boolean contains(T o){
			if(o!=null){
				return setList.contains(o);
			}else{
				return false;
			}
		}
}
