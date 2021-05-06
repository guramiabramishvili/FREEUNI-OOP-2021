
// Test cases for CharGrid -- a few basic tests are provided.

import junit.framework.TestCase;

public class CharGridTest extends TestCase {
	
	public void testCharArea1() {
		char[][] grid = new char[][] {
				{'a', 'y', ' '},
				{'x', 'a', 'z'},
			};
		CharGrid cg = new CharGrid(grid);
				
		assertEquals(4, cg.charArea('a'));
		assertEquals(1, cg.charArea('z'));
	}
	
	
	public void testCharArea2() {
		char[][] grid = new char[][] {
				{'c', 'a', ' '},
				{'b', ' ', 'b'},
				{' ', ' ', 'a'}
			};
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(6, cg.charArea('a'));
		assertEquals(3, cg.charArea('b'));
		assertEquals(1, cg.charArea('c'));
	}

	public void testCharArea3() {
		char[][] grid = new char[][] {
				{'c', 'a', ' ',' ',' ','a','b'},
		};

		CharGrid cg = new CharGrid(grid);
		assertEquals(5, cg.charArea('a'));
		assertEquals(1, cg.charArea('b'));
		assertEquals(1, cg.charArea('c'));
	}

	public void testCharArea4() {
		char[][] grid = new char[][] {
				{'c'},
				{'b'},
				{'a'}
		};

		CharGrid cg = new CharGrid(grid);
		assertEquals(1, cg.charArea('a'));
		assertEquals(1, cg.charArea('b'));
		assertEquals(1, cg.charArea('c'));
	}

	public void testCharArea5() {
		char[][] grid = new char[][] {
				{'c', 'a', ' '},
				{'a', 'a', 'b'},
				{'b', ' ', 'a'}
		};

		CharGrid cg = new CharGrid(grid);
		assertEquals(9, cg.charArea('a'));
		assertEquals(6, cg.charArea('b'));
		assertEquals(1, cg.charArea('c'));
	}


	public void testCount1(){
		char[][] grid = new char[][] {
				{' ',' ','p',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ','p',' ',' ',' ',' ','x',' ',' '},
				{'p','p','p','p','p',' ','x','x','x',' '},
				{' ',' ','p',' ',' ',' ','y','x',' ',' '},
				{' ',' ','p',' ',' ','y','y','y',' ',' '},
				{'z','z','z','z','z',' ','y','z','z','z'},
				{'x','x',' ',' ',' ',' ','y',' ',' ',' '},
		};
		CharGrid cg = new CharGrid(grid);
		assertEquals(2, cg.countPlus());
	}

	public void testCount2(){
		char[][] grid = new char[][] {
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ','p',' ',' ',' ',' ','x',' ',' '},
				{'p','p','p','p','p',' ',' ','x','x',' '},
				{' ',' ','p',' ',' ',' ','y','x',' ',' '},
				{' ',' ','p',' ',' ',' ','y','y',' ',' '},
				{'z','z','z','z','z',' ','y','z','z','z'},
				{'x','x',' ',' ',' ',' ','y',' ',' ',' '},
		};
		CharGrid cg = new CharGrid(grid);
		assertEquals(0, cg.countPlus());
	}

	public void testCount3(){
		char[][] grid = new char[][] {
				{'p','p','p',' '},
				{'p','p','p',' '},
				{'p','p','p',' '},
				{' ',' ',' ',' '},
				{' ',' ',' ',' '},
		};
		CharGrid cg = new CharGrid(grid);
		assertEquals(1, cg.countPlus());
	}

	public void testCount4(){
		char[][] grid = new char[][] {
				{' ',' ','p','p',' ',' ',' ',' ',' ',' '},
				{' ',' ','p','p','p',' ',' ','x',' ',' '},
				{'p','p','p','p','p',' ','x','x','x',' '},
				{' ',' ','p',' ',' ',' ','y','x',' ',' '},
				{' ',' ','p',' ',' ','y','y','y',' ',' '},
				{'z','z','z','z','z',' ','y','z','z','z'},
				{'x','x',' ',' ',' ',' ','y',' ',' ',' '},
		};
		CharGrid cg = new CharGrid(grid);
		assertEquals(3, cg.countPlus());
	}

	public void testCount5(){
		char[][] grid = new char[][] {
				{'y','y','y','y','y','y','y'},
		};
		CharGrid cg = new CharGrid(grid);
		assertEquals(0, cg.countPlus());
	}

	public void testCount6(){
		char[][] grid = new char[][] {
				{'y'},
				{'y'},
				{'y'},
				{'y'},
		};
		CharGrid cg = new CharGrid(grid);
		assertEquals(0, cg.countPlus());
	}

	public void testCount7(){
		char[][] grid = new char[][] {
				{'a'},
				{'y'},
				{'y'},
				{'y'},
		};
		CharGrid cg = new CharGrid(grid);
		assertEquals(1, cg.countPlus());
	}
	
	
}
