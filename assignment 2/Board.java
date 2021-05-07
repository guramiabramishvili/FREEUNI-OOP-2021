import java.util.Arrays;
import junit.framework.TestCase;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 CS108 Tetris Board.
 Represents a Tetris board -- essentially a 2-d grid
 of booleans. Supports tetris pieces and row clearing.
 Has an "undo" feature that allows clients to add and remove pieces efficiently.
 Does not do any drawing or have any idea of pixels. Instead,
 just represents the abstract 2-d board.
 */
public class Board	{
	// Some ivars are stubbed out for you:
	private int width;
	private int height;
	private boolean[][] grid;
	private boolean DEBUG = true;

	private int []widths;
	private int []heights;
	private int maxHeight;

	private int []xWidths;
	private int []xHeights;
	private int xMaxHeight;
	private boolean[][] xGrid;
	boolean committed;

	// Here a few trivial methods are provided:

	/**
	 Creates an empty board of the given width and height
	 measured in blocks.
	 */
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new boolean[width][height];

		widths = new int[height];
		heights = new int[width];
		maxHeight = 0;

		xWidths = new int[height];
		xMaxHeight = 0;
		xHeights = new int[width];
		xGrid = new boolean[width][height];
		committed = true;


		// YOUR CODE HERE
	}


	/**
	 Returns the width of the board in blocks.
	 */
	public int getWidth() {
		return width;
	}


	/**
	 Returns the height of the board in blocks.
	 */
	public int getHeight() {
		return height;
	}


	/**
	 Returns the max column height present in the board.
	 For an empty board this is 0.
	 */
	public int getMaxHeight() {
		return maxHeight; // YOUR CODE HERE
	}


	/**
	 Checks the board for internal consistency -- used
	 for debugging.
	 */

	public void sanityCheck() {
		if (DEBUG) {
			int[] newWidths = new int[height];
			int[] newHeights = new int[width];
			int newMaxHeight = 0;
			for(int x=0; x<grid.length; x++){
				for(int y=0; y<grid[x].length; y++){
					if(grid[x][y]) {
						newWidths[y]++;
						if(y >= newHeights[x]){
							newHeights[x] = y + 1;
							if(newMaxHeight <= y) newMaxHeight = y + 1;
						}
					}
				}
			}
			assertEquals(maxHeight,newMaxHeight);
			assertTrue(Arrays.equals(widths,newWidths));
			assertTrue(Arrays.equals(heights,newHeights));
		}
	}

	/**
	 Given a piece and an x, returns the y
	 value where the piece would come to rest
	 if it were dropped straight down at that x.
	 <p>
	 Implementation: use the skirt and the col heights
	 to compute this fast -- O(skirt length).
	 */

	public int dropHeight(Piece piece, int x) {
		int dropHeight = 0;
		for(int i=0; i<piece.getWidth(); i++){
			int actualHeight = heights[x+i] - piece.getSkirt()[i];
			if(dropHeight < actualHeight && actualHeight > 0)
				dropHeight = actualHeight;
		}
		return dropHeight;
	}


	/**
	 Returns the height of the given column --
	 i.e. the y value of the highest block + 1.
	 The height is 0 if the column contains no blocks.
	 */
	public int getColumnHeight(int x) {
		return heights[x];
	}


	/**
	 Returns the number of filled blocks in
	 the given row.
	 */
	public int getRowWidth(int y) {
		return widths[y];
	}


	/**
	 Returns true if the given block is filled in the board.
	 Blocks outside of the valid width/height area
	 always return true.
	 */
	public boolean getGrid(int x, int y) {
		return x>= width || y>=height || x <0 || y <0 || grid[x][y]; // YOUR CODE HERE
	}


	public static final int PLACE_OK = 0;
	public static final int PLACE_ROW_FILLED = 1;
	public static final int PLACE_OUT_BOUNDS = 2;
	public static final int PLACE_BAD = 3;



	private void backup(){
		for(int i=0; i<width; i++) {
			System.arraycopy(grid[i], 0, xGrid[i], 0, height);
		}
		System.arraycopy(widths,0,xWidths,0,height);
		System.arraycopy(heights,0,xHeights,0,width);
		xMaxHeight = maxHeight;
	}

	/**
	 Attempts to add the body of a piece to the board.
	 Copies the piece blocks into the board grid.
	 Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
	 for a regular placement that causes at least one row to be filled.
	 <p>Error cases:
	 A placement may fail in two ways. First, if part of the piece may falls out
	 of bounds of the board, PLACE_OUT_BOUNDS is returned.
	 Or the placement may collide with existing blocks in the grid
	 in which case PLACE_BAD is returned.
	 In both error cases, the board may be left in an invalid
	 state. The client can use undo(), to recover the valid, pre-place state.
	 */
	public int place(Piece piece, int x, int y) {
		// flag !committed problem
		if (!committed) throw new RuntimeException("place commit problem");
		committed = false;
		backup();
		int row,col = 0;
		int result = PLACE_OK;
		for(int i=0; i<piece.getBody().length; i++){
			row = piece.getBody()[i].y + y;
			col = piece.getBody()[i].x + x;
			if(col >= width || row >= height || col < 0 || row < 0){
				result = PLACE_OUT_BOUNDS;
				break;
			}

			if(grid[col][row] == true){
				result = PLACE_BAD;
				break;
			}

			widths[row]++;
			if(row >= heights[col]) heights[col] = row + 1;

			if(widths[row] == width) result = PLACE_ROW_FILLED;
			if(heights[col] > maxHeight) maxHeight = heights[col];
			grid[col][row] = true;
		}

		committed = false;
		return result;
	}

	/**
	 Deletes rows that are filled all the way across, moving
	 things above down. Returns the number of rows cleared.
	 */
	public int clearRows() {
	    if(committed == true) committed = false;
		//backup();
		int rowsCleared = 0;
		boolean wasCleared = false;
		int to = 0;
		for(int i=0; i<height; i++){
			if(widths[i] == width){
				rowsCleared++;
				maxHeight--;
				widths[i] = 0;
				wasCleared = true;
				continue;
			}
			if(wasCleared){
				widths[to] = widths[i];
				for(int j=0; j<width; j++){
					grid[j][to] = grid[j][i];
				}
			}
			to++;
		}

		for(int i=0; i<width; i++){
			int ht = 0;
			for(int j=0; j<maxHeight; j++){
				if(grid[i][j])
					ht = j + 1;
			}
			heights[i] = ht;
		}

		sanityCheck();
		return rowsCleared;
	}

	/**
	 Reverts the board to its state before up to one place
	 and one clearRows();
	 If the conditions for undo() are not met, such as
	 calling undo() twice in a row, then the second undo() does nothing.
	 See the overview docs.
	 */


	public void undo() {
		if(committed == false) {

			int[] temp0 = widths;
			widths = xWidths;
			xWidths = temp0;


			int[] temp1 = heights;
			heights = xHeights;
			xHeights = temp1;


			boolean[][] temp2 = grid;
			grid = xGrid;
			xGrid = temp2;

			maxHeight = xMaxHeight;
			commit();
		}
	}


	/**
	 Puts the board in the committed state.
	 */
	public void commit() {
		committed = true;
	}



	/*
	 Renders the board state as a big String, suitable for printing.
	 This is the sort of print-obj-state utility that can help see complex
	 state change over time.
	 (provided debugging utility)
	 */
	public String toString() {
		StringBuilder buff = new StringBuilder();
		for (int y = height-1; y>=0; y--) {
			buff.append('|');
			for (int x=0; x<width; x++) {
				if (getGrid(x,y)) buff.append('+');
				else buff.append(' ');
			}
			buff.append("|\n");
		}
		for (int x=0; x<width+2; x++) buff.append('-');
		return(buff.toString());
	}
}