package pieces;

public class Knight extends Piece {

	public Knight(int color) {
		this.color = color;
	}

	public static Piece createWhiteKnight() {
		// TODO Auto-generated method stub
		return new Knight(Piece.white);
	}

	@Override
	public char getRepresentation() {
		// TODO Auto-generated method stub
		return isBlack() ? 'N' : 'n';
	}

	@Override
	protected double getScores() {
		// TODO Auto-generated method stub
		return 2.5;
	}

}
