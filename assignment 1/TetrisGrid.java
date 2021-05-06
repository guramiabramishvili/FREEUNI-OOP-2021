//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.

public class TetrisGrid {
	private boolean[][] grid;
	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public TetrisGrid(boolean[][] grid) {
		this.grid = grid;
	}
	
	
	/**
	 * Does row-clearing on the grid (see handout).
	 */
	public void clearRows() {
		int shouldWeDelete = 0;
		for(int j=0; j< grid[0].length; j++){

			if(shouldWeDelete == 0) {
				for (int i = 0; i < grid.length; i++) {
					if (grid[i][j] == false) {
						break;
					}
					if (i == grid.length - 1) {
						shouldWeDelete = 1;
						break;
					}
				}
			}
			if(shouldWeDelete == 1) {
				for(int y = j; y<grid[0].length; y++) {
					for (int x = 0; x < grid.length; x++) {
						if (y != grid[0].length - 1) {
							grid[x][y] = grid[x][y + 1];
						} else grid[x][y] = false;
					}
				}
				shouldWeDelete = 0;
			}

		}

	}
	
	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	boolean[][] getGrid() {
		return grid; // YOUR CODE HERE
	}
}
