package chess;

import chess.pieces.*;

public class TestBoard extends junit.framework.TestCase{
	private Board _board;
	public void setUp() {
		_board = new Board();
	}
	
	public void testCreate() {
		for(int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++)
				assertTrue(Piece.Type.NO_PIECE == _board.get(i,j).getType());			
		}
	}
	public void testPutPawn() {
		final int row = 1;
		final int column = 4;
		_board.put(new Piece(Piece.Type.PAWN, Piece.Color.WHITE, 'P'), row, column);
		assertEquals(1, _board.getNumberOfPawns());
		_board.put(new Piece(Piece.Type.PAWN,Piece.Color.BLACK, 'P'), row, column + 1);
		assertEquals(2, _board.getNumberOfPawns());
//		//assertEquals(indexOfRows, _board.getPawnPositions()[_board.getNumberOfPawns()][rowOfPawn]);
//		//assertEquals(indexOfColumns, _board.getPawnPositions()[_board.getNumberOfPawns()][columnOfPawn]);
//		//indexOfRows = 7;
//		//indexOfColumns = 7;
//		//_board.put(new Pawn(Pawn.black), indexOfRows, indexOfColumns);
//		//assertEquals(2, _board.getNumberOfPawns());
//		//assertEquals(indexOfRows, _board.getPawnPositions()[_board.getNumberOfPawns()][rowOfPawn]);
//		//assertEquals(indexOfRows, _board.getPawnPositions()[_board.getNumberOfPawns()][columnOfPawn]);		
	}
}
