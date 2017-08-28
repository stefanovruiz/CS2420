package puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	
	private Stack<Board> solutionBoard;
	private int moves;
	
    public Solver(Board initial){
    	// find a solution to the initial board (using the A* algorithm)
    	if(initial.isSolvable() == false) throw new java.lang.IllegalArgumentException();
    	solutionBoard = new Stack<>();
    	
    	MinPQ<Move>	q = new MinPQ<>();
    	q.insert(new Move(initial, 0, null));
		while(true){
			Move move = q.delMin();
			if(move.board.isGoal()){ //goal has been reached, populate fields of interest and return
				this.moves = move.moves;
				do{
					solutionBoard.push(move.board);
					move = move.parent;
				}
				while(move != null);
				return; //done solving
			}
			for(Board next : move.board.neighbors()){
				if(move.parent == null || !next.equals(move.parent.board)) //look back one move to prevent useless looping
					q.insert(new Move(next, move.moves+1, move));
			}
		}
    }
    
    private class Move implements Comparable<Move>{
    	
    	private Board board;
    	private int moves;
    	private Move parent;
    	
    	public Move(Board b, int m, Move p){
    		this.board = b;
    		this.moves = m;
    		this.parent = p;
    	}

		@Override
		public int compareTo(Move arg0) {
			int difference = this.board.manhattan() + this.moves - arg0.board.manhattan() - arg0.moves;
			if (difference != 0) return difference; //return normal difference of priority functions
			if (this.moves > arg0.moves) return -1; //if priority is the same give preference to the one with more moves
			return 1;
		}
    	
    }
    
    public int moves(){
		return moves;
    	// min number of moves to solve initial board
    }
    
    public Iterable<Board> solution(){
		return solutionBoard;
    	// sequence of boards in a shortest solution
    }
    
    public static void main(String[] args) {

        // create initial board from file
        In in = new In("src/puzzle.txt");
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // check if puzzle is solvable; if so, solve it and output solution
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

        // if not, report unsolvable
        else {
            StdOut.println("Unsolvable puzzle");
        }
    }
}