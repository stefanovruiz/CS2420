package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	int n;
	int size;
	int topRow;
	int bottomRow;
	boolean[][] grid;
	WeightedQuickUnionUF unionFind;
	WeightedQuickUnionUF unionFind2;
	int numberOpen = 0;
	
	public Percolation(int N){
		
		
		
		if(n<0){
			throw new IllegalArgumentException("No negative numbers allowed");
		}
		
		this.n=N;
		size=n*n;
		
		grid = new boolean[N][N];
		
		unionFind = new WeightedQuickUnionUF((size+2));
		unionFind2 = new WeightedQuickUnionUF((size+1));
		topRow=size;
		bottomRow=size+1;
		
		for (int i = 0; i < n; i++)
		{
		    unionFind.union(i, topRow);
		    unionFind2.union(i, topRow);
		    unionFind.union(size - i - 1, bottomRow);
		}
		
		
	}
	
	public void open(int i, int j){
		
		if (i > n - 1 || i < 0 || j > n - 1 || j < 0)
	    {
		throw new IndexOutOfBoundsException("Index out of bounds");
	    }
	    grid[i][j] = true;
	    if (i - 1 >= 0 && grid[i - 1][j])
	    {
		unionFind.union(location(i - 1, j), location(i, j));
		unionFind2.union(location(i - 1, j), location(i, j));
	    }
	    if (i + 1 < n && grid[i + 1][j])
	    {
		unionFind.union(location(i + 1, j), location(i, j));
		unionFind2.union(location(i + 1, j), location(i, j));
	    }
	    if (j - 1 >= 0 && grid[i][j - 1])
	    {
		unionFind.union(location(i, j - 1), location(i, j));
		unionFind2.union(location(i, j - 1), location(i, j));
	    }
	    if (j + 1 < n && grid[i][j + 1])
	    {
		unionFind.union(location(i, j + 1), location(i, j));
		unionFind2.union(location(i, j + 1), location(i, j));
	    }
	    numberOpen++;
	}
	

	public boolean isOpen(int i, int j){

		 if (i > n - 1 || i < 0 || j > n - 1 || j < 0)
		    {
			throw new IndexOutOfBoundsException("Index out of bounds!");
		    }
		    return grid[i][j];
		
	}
	
	public boolean isFull(int i, int j){
		
		if (i > n - 1 || i < 0 || j > n - 1 || j < 0)
	    {
		throw new IndexOutOfBoundsException("Index out of bounds!");
	    }
	    if (!isOpen(i, j))
	    {
		return false;
	    }
	    return unionFind2.connected(topRow, location(i, j));
		
	}
	
	public boolean percolates(){

		return unionFind.connected(topRow, bottomRow);
		
	}
	
	private int location(int i, int j) {

		return (n * i)+j;
	}

	public String numberOfOpenSites() {
		return ""+numberOpen;
	}


}
