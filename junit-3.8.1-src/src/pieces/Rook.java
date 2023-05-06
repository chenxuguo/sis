package pieces;

public class Rook extends Piece {

	Rook(int color) {
		this.color = color;
	}
	public static Rook createBlackRook() {
		// TODO Auto-generated method stub
		return new Rook(Piece.black);
	}
	public static Piece createWhiteRook() {
		// TODO Auto-generated method stub
		return new Rook(Piece.white);
	}
	@Override
	public char getRepresentation() {
		// TODO Auto-generated method stub
		return color == black ? 'R' : 'r';
	}
	@Override
	protected double getScores() {
		// TODO Auto-generated method stub
		return 5.0;
	}
}
