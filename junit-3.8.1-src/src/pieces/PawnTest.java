package pieces;

import junit.framework.*;
public class PawnTest extends TestCase {
	public void testCreate() {
		int white = 0;
		Pawn p = new Pawn(white);
		Pawn p2 = p;
		assertTrue(p2.isWhite());
		int black = 1;
		Pawn pawn = new Pawn(black );
		assertTrue(p.equals(p2));
		assertFalse(p.equals(pawn));		
	}
	public void testToString() {
		Pawn p = new Pawn(Pawn.black);
		assertEquals("P", p.toString());
		Pawn p2 = new Pawn(Pawn.white);
		assertEquals("p", p2.toString());
	}
}
