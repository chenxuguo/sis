package pieces;

import junit.framework.*;
public class BishopTest extends TestCase {
	public void testCreate() {
		int white = 0;
		Bishop p = new Bishop(white );
		Bishop p2 = p;
		assertTrue(p2.isWhite());
	}
	public void testToString() {
		Bishop p = new Bishop(Piece.black);
		assertEquals('B', p.getRepresentation());
		Bishop p2 = new Bishop(Piece.white);
		assertEquals('b', p2.getRepresentation());
	}

}
