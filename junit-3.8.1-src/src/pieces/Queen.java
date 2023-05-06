package pieces;

public class Queen extends Piece{

	
	public Queen(int color) {
		this.color = color;
	}
	public char getRepresentation() {
		// TODO Auto-generated method stub
		return color == black ? 'Q' : 'q';
	}
	public static Queen createWhiteQueen() {
		return new Queen(Piece.white);
	}
	public static Queen createBlackQueen() {
		return new Queen(Piece.black);
	}
	@Override
	protected double getScores() {
		// TODO Auto-generated method stub
		return 5.0;
	}

}
