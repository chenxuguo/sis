package chess;

import chess.pieces.Piece;
import junit.framework.*;

public class BoardTest extends TestCase{
	private Board board;
	
	protected void setUp() {
		board = new Board();
	}
	public void testCreate() {
		board.initialize();
		assertEquals(32, board.pieceCount());
		String blankRank = StringUtil.appendNewLine("........");
		assertEquals(
				StringUtil.appendNewLine("RNBQKBNR") +
				StringUtil.appendNewLine("PPPPPPPP") +
				blankRank + blankRank + blankRank + blankRank +
				StringUtil.appendNewLine("pppppppp") +
				StringUtil.appendNewLine("rnbqkbnr"),
			board.print());
	}
	public void testGetPiece() throws Exception {
		board.initialize();
		assertEquals(Piece.Type.ROOK,board.get(0, 0).getType());
		assertEquals(Piece.Type.KING,board.get(7, 4).getType());
		assertEquals(Piece.Type.ROOK, board.getPiece("a8").getType());
		assertEquals(Piece.Type.KING, board.getPiece("e1").getType());
	}
	public void testPutPieces() throws Exception {
		board.putPiece(Piece.createBlackKing(), "b6");
		assertEquals(Piece.Type.KING, board.getPiece("b6").getType());
		board.putPiece(Piece.createBlackRook(), "b5");
		board.putPiece(Piece.createWhiteKing(), "c4");
		assertEquals(3, board.pieceCount());
		String blankRank = StringUtil.appendNewLine("........");
		assertEquals(blankRank + blankRank + 
				StringUtil.appendNewLine(".K......") +
				StringUtil.appendNewLine(".R......") +
				StringUtil.appendNewLine("..k.....") +
				blankRank + blankRank + blankRank,
				board.print());

		createOneCase();
		assertEquals(
				StringUtil.appendNewLine(".KR.....") +
				StringUtil.appendNewLine("P.PB....") +
				StringUtil.appendNewLine(".P..Q...") +
				blankRank +
				StringUtil.appendNewLine(".....nq.") +
				StringUtil.appendNewLine(".....p.p") +
				StringUtil.appendNewLine(".....pp.") +
				StringUtil.appendNewLine("....rk.."),
				board.print());
	
	}
	public void testScoreMap() {
		assertEquals(5.0, Piece.SCORES.get(Piece.Type.QUEEN));
	}
	private void createOneCase() {
		board = new Board();
		board.putPiece(Piece.createBlackKing(), "b8");
		board.putPiece(Piece.createBlackRook(), "c8");
		board.putPiece(Piece.createBlackPawn(), "a7");
		board.putPiece(Piece.createBlackPawn(), "c7");
		board.putPiece(Piece.createBlackBishop(), "d7");
		board.putPiece(Piece.createBlackPawn(), "b6");
		board.putPiece(Piece.createBlackQueen(), "e6");
		board.putPiece(Piece.createWhiteKnight(), "f4");
		board.putPiece(Piece.createWhiteQueen(), "g4");
		board.putPiece(Piece.createWhitePawn(), "f3");
		board.putPiece(Piece.createWhitePawn(), "h3");
		board.putPiece(Piece.createWhitePawn(), "f2");
		board.putPiece(Piece.createWhitePawn(), "g2");
		board.putPiece(Piece.createWhiteRook(), "e1");
		board.putPiece(Piece.createWhiteKing(), "f1");		
	}
	public void testGetScores() {
		board.putPiece(Piece.createBlackKing(), "b8");
		assertEquals(4.0, board.getBlackScores(Piece.Color.BLACK));
		assertEquals(0.0, board.getWhiteScores());
		board.putPiece(Piece.createBlackRook(), "c8");
		assertEquals(9.0, board.getBlackScores(Piece.Color.BLACK));
		assertEquals(0.0, board.getWhiteScores());		
		board.putPiece(Piece.createBlackPawn(), "a7");
		assertEquals(10.0, board.getBlackScores(Piece.Color.BLACK));
		assertEquals(0.0,  board.getWhiteScores());
		board.putPiece(Piece.createBlackPawn(), "c7");
		assertEquals(11.0, board.getBlackScores(Piece.Color.BLACK));
		assertEquals(0.0,  board.getWhiteScores());		
		board.putPiece(Piece.createBlackBishop(), "d7");
		assertEquals(14.0, board.getBlackScores(Piece.Color.BLACK));
		assertEquals(0.0,  board.getWhiteScores());		
		board.putPiece(Piece.createBlackPawn(), "b6");
		assertEquals(15.0, board.getBlackScores(Piece.Color.BLACK));
		assertEquals(0.0,  board.getWhiteScores());		
		board.putPiece(Piece.createBlackQueen(), "e6");
		assertEquals(20.0, board.getBlackScores(Piece.Color.BLACK));
		assertEquals(0.0,  board.getWhiteScores());		
		board.putPiece(Piece.createWhiteKnight(), "f4");
		assertEquals(20.0, board.getBlackScores(Piece.Color.BLACK));
		assertEquals(2.5,  board.getWhiteScores());		
		board.putPiece(Piece.createWhiteQueen(), "g4");
		assertEquals(20.0, board.getBlackScores(Piece.Color.BLACK));
		assertEquals(7.5,  board.getWhiteScores());		
		board.putPiece(Piece.createWhitePawn(), "f3");
		assertEquals(20.0, board.getBlackScores(Piece.Color.BLACK));
		assertEquals(8.5,  board.getWhiteScores());		
		board.putPiece(Piece.createWhitePawn(), "h3");
		assertEquals(20.0, board.getBlackScores(Piece.Color.BLACK));
		assertEquals(9.5,  board.getWhiteScores());		
		board.putPiece(Piece.createWhitePawn(), "f2");
		assertEquals(20.0, board.getBlackScores(Piece.Color.BLACK));
		assertEquals(9.5,  board.getWhiteScores());		
		board.putPiece(Piece.createWhitePawn(), "g2");
		assertEquals(20.0, board.getBlackScores(Piece.Color.BLACK));
		assertEquals(10.5,  board.getWhiteScores());		
		board.putPiece(Piece.createWhiteRook(), "e1");
		assertEquals(20.0, board.getBlackScores(Piece.Color.BLACK));
		assertEquals(15.5,  board.getWhiteScores());		
		board.putPiece(Piece.createWhiteKing(), "f1");		
		assertEquals(20.0, board.getBlackScores(Piece.Color.BLACK));
		assertEquals(19.5,  board.getWhiteScores());		
	}
	public void testMove() throws Exception {
		Piece piece = Piece.createWhiteKing();
		board.putPiece(piece, "f8");
		assertEquals(Piece.Type.KING, board.getPiece("f8").getType());
		board.move("f8", "f7");
		assertEquals(board.getPiece("f7").getType(),Piece.Type.KING);
		assertEquals(board.getPiece("f8").getType(), Piece.Type.NO_PIECE);
		try {
			board.move("f7", "f5");
			fail("It shouldn't go here");
		} 
		catch(Exception e) {
			assertEquals(e.getMessage(), "King can only move one step");
		}
		assertEquals(board.getPiece("f7").getType(), Piece.Type.KING);
		try {
			board.move("f5", "h5");
			System.out.println(board.print());
			fail("It shouldn't go here");
		} 
		catch(Exception e) {
			assertEquals(e.getMessage(), "It should be no piece here");
		}
		assertEquals(board.getPiece("f7").getType(), Piece.Type.KING);
//		board.movePieceKing(Board.Move.LEFT);
//		assert(board.getPiece("f7").getType() == Piece.Type.KING);
//		board.movePieceKing(Board.Move.RIGHT);
	//	board.movePieceKing(Board.Move.U);
		//board.movePieceKing(Board.Move.DOWN);
	}	
}
