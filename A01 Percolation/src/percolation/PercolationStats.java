package percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
	
	int x;
	double[] avgs;
	
	public PercolationStats(int N, int T){
		
		if(N<=0 || T<=0){
			throw new java.lang.IllegalArgumentException("No negatives allowed");
		}
		this.x=T;
		avgs=getStats(N,T);
		
	}
	
	private double[] getStats(int n, int t) {
		double[] count = new double[t];
		
		for(int i =0; i<t; i++){
			Percolation p = new Percolation(n);
			int c = 0;
			
			while(!p.percolates()){
				int row = StdRandom.uniform(n);
				int column = StdRandom.uniform(n);
				if(!p.isOpen(row, column)){
					p.open(row, column);
					c++;
				}
			}
			count[i]=((double) c)/(n*n);
		}
		
		return count;
	}

	public double mean(){
		
		return StdStats.mean(avgs);
		
	}
	
	public double stddev(){
		
		return StdStats.stddev(avgs);
	}
	
	public double confidenceLow(){
		
		return mean() - StdStats.stddev(avgs);
	}
	
	public double confidenceHigh(){
		
		return mean() + StdStats.stddev(avgs);
	}
	
	public static void main(String[] args){
		
		PercolationStats test = new PercolationStats(200,100);
		
		StdOut.println("Test Results: ");
		StdOut.println();
		StdOut.printf("%-20s %.10f\n", "mean():", test.mean());
		StdOut.printf("%-20s %.10f\n", "stddev():", test.stddev());
		StdOut.printf("%-20s %.10f\n", "confidenceLow():", test.confidenceLow());
		StdOut.printf("%-20s %.10f\n", "confidenceHigh():", test.confidenceHigh());
	}

}
