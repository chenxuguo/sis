package pieces;

public class Bishop extends Piece{
	Bishop(int color){
		this.color = color;
	}

	@Override
	public char getRepresentation() {
		// TODO Auto-generated method stub
		return isBlack() ? 'B' : 'b';
	}

	public static Piece createBlackBishop() {
		return new Bishop(Piece.black);
	}

	@Override
	protected double getScores() {
		// TODO Auto-generated method stub
		return 3.0;
	}
}
