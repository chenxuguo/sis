package pieces;

import junit.framework.*;
public class KingTest extends TestCase{
	public void testColor() {
		King blackKing = King.createBlackKing();
		assertTrue(blackKing.isBlack());
		assertEquals('K',blackKing.getRepresentation());
		King whiteKing = King.createWhiteKing();
		assertTrue(whiteKing.isWhite());
		assertEquals('k',whiteKing.getRepresentation());
	}
}
