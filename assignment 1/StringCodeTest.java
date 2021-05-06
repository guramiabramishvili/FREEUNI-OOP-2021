// StringCodeTest
// Some test code is provided for the early HW1 problems,
// and much is left for you to add.

import junit.framework.TestCase;

public class StringCodeTest extends TestCase {
	//
	// blowup
	//
	public void testBlowup1() {
		// basic cases
		assertEquals("xxaaaabb", StringCode.blowup("xx3abb"));
		assertEquals("xxxZZZZ", StringCode.blowup("2x3Z"));
		assertEquals("aaaaaaaabbb", StringCode.blowup("7a2b"));
	}
	
	public void testBlowup2() {
		// things with digits
		assertEquals("voodoo1", StringCode.blowup("v1od1o11"));

		// digit at end
		assertEquals("axxx", StringCode.blowup("a2x3"));
		
		// digits next to each other
		assertEquals("a33111", StringCode.blowup("a231"));
		
		// try a 0
		assertEquals("aabb", StringCode.blowup("aa0bb"));
	}
	
	public void testBlowup3() {
		// weird chars, empty string
		assertEquals("AB&&,- ab", StringCode.blowup("AB&&,- ab"));
		assertEquals("", StringCode.blowup(""));
		
		// string with only digits
		assertEquals("", StringCode.blowup("2"));
		assertEquals("33", StringCode.blowup("23"));
	}
	
	
	//
	// maxRun
	//
	public void testRun1() {
		assertEquals(2, StringCode.maxRun("hoopla"));
		assertEquals(3, StringCode.maxRun("hoopllla"));
	}
	
	public void testRun2() {
		assertEquals(3, StringCode.maxRun("abbcccddbbbxx"));
		assertEquals(0, StringCode.maxRun(""));
		assertEquals(3, StringCode.maxRun("hhhooppoo"));
	}
	
	public void testRun3() {
		// "evolve" technique -- make a series of test cases
		// where each is change from the one above.
		assertEquals(1, StringCode.maxRun("123"));
		assertEquals(2, StringCode.maxRun("1223"));
		assertEquals(2, StringCode.maxRun("112233"));
		assertEquals(3, StringCode.maxRun("1112233"));
	}

	// Need test cases for stringIntersect
	public void testIntersect1(){
		assertTrue(StringCode.stringIntersect("abc","cbc",2));
		assertFalse(StringCode.stringIntersect("abcdef","acbdfe",2));
		assertTrue(StringCode.stringIntersect("abcd%fgh","acbd%fgh",2));
		assertTrue(StringCode.stringIntersect("ab defgh","ab dffgh",4));
		assertFalse(StringCode.stringIntersect("abcdefgh12","ac5bd5eg5f1h2",2));
		assertFalse(StringCode.stringIntersect("abcdefgh12","ac5bd5eg5f1h2",13));
		assertFalse(StringCode.stringIntersect("123456789","123456789",10));
	}

}
