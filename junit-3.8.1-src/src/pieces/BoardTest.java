package pieces;
import chess.StringUtil;
import junit.framework.*;
import java.util.*;

public class BoardTest extends TestCase {
	private Board board;
	
	protected void setUp() {
		board = new Board();
	}
	
	
	public void testCreate() {
		Board b = new Board();
		assertTrue(b.isBlank());
	}
	public void testInitialize() {
		Board b= new Board();
		b.initialize();
		assertEquals("PPPPPPPP",b.getRow(2));
		assertEquals("pppppppp",b.getRow(7));
		assertEquals("........" + "\r\n" +
					 "PPPPPPPP" + "\r\n" +
					 "........" + "\r\n" +
					 "........" + "\r\n" +
					 "........" + "\r\n" +
					 "........" + "\r\n" +
					 "pppppppp" + "\r\n" +
					 "........",
					 b.toString()
		);
		assertEquals(16, b.pieces());
	}
	public void testPositionStringConvertion() throws Exception{
		final String position = "a8";
		assertEquals(0, Board.positionToRowIndex(position));
		assertEquals('8', Board.rowIndexToChar(Board.positionToRowIndex(position)));
		assertEquals(0, Board.positionToColumnIndex(position));
		assertEquals('a', Board.columnIndexToChar(Board.positionToColumnIndex(position)));
		final String position2 = "a1";
		assertEquals(7, Board.positionToRowIndex(position2));
		assertEquals('1', Board.rowIndexToChar(Board.positionToRowIndex(position2)));
		assertEquals(0, Board.positionToColumnIndex(position2));
		assertEquals('a', Board.columnIndexToChar(Board.positionToColumnIndex(position2)));
		final String position3 = "h1";
		assertEquals(7, Board.positionToRowIndex(position3));
		assertEquals('1', Board.rowIndexToChar(Board.positionToRowIndex(position3)));
		assertEquals(7, Board.positionToColumnIndex(position3));
		assertEquals('h', Board.columnIndexToChar(Board.positionToColumnIndex(position3)));
		final String position4 = "h8";
		assertEquals(0, Board.positionToRowIndex(position4));
		assertEquals(7, Board.positionToColumnIndex(position4));
		final String position5 = "b6";
		assertEquals(2, Board.positionToRowIndex(position5));
		assertEquals(1, Board.positionToColumnIndex(position5));

	}
	public void testInvalidPositionString() {
		final String position5 = "08";
		try {
			assertEquals(7, Board.positionToColumnIndex(position5));
			fail("It shouldn't be here");
		} catch(Exception e) {
			int i = '0' - 'a' ;
			assertEquals("Index out of range [1 to 8]" + i, e.getMessage());
		}	
		final String position6 = "a0";		
		try {
			assertEquals(8, Board.positionToRowIndex(position6));
			fail("It shouldn't be here");
		} catch(Exception e) {
			int i = 7 - ('0' - '1' );
			assertEquals("Index out of range [1 to 8]" + i, e.getMessage());
		}	
	}
	public void testString() {
		int i = 'a';
		assertEquals(97, i);
		assertEquals("97", String.valueOf(i));
		assertEquals('a', (char)i);
		assertEquals("a", String.valueOf((char)i));
	}
	public void testPutPieces() throws Exception {
		board.putPiece(King.createBlackKing(), "b6");
		assertEquals(1, board.pieceCount());
		String blankRank = StringUtil.appendNewLine("........");
		assertEquals(blankRank + blankRank + 
				StringUtil.appendNewLine(".K......") +
				StringUtil.appendNewLine("........") +
				StringUtil.appendNewLine("........") +
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
	private void createOneCase() {
		board = new Board();
		board.putPiece(King.createBlackKing(), "b8");
		board.putPiece(Rook.createBlackRook(), "c8");
		board.putPiece(Pawn.createBlackPawn(), "a7");
		board.putPiece(Pawn.createBlackPawn(), "c7");
		board.putPiece(Bishop.createBlackBishop(), "d7");
		board.putPiece(Pawn.createBlackPawn(), "b6");
		board.putPiece(Queen.createBlackQueen(), "e6");
		board.putPiece(Knight.createWhiteKnight(), "f4");
		board.putPiece(Queen.createWhiteQueen(), "g4");
		board.putPiece(Pawn.createWhitePawn(), "f3");
		board.putPiece(Pawn.createWhitePawn(), "h3");
		board.putPiece(Pawn.createWhitePawn(), "f2");
		board.putPiece(Pawn.createWhitePawn(), "g2");
		board.putPiece(Rook.createWhiteRook(), "e1");
		board.putPiece(King.createWhiteKing(), "f1");		
	}
	public void testGetScores() {
		board.putPiece(King.createBlackKing(), "b8");
		assertEquals(4.0, board.getBlackScores());
		assertEquals(0.0, board.getWhiteScores());
		board.putPiece(Rook.createBlackRook(), "c8");
		assertEquals(9.0, board.getBlackScores());
		assertEquals(0.0, board.getWhiteScores());		
		board.putPiece(Pawn.createBlackPawn(), "a7");
		assertEquals(10.0, board.getBlackScores());
		assertEquals(0.0,  board.getWhiteScores());
		board.putPiece(Pawn.createBlackPawn(), "c7");
		assertEquals(11.0, board.getBlackScores());
		assertEquals(0.0,  board.getWhiteScores());		
		board.putPiece(Bishop.createBlackBishop(), "d7");
		assertEquals(14.0, board.getBlackScores());
		assertEquals(0.0,  board.getWhiteScores());		
		board.putPiece(Pawn.createBlackPawn(), "b6");
		assertEquals(15.0, board.getBlackScores());
		assertEquals(0.0,  board.getWhiteScores());		
		board.putPiece(Queen.createBlackQueen(), "e6");
		assertEquals(20.0, board.getBlackScores());
		assertEquals(0.0,  board.getWhiteScores());		
		board.putPiece(Knight.createWhiteKnight(), "f4");
		assertEquals(20.0, board.getBlackScores());
		assertEquals(2.5,  board.getWhiteScores());		
		board.putPiece(Queen.createWhiteQueen(), "g4");
		assertEquals(20.0, board.getBlackScores());
		assertEquals(7.5,  board.getWhiteScores());		
		board.putPiece(Pawn.createWhitePawn(), "f3");
		assertEquals(20.0, board.getBlackScores());
		assertEquals(8.5,  board.getWhiteScores());		
		board.putPiece(Pawn.createWhitePawn(), "h3");
		assertEquals(20.0, board.getBlackScores());
		assertEquals(9.5,  board.getWhiteScores());		
		board.putPiece(Pawn.createWhitePawn(), "f2");
		assertEquals(20.0, board.getBlackScores());
		assertEquals(9.5,  board.getWhiteScores());		
		board.putPiece(Pawn.createWhitePawn(), "g2");
		assertEquals(20.0, board.getBlackScores());
		assertEquals(10.5,  board.getWhiteScores());		
		board.putPiece(Rook.createWhiteRook(), "e1");
		assertEquals(20.0, board.getBlackScores());
		assertEquals(15.5,  board.getWhiteScores());		
		board.putPiece(King.createWhiteKing(), "f1");		
		assertEquals(20.0, board.getBlackScores());
		assertEquals(19.5,  board.getWhiteScores());		
	}
	public void testMove() throws Exception {
		Piece piece = King.createWhiteKing();
		board.putPiece(piece, "f8");
		assertTrue(piece.getPossibleMoves(board).contains("g8"));
		assertTrue(piece.getPossibleMoves(board).contains("e8"));
		assertTrue(piece.getPossibleMoves(board).contains("f7"));
		assertTrue(piece.getPossibleMoves(board).contains("e7"));
		assertTrue(piece.getPossibleMoves(board).contains("g7"));		
	}	
	
}
