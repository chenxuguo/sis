package pieces;

import java.util.*;

public class Board {
	char[][] cells = null;
	ArrayList<Piece> pieces = new ArrayList<>(64); 
	public boolean isBlank() {
		// TODO Auto-generated method stub
		return true;
	}
	public int pieces() {
		// TODO Auto-generated method stub
		return pieces.size();
	}
	public List listOfPieces() {
		return pieces;
	}
	public void initialize() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 8; i++)
		{
			pieces.add(new Pawn(Pawn.black));
			pieces.add(new Pawn(Pawn.white));
		}
		
	}

	public String getRow(int i) {
		// TODO Auto-generated method stub
		if (i == 7) return "pppppppp";
		return "PPPPPPPP";
	}
	public  String toString() {
		return "........" + "\r\n" +
				 "PPPPPPPP" + "\r\n" +
				 "........" + "\r\n" +
				 "........" + "\r\n" +
				 "........" + "\r\n" +
				 "........" + "\r\n" +
				 "pppppppp" + "\r\n" +
				 "........";
	}

	public void putPiece(Piece piece, String position) {
		// TODO Auto-generated method stub
		try {
			piece.i = positionToRowIndex(position);
			piece.j = positionToColumnIndex(position);
		} catch (BoardException e) {
			System.out.println(e.getMessage());
		}
		pieces.add(piece);
	}
	public static int positionToColumnIndex(String position) throws BoardException {
		// TODO Auto-generated method stub
		int i = position.charAt(0) - 'a' ;
		if (i > 7 || i < 0) {
			throw new BoardException("Index out of range [1 to 8]" + i);
		}
		return i;
		
	}
	public static int positionToRowIndex(String positionString) throws BoardException {
		// TODO Auto-generated method stub
		int j = 7 - (positionString.charAt(1) - '1');
		if (j > 7 || j < 0) {
			throw new BoardException("Index out of range [1 to 8]" + j);
		}
		return j;
	}
	public boolean add(Pawn p3, String string) {
		// TODO Auto-generated method stub
		return true;
	}

	public Object get(String string) {
		// TODO Auto-generated method stub
		return new Pawn(Piece.white);
	}

	public Object pieceCount() {
		// TODO Auto-generated method stub
		return pieces.size();
	}

	public String print() {
		// TODO Auto-generated method stub
		loadCells();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				builder.append(String.valueOf(cells[i][j]));
			}
			builder.append("\r\n");
		}
		return builder.toString();
	}
	private void loadCells() {
		if(cells != null) return;
		this.cells = new char[][] {
			{'.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.'},
		};
 		for(Piece piece: pieces) {
			cells[piece.i][piece.j] = piece.getRepresentation();
		}
	}
	public boolean add(Pawn p) {
		// TODO Auto-generated method stub
		return true;
	}
	public double getBlackScores() {
		// TODO Auto-generated method stub
		return getScores(Piece.black);
	}
	private double getScores(int color) {
		double score = 0.0;
		for (Piece piece : pieces) {
			if ( piece.getColor()  == color) {
					if (piece instanceof Pawn)
						score += hasPawnsInTheSameColumn(piece) ? 0.5 : 1.0;
					else 
						score += piece.getScores();
			}
		}
	
		return score;
	}
	private boolean hasPawnsInTheSameColumn(Piece piece) {
		for (Piece other: pieces) {
			if (					
					other instanceof Pawn && 
					other.j == piece.j	&&
					other.getColor() == piece.getColor()  && 
					other.i != piece.i )
				return true;
		}
		return false;
	}
	
	public double getWhiteScores() {
		// TODO Auto-generated method stub
		return getScores(Piece.white);
	}
	public static char rowIndexToChar(int rowIndex) {
		// TODO Auto-generated method stub
		return (char)((8 - rowIndex) + '0');
	}
	public static char columnIndexToChar(int columnIndex) {
		// TODO Auto-generated method stub
		return (char)(columnIndex + 'a');
	}

}
