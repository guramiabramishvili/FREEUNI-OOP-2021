import junit.framework.TestCase;

import java.util.*;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest extends TestCase {
	// You can create data to be used in the your
	// test cases like this. For each run of a test method,
	// a new PieceTest object is created and setUp() is called
	// automatically by JUnit.
	// For example, the code below sets up some
	// pyramid and s pieces in instance variables
	// that can be used in tests.
	private Piece pyr1, pyr2, pyr3, pyr4,L1,L2,S1,S2,Square,Stick;
	private Piece s, sRotated;
	private Piece[] pieces;
	protected void setUp() throws Exception {
		super.setUp();

		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();

		pieces = Piece.getPieces();

		L1 = new Piece(Piece.L1_STR);
		L2 = new Piece(Piece.L2_STR);
		S1 = new Piece(Piece.S1_STR);
		S2 = new Piece(Piece.S2_STR);
		Square = new Piece(Piece.SQUARE_STR);
		Stick = new Piece(Piece.STICK_STR);

		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();
	}

	// Here are some sample tests to get you started

	public void testSampleSize() {
		// Check size of pyr piece
		assertEquals(3, pyr1.getWidth());
		assertEquals(2, pyr1.getHeight());

		// Now try after rotation
		// Effectively we're testing size and rotation code here
		assertEquals(2, pyr2.getWidth());
		assertEquals(3, pyr2.getHeight());

		// Now try with some other piece, made a different way
		Piece l = new Piece(Piece.STICK_STR);
		assertEquals(1, l.getWidth());
		assertEquals(4, l.getHeight());
	}


	// Test the skirt returned by a few pieces
	public void testSampleSkirt() {
		// Note must use assertTrue(Arrays.equals(... as plain .equals does not work
		//
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));

		assertTrue(Arrays.equals(new int[] {0, 0, 1}, s.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0}, sRotated.getSkirt()));
	}

	//Test rotations
	public void test1(){
		assertEquals(L1.computeNextRotation().getWidth(),3);
		assertEquals(S1.computeNextRotation().getWidth(),2);
		assertEquals(pieces[0].fastRotation(),Stick.computeNextRotation());
	}


	//Test dimensions
	public void test2(){
		assertEquals(L1.getWidth(), 2);
		assertEquals(L1.getHeight(), 3);
		assertEquals(S1.getWidth(), 3);
		assertEquals(S1.getHeight(), 2);
		assertEquals(Square.getHeight(),Square.getWidth());
		assertEquals(Stick.getHeight(),Stick.computeNextRotation().getWidth());
		assertEquals(Stick.getWidth(),Stick.computeNextRotation().getHeight());
	}

	//Test equal
	public void test3(){
		Piece L1 = pieces[1].fastRotation();
		Piece L2 = L1.fastRotation();
		Piece L3 = L2.fastRotation();
		Piece L4 = L3.fastRotation();
		Piece L5 = L4.fastRotation();
		assertTrue(L5.equals(L1));
		assertTrue(L5 == L1);
		assertTrue(Arrays.equals(L1.getBody(),L5.getBody()));
		L5 = L4.computeNextRotation();
		assertTrue(L5.equals(L1));
		assertFalse(L5 == L1);
	}


	public void test4(){
		assertTrue(Arrays.equals(L1.getSkirt(),L2.getSkirt()));
		assertTrue(Arrays.equals(Square.getSkirt(),Square.computeNextRotation().getSkirt()));
	}

}
