package randomizedQueuesDeques;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] queue;
	private int size;

	public RandomizedQueue(){
		
		queue = (Item[]) new Object[1];
		size = 0;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
	public void enqueue(Item item){
		if(item == null){
			throw new java.lang.NullPointerException();
		}
		
		if(size == queue.length){
			resize(2*queue.length);
		}
		queue[size++]=item;
	}
	
	private void resize(int n) {
		Item[] temp = (Item[]) new Object[n];
		
		for (int i = 0; i < size; i++) {
            temp[i] = queue[i];
        }
        
        queue = temp;
		
		
	}

	public Item dequeue(){
		 if (isEmpty()) {
	            throw new java.util.NoSuchElementException();
	        }
		 int rand = StdRandom.uniform(size);
		 Item item = queue[rand];
		 
		 if (rand != size - 1) {
	            queue[rand] = queue[size - 1];
	        }
	        // set the last item to null
	        queue[size - 1] = null;
	        size--;
	        
	        if (size < queue.length/4) {
	            resize(queue.length/2);
	        }
		 
		 return item;
	}
	
	public Item sample(){
		if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int random = StdRandom.uniform(size);
        return queue[random];
	}
	
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new RandomizedQueueIterator<Item>();
	}
	
	 private class RandomizedQueueIterator<E> implements Iterator<E> {
	        public boolean hasNext() {
	            return size() > 0;
	        }
	        public E next() {
	            if (isEmpty()) {
	                throw new java.util.NoSuchElementException();
	            }
	            return (E) dequeue();
	        }
	        public void remove() {
	            throw new java.lang.UnsupportedOperationException();
	        }
	    }
	
	public static void main(String[] args){
		
	}

}
