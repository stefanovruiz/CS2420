package midterm1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class CityApp {
	

	
	public static void main(String[] agrs) {
		City[] cities = {
				new City("Chicago", 2715000, 5498),
				new City("Denver", 634000, 1292),
				new City("New York", 8337000, 8683),
				new City("San Francisco", 826000, 1365),
				new City("Seattle", 635000, 2470)
		};
		

	
		// Part 1		
		StdOut.println("Cities: ");
		for(City c : cities) {
			StdOut.println(c);
		}
        // TODO part 1
		StdOut.println();
		StdOut.println("Cities sorted by population density:");
		sort(cities);
		for(City c : cities) {
			StdOut.println(c);
		}
		
		
		// Part 2
		// TODO part 2
		Queue q = new Queue();
		for(City c: cities){
			q.enqueue(c);
		}
		StdOut.println(c);
		
		
	}
	
	public class Queue<Item> implements Iterable<Item>{
		private Node<Item> first;
		private Node<Item> last;
		private int n;
		
		
		public Queue(){
			first = null;
			last = null;
			n = 0;
		}
		
		public void enqueue(Item item){
			Node<Item> oldlast = last;
	        last = new Node<Item>();
	        last.item = item;
	        last.next = null;
	        if (isEmpty()){
	        	first = last;
	        }
	        else {
	        	oldlast.next = last;
	        }
	        n++;
		}
		
		
	}
	
	
	public static City[] sort(City[] arr){
		int n = arr.length;
		for(int i=0; i<n; i++){
			int min = i;
		
		for(int j=i+1; j<n; j++){
			if(less( arr[j] , arr[min])){
				min = j;
				
				City small = arr[i];
				arr[i]=arr[j];
				arr[j]=small;
			}
		}
	}
		return arr;
}
	public static boolean less(  City v, City w){
		return v.compareTo(w) < 0;
	}
}
