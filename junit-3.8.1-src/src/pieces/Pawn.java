package pieces;

public class Pawn extends Piece {

	public Pawn(int black) {
		// TODO Auto-generated constructor stub
		color = black;
	}

	public Pawn() {
		// TODO Auto-generated constructor stub
		color = Piece.white;
	}

	public String toString() {
		return color == Piece.black ? "P" : "p"; 
	}
	@Override
	public boolean equals(Object that) {
		Pawn p = (Pawn)that;
		return color == p.color
				;
	}
	public static Piece createWhitePawn() {
		// TODO Auto-generated method stub
		return new Pawn(Piece.white);
	}

	public static Piece createBlackPawn() {
		// TODO Auto-generated method stub
		return new Pawn(Piece.black);
	}

	@Override
	public char getRepresentation() {
		// TODO Auto-generated method stub
		return isBlack() ? 'P' : 'p';
	}

}
