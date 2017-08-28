package kdtrees;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PointST<Value> {
	
	private RedBlackBST<Point2D, Value> st;
	
	   public PointST() {
		   // construct an empty symbol table of points 
		   st = new RedBlackBST<>();
	   }
	   public boolean isEmpty(){   
		   // is the symbol table empty? 
		   return size() ==0;
		   
	   }
	   public int size() {  
		   // number of points 
		   return st.size();
	   }
	   public void put(Point2D p, Value val){ 
		   // associate the value val with point p
		   if(p==null)throw new java.lang.NullPointerException();
		    st.put(p, val);
	   }
	   public Value get(Point2D p) { 
		   // value associated with point p 
		   if (p == null) throw new NullPointerException("called get() with null point");
			return st.get(p);
	   }
	   public boolean contains(Point2D p){
		   // does the symbol table contain point p? 
		   if (p == null) throw new NullPointerException("called contains() with null point");
			return st.contains(p);
	   }
	   public Iterable<Point2D> points(){  
		   // all points in the symbol table 
		   return st.keys();
	   }
	   public Iterable<Point2D> range(RectHV rect){
		   
		   // all points that are inside the rectangle 
		   if (rect == null) throw new NullPointerException("called range() with null rectangle");
			Queue<Point2D> que = new Queue<>();
			for (Point2D p : st.keys()) {
				if (rect.contains(p))
					que.enqueue(p);
			}
			return que;
	   }
	   public Point2D nearest(Point2D p){    
		   // a nearest neighbor to point p; null if the symbol table is empty 
		   if (p == null) throw new NullPointerException("called nearest() with null point");
			Point2D nearest = st.max();
			for (Point2D point : st.keys()) {
				if (p.distanceSquaredTo(point) < p.distanceSquaredTo(nearest)) {
					nearest = point;
				}
			}
			return nearest;
		   
	   }
	   public static void main(String[] args) {
			String filename = "src/one.txt";
	        In in = new In(filename);
	        PointST<Integer> brute = new PointST<>();
	        for (int i = 0; !in.isEmpty(); i++) {
	            double x = in.readDouble();
	            double y = in.readDouble();
	            Point2D p = new Point2D(x, y);
	            brute.put(p, i);
	        }
	        StdOut.println("Starting");
			long starttime = System.currentTimeMillis();
			int numberOfTimesToCalculate = 500;
			for (int i = 0; i < numberOfTimesToCalculate; i++) {
				brute.nearest(new Point2D(StdRandom.uniform(), StdRandom.uniform()));
			}
			long endtime = System.currentTimeMillis();
			double timeTakenInSeconds = (endtime-starttime)/1000.0;
			StdOut.println("Total time: " + timeTakenInSeconds);
			StdOut.println("Average per second: " + numberOfTimesToCalculate / timeTakenInSeconds);
		}
	}
