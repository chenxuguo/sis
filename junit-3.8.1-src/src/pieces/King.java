package pieces;
import java.util.*;

public class King extends Piece {
	
	private final int scores = 4;
	
	public King(int color) {
		this.color = color;
	}

	public char getRepresentation() {
		// TODO Auto-generated method stub
		return color == Piece.black ? 'K' : 'k';
	}
	
	public static King createWhiteKing() {
		// TODO Auto-generated method stub
		return new King(Piece.white);
	}

	public static King createBlackKing() {
		// TODO Auto-generated method stub
		return new King(Piece.black);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected double getScores() {
		// TODO Auto-generated method stub
		return 4.0;
	}

	@Override
	protected List<String> getPossibleMoves(Board board) {
		ArrayList<String> possibleMoves = new ArrayList<String>();
		possibleMoves.add(String.valueOf(Board.columnIndexToChar(j + 1)) + 
					String.valueOf(Board.rowIndexToChar(i)));
		possibleMoves.add(String.valueOf(Board.columnIndexToChar(j - 1)) + 
				String.valueOf(Board.rowIndexToChar(i)));
		if (i + 1 < 8) {
			possibleMoves.add(String.valueOf(Board.columnIndexToChar(j)) + 
				String.valueOf(Board.rowIndexToChar(i + 1)));
			if (j - 1 > 0)possibleMoves.add(String.valueOf(Board.columnIndexToChar(j - 1)) + 
				String.valueOf(Board.rowIndexToChar(i + 1)));
			if (j + 1 < 8)possibleMoves.add(String.valueOf(Board.columnIndexToChar(j + 1)) + 
				String.valueOf(Board.rowIndexToChar(i + 1)));
		}
		if (i - 1 > 0) {
			possibleMoves.add(String.valueOf(Board.columnIndexToChar(j - 1)) + 
				String.valueOf(Board.rowIndexToChar(i - 1)));
			possibleMoves.add(String.valueOf(Board.columnIndexToChar(j)) + 
				String.valueOf(Board.rowIndexToChar(i - 1)));
			possibleMoves.add(String.valueOf(Board.columnIndexToChar(j + 1)) + 
				String.valueOf(Board.rowIndexToChar(i - 1)));
		}
		System.out.println(possibleMoves);
		return possibleMoves;
	}

}
