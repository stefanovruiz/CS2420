package randomizedQueuesDeques;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Deque<Item> implements Iterable<Item> {
	
	private Node first;
	private Node last;
	private int size;
	
	private class Node{
		private Item item;
		private Node next;
		private Node prev;
	}
	
	public Deque(){
		first=null;
		last=null;
		size=0;
		
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Item item){
		 if (item == null) {
	            throw new java.lang.NullPointerException();
	        }
		Node node = new Node();
		node.item = item;
		if (size() == 0) {
            first = node;
            last = node;
        } else {
            first.prev = node;
            node.next = first;
            first = node;
        }
        size++;
	}
	
	public void addLast(Item item){
		if (item == null) {
            throw new java.lang.NullPointerException();
        }
		Node node = new Node();
		node.item = item;
		 if (size() == 0) {
	            first = node;
	            last = node;
	        } else {
	            last.next = node;
	            node.prev = last;
	            last = node;
	        }
	        size++;
	}
	
	public Item removeFirst(){
		if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
		Item item = first.item;
		if(first == last){
			first= null;
			last = null;
		}else{
			Node second = first.next;
			second.prev = null;
			first.next = null;
			first = second;
		}
		size--;
		return item;
		
	}
	
	public Item removeLast(){
		if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
		Item item = last.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            Node oldLast = last;
            last = oldLast.prev;
            last.next = null;
            oldLast.prev = null;
            oldLast = null;
        }
        size--;
		
		return item;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
	
	 private class DequeIterator implements Iterator<Item> {
		 
		 private Node current = first;
		 
	        public boolean hasNext() {
	            return current != null;
	        }
	        public Item next() {
	            if (current == null) {
	                throw new java.util.NoSuchElementException();
	            }
	            Item item = current.item;
	            current = current.next;
	            return item;
	        }
	        public void remove() {
	            throw new java.lang.UnsupportedOperationException();
	        }
	    }
	
	public static void main(String[] args){
		
	}

}
