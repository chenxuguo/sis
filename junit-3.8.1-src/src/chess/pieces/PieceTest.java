package chess.pieces;
import junit.framework.*;

public class PieceTest extends TestCase {
	public void testColor() {
		assertTrue(new Piece(Piece.Type.PAWN,Piece.Color.BLACK, Piece.PAWN_REPRESENTATION).isBlack());
		assertTrue(new Piece(Piece.Type.PAWN,Piece.Color.WHITE, Piece.PAWN_REPRESENTATION).isWhite());
	}
	public void testCreate() {
		verifyCreation(
				Piece.createWhitePawn(), Piece.createBlackPawn(),
				Piece.Type.PAWN, Piece.PAWN_REPRESENTATION);
		verifyCreation(
				Piece.createWhiteRook(), Piece.createBlackRook(),
				Piece.Type.ROOK, Piece.ROOK_REPRESENTATION);
		verifyCreation(
				Piece.createWhiteKnight(), Piece.createBlacKnight(),
				Piece.Type.KNIGHT, Piece.KNIGHT_REPRESENTATION);
		verifyCreation(
				Piece.createWhiteBishop(), Piece.createBlackBishop(),
				Piece.Type.BISHOP, Piece.BISHOP_REPRESENTATION);
		verifyCreation(
				Piece.createWhiteQueen(), Piece.createBlackQueen(),
				Piece.Type.QUEEN, Piece.QUEEN_REPRESENTATION);
		verifyCreation(
				Piece.createWhiteKing(), Piece.createBlackKing(),
				Piece.Type.KING, Piece.KING_REPRESENTATION);
		Piece blank = Piece.noPiece();
		assertEquals('.', blank.getRepresentation());
		assertEquals(Piece.Type.NO_PIECE, blank.getType());
	}
	public void verifyCreation(Piece whitePiece, Piece blackPiece,
			Piece.Type type, char representation) {
		assertTrue(whitePiece.isWhite());
		assertEquals(type, whitePiece.getType());
		assertEquals(representation, whitePiece.getRepresentation());
		
		assertTrue(blackPiece.isBlack());
		assertEquals(type, blackPiece.getType());
		assertEquals(Character.toUpperCase(representation),
				blackPiece.getRepresentation());
	}
}
