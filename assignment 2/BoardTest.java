import junit.framework.TestCase;

import java.util.Arrays;


public class BoardTest extends TestCase {
	Board a,b,c,d,e,f;
	Piece pyr1, pyr2, pyr3, pyr4, s, sRotated, L1, L12, L2, S1, S2, Square, Stick;
	int [] heights;
	boolean hole;
	// This shows how to build things in setUp() to re-use
	// across tests.

	// In this case, setUp() makes shapes,
	// and also a 3X6 board, with pyr placed at the bottom,
	// ready to be used by tests.

	protected void setUp() throws Exception {
		b = new Board(3, 6);

		L1 = new Piece(Piece.L1_STR);
		L2 = new Piece(Piece.L2_STR);
		S1 = new Piece(Piece.S1_STR);
		S2 = new Piece(Piece.S2_STR);
		Square = new Piece(Piece.SQUARE_STR);
		Stick = new Piece(Piece.STICK_STR);

		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();

		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();

		b.place(pyr1, 0, 0);

		//test3
		a = new Board(5,5);
		L12 = L1.computeNextRotation().computeNextRotation();
		//test4
		c = new Board(8,8);
		//test5
		d = new Board(8,8);
		hole = false;
		heights = new int[d.getWidth()];
		//test6
		e = new Board(8,8);
		f = new Board(8,8);
	}

	public static final int PLACE_OK = 0;
	public static final int PLACE_ROW_FILLED = 1;
	public static final int PLACE_OUT_BOUNDS = 2;
	public static final int PLACE_BAD = 3;


	// Check the basic width/height/max after the one placement
	public void testSample1() {
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(2, b.getColumnHeight(1));
		assertEquals(2, b.getMaxHeight());
		assertEquals(3, b.getRowWidth(0));
		assertEquals(1, b.getRowWidth(1));
		assertEquals(0, b.getRowWidth(2));
	}

	// Place sRotated into the board, then check some measures
	public void testSample2() {
		b.commit();
		int result = b.place(sRotated, 1, 1);
		assertEquals(Board.PLACE_OK, result);
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(4, b.getColumnHeight(1));
		assertEquals(3, b.getColumnHeight(2));
		assertEquals(4, b.getMaxHeight());
	}

	// Makre  more tests, by putting together longer series of
	// place, clearRows, undo, place ... checking a few col/row/max
	// numbers that the board looks right after the operations.

	//test place results
	public void testSample3(){
		int result = a.place(Square,5,5);
		assertEquals(a.getHeight(),5);
		assertEquals(a.getWidth(), 5);
		assertTrue(a.getGrid(5,5));
		assertEquals(result,PLACE_OUT_BOUNDS);
		a.commit();
		result = a.place(Square,0,0);
		assertTrue(a.getGrid(0,0));
		assertEquals(result,PLACE_OK);
		a.commit();
		result = a.place(Square, 1,1);
		assertEquals(result,PLACE_BAD);
		a.commit();
		result = a.place(Stick,3,1);
		assertEquals(result,PLACE_OK);
		a.commit();
		a.place(Stick,4,0);
		a.commit();
		result = a.place(L12,1,0);
		assertEquals(result,PLACE_ROW_FILLED);
	}

	//test incorrect place + undo + place + clearrows
	public void testSample4(){
		c.place(Stick.computeNextRotation(),0,0);
		assertEquals(c.getRowWidth(0),4);
		c.commit();
		c.place(pyr3,1,1);
		assertEquals(c.getMaxHeight(),3);
		c.commit();
		assertEquals(c.place(Stick,1,1),PLACE_BAD);
		//one piece of incorrectly placed stick is still on grid
		assertEquals(c.getRowWidth(1),2);
		c.undo();
		assertEquals(c.getRowWidth(1),1);
		assertEquals(c.place(Stick.computeNextRotation(),4,0),PLACE_ROW_FILLED);
		c.clearRows();
		assertEquals(c.getMaxHeight(),2);
	}

	//test place + clearrows = holes
	public void testSample5(){

		d.place(pyr1,0,0);
		d.commit();
		d.place(S1,2,1);
		d.commit();
		d.place(Stick.computeNextRotation(),0,3);
		d.commit();
		assertEquals(d.place(Stick.computeNextRotation(),4,3),PLACE_ROW_FILLED);
		for(int i=0; i<d.getWidth(); i++){
			heights[i] = d.getColumnHeight(i);
			assertEquals(heights[i],d.getMaxHeight());
			if(d.getWidth() - i >= pyr1.getWidth()) assertEquals(d.dropHeight(pyr1,i),d.getMaxHeight());
		}
		d.clearRows();
		for(int i=0; i<d.getWidth(); i++){
			hole = hole || (d.getColumnHeight(i) < (heights[i] - 1));
		}
		assertTrue(hole);
	}

	//test toString
	public void testSample6(){
		e.place(pyr1,0,0);
		e.commit();
		e.place(S1,2,1);
		e.commit();
		f.place(pyr1,0,0);
		f.commit();
		f.place(S1,2,1);
		f.commit();
		f.place(Stick.computeNextRotation(),0,3);
		f.commit();
		f.place(Stick.computeNextRotation(),4,3);
		f.clearRows();
		assertTrue(e.toString().equals(f.toString()));
	}

}
