package puzzle;

import edu.princeton.cs.algs4.Stack;

public class Board {
	private int n;
	private int[] boardTiles;
	private int zero;
	private int hamming;
	private int manhattan;
	
    public Board(int[][] blocks){
    	// construct a board from an N-by-N array of blocks
    	// (where blocks[i][j] = block in row i, column j)
    	if(blocks == null) throw new java.lang.NullPointerException();
    	n = blocks.length;
    	
    	boardTiles = new int[n*n];
    	
    	int tile = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <n; j++) {
				if (blocks[i][j] == 0) zero = tile;
				boardTiles[tile++] = blocks[i][j];
			}
		}
    	
    	
    	
    }
                                           
    public int size() {
    	// board size N
    	return n;
    }
    public int hamming() {
    	// number of blocks out of place
    	if(hamming > 0) return hamming;
    	
    	  hamming = 0;
    	
    	for(int i = 0; i<boardTiles.length; i++){
    		if(boardTiles[i] != (i+1) && boardTiles[i] != 0)hamming++;
    	}
    	
    	return hamming;
    }
    public int manhattan()  {
    	// sum of Manhattan distances between blocks and goal
    	if(manhattan > 0) return manhattan;
    	
    	manhattan = 0;
    	for(int i = 0; i < boardTiles.length; i++){
    	if(boardTiles[i] == (i+1) || i == zero)continue;
    	manhattan += Math.abs((i/n) - ((boardTiles[i]-1))/n);
    	manhattan += Math.abs((i%n) - ((boardTiles[i]-1))%n);
    	}
    	    	
    	return manhattan;
    }
    public boolean isGoal()  {
    	// is this board the goal board?
    	if(boardTiles.length -1 != zero)return false;
    	for(int i = 0; i < boardTiles.length -1; i++){
    		if(boardTiles[i]!=(i+1)){
    			return false;
    		}
    	}
    	return true;
    }
    public boolean isSolvable()    {
    	// is this board solvable?
    	int inversions = 0;
    	//odd board
    	if(n%2 != 0){
    		for(int i = 0; i < boardTiles.length; i++){
    			if(boardTiles[i]==0)continue; //skip zero
    			for(int j =1; j<boardTiles.length; j++){
    				if(boardTiles[i] > boardTiles[j] && boardTiles[j] != 0){
    					inversions++;
    				}
    			}
    		}
    		if(inversions % 2 != 0)return false;
    	}
    	//even board
    	int inversions2 = 0;
    	if(n%2 == 0){
    		for(int i = 0; i < boardTiles.length; i++){
    			if(boardTiles[i]==0)continue; //skip zero
    			for(int j =1; j<boardTiles.length; j++){
    				if(boardTiles[i] > boardTiles[j] && boardTiles[j] != 0){
    					inversions2++;
    				}
    			}
    			
    		}
    		inversions2 += zero/n;
    		if(inversions2 %2 ==0 )return false;
    	}
    	return true;
    }
    
    public boolean equals(Object y)  {
    	// does this board equal y?
    	if(y==this)return true;
    	if(y==null)return false;
    	if(this.getClass() != y.getClass())return false;
    	Board other = (Board) y;
    	for (int i = 0; i < boardTiles.length; i++)
			if (this.boardTiles[i] != other.boardTiles[i]) return false;
		return true;
    	
    }
    
    public Iterable<Board> neighbors() {
    	// all neighboring boards
    	Stack<Board> boards = new Stack<>();
    	
    	if(zero/n != 0) pushToStack(boards, -n);// up neighbor
    	if(zero/n != n-1) pushToStack(boards, n);//down neighbor
    	if(zero%n != 0)pushToStack(boards, -1);//left neighbor
    	if(zero%n != n-1)pushToStack(boards, 1);//right neighbor
    	return boards;
    	
    }
    
    private void pushToStack(Stack<Board> board, int i){
    	swap(boardTiles, zero, zero+i);
    	board.push(new Board(boardTiles, n, zero +i));
    	swap(boardTiles, zero, zero+i);
    }
    
    private void swap(int[] board, int i, int j){
    	int swap = board[i];
    	board[i] = board[j];
    	board[j] = swap;
    }
    
    private Board(int[] block, int n, int zero){
    	this.n = n;
    	this.zero = zero;
    	boardTiles = new int[n*n];
    	System.arraycopy(block, 0, boardTiles, 0, boardTiles.length);
    }
    
    
    public String toString()   {
    	// string representation of this board (in the output format specified below)
    	StringBuilder s = new StringBuilder();
	    s.append(n + "\n");
	    for (int i = 0; i < boardTiles.length; i++) {
            s.append(String.format("%2d ", boardTiles[i]));
            if ((i + 1) % n == 0)
            	s.append("\n");
	    }
	    return s.toString();
    }
    
    	
  
    public static void main(String[] args){
    	// unit tests (not graded)

    }
}