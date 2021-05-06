// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.



public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}


	
	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
		int minI = grid.length;
		int minJ = grid[0].length;
		int maxI = 0;
		int maxJ = 0;

		int atLeastOne = 0;
		for(int i=0; i< grid.length; i++){
			for(int j=0; j<grid[i].length; j++){
				if(grid[i][j] == ch){
					if(minI > i) minI = i;
					if(minJ > j) minJ = j;
					if(maxI < i) maxI = i;
					if(maxJ < j) maxJ = j;
					atLeastOne = 1;
				}
			}
		}
		if(atLeastOne == 0) return 0;
		return (maxI-minI + 1)*(maxJ-minJ + 1); // YOUR CODE HERE
	}


	public int charsInDirection(int I,int J,int directionI,int directionJ){
		int nextI = I + directionI;
		int nextJ = J + directionJ;
		if(nextI < 0 || nextI >= grid.length || nextJ < 0 || nextJ >= grid[0].length) return 0;
		if(grid[I][J] == grid[nextI][nextJ]) return 1 + charsInDirection(nextI,nextJ,directionI,directionJ);
		return 0;
	}
	
	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */
	public int countPlus() {
		int pluses = 0;
		for(int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if(grid[i][j] == ' ') continue;
				else{
					int right = charsInDirection(i,j,0,1);
					int left = charsInDirection(i,j,0,-1);
					int up = charsInDirection(i,j,1,0);
					int down = charsInDirection(i,j,-1,0);
					if( up == down && right == left && right == up) pluses++;
				}
			}
		}

		return pluses; // YOUR CODE HERE
	}
	
}
