import junit.framework.TestCase;
import java.util.*;

public class TetrisGridTest extends TestCase {

	/*
	public void printArr(boolean[][] arr){
		for(int i=0; i<arr[0].length; i++){
			for(int j=0; j<arr.length; j++){
				System.out.print(arr[j][i]);
			}
			System.out.println("");
		}
	}*/
	// Provided simple clearRows() test
	// width 2, height 3 grid
	public void testClear1() {
		boolean[][] before =
		{	
			{true, true, false, },
			{false, true, true, }
		};
		
		boolean[][] after =
		{	
			{true, false, false},
			{false, true, false}
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}

	public void testClear2() {
		boolean[][] before =
				{
						{true, true, false, true, false, },
						{false, true, false, true, true, }
				};

		boolean[][] after =
				{
						{true, false, false, false, false,},
						{false, false, true, false, false,}
				};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();
		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}

	public void testClear3() {
		boolean[][] before =
				{
						{true,},
						{true,}
				};

		boolean[][] after =
				{
						{false,},
						{false,}
				};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();
		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}

	public void testClear4() {
		boolean[][] before =
				{
						{false,},
						{false,}
				};

		boolean[][] after =
				{
						{false,},
						{false,}
				};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();
		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}

	public void testClear5() {
		boolean[][] before =
				{
						{ },
						{ }
				};

		boolean[][] after =
				{
						{},
						{}
				};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();
		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
}
