package pieces;

import java.util.*;

abstract public class Piece {

	protected int color;

	public int i, j;
	
	protected char representation = '.';

	public static int white = 0;
	public static int black = 1;

	public boolean isWhite() {
		return color == white;
	}
	public boolean isBlack() {
		return color == black;
	}
	abstract public char getRepresentation();
	protected abstract double getScores();
	public int getColor() 
	{ 
		return color;
	}
	protected abstract List<String> getPossibleMoves(Board board);
}
