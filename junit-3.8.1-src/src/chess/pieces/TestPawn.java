package chess.pieces;

public class TestPawn extends junit.framework.TestCase {
	public void testCreate() {
		Piece firstPawn = new Piece(Piece.Type.PAWN,Piece.Color.BLACK, Piece.PAWN_REPRESENTATION);
		assertEquals(Piece.Color.BLACK, firstPawn.getColor());
		Piece pawn = new Piece(Piece.Type.PAWN,Piece.Color.BLACK, Piece.PAWN_REPRESENTATION);
		assertEquals(Piece.Color.BLACK, pawn.getColor());
	}
	public void testPawnString() {
		Piece pawn = new Piece(Piece.Type.PAWN,Piece.Color.BLACK, Piece.PAWN_REPRESENTATION);
		assertEquals("P", pawn.toString());
		pawn = new Piece(Piece.Type.PAWN,Piece.Color.WHITE, Piece.PAWN_REPRESENTATION);
		assertEquals("p", pawn.toString());
	}
	public static void main(String[] args) {
		junit.textui.TestRunner.run(new junit.framework.TestSuite(TestPawn.class));
	}
}
