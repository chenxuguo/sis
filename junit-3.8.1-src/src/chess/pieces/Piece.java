package chess.pieces;

import java.util.HashMap;
import java.util.Map;

import chess.Board;

public class Piece {
	public enum Color {BLACK, WHITE}
	public static enum Type {PAWN, ROOK,BISHOP, KNIGHT, QUEEN, KING, NO_PIECE}
	public static final char ROOK_REPRESENTATION = 'r';
	public static final char PAWN_REPRESENTATION = 'p';
	public static final char BISHOP_REPRESENTATION = 'b';
	public static final char QUEEN_REPRESENTATION = 'q';
	public static final char KNIGHT_REPRESENTATION = 'n';
	public static final char KING_REPRESENTATION = 'k';
	public static final char NO_PIECE_REPRESENTATION = '.';
	public static Map<Piece.Type, Double> SCORES = new HashMap<>();
	static {
		SCORES.put(Type.QUEEN, 5.0);
		SCORES.put(Type.ROOK, 5.0);
		SCORES.put(Type.BISHOP, 3.0);
		SCORES.put(Type.KNIGHT, 2.5);
		SCORES.put(Type.PAWN, 1.0);
		SCORES.put(Type.KING, 4.0);
		SCORES.put(Type.NO_PIECE, 0.0);
	}

	private Color color = Color.BLACK;
	private String name;
	private Type type;
	private char representation;
	public Piece(Color color, char representation) { 
		this.color = color; 
		this.representation = representation;
	}
	public Piece(Type type, char representation) { 
		this.type = type; 
		if (color == Color.BLACK) this.representation = Character.toUpperCase(representation);
		else this.representation = representation;
	}	
	public Piece(Type type, Color color, char representation) {
		this.type = type;
		this.color = color; 
		if (color == Color.BLACK) 
			this.representation = Character.toUpperCase(representation);
		else 
			this.representation = representation;
	}	
	public Piece(Type type) {
		this.type = type;
		switch(type) {
		case PAWN:
			
		}
	}
	public Piece create(Type type, Color color, char representation) {
		return new Piece(type, color, representation);
	}	
	public boolean isWhite() {
		return color == Color.WHITE;
	}
	public boolean isBlack() {
		return color == Color.BLACK;
	}
	public Color getColor() {
		return this.color;
	}
	public String toString() {
			return String.valueOf(this.representation);
	}
	public void putInBoard(Board board, int row, int col) {
		
	}
	public static Piece createWhitePawn() {
		return new Piece(Type.PAWN,Color.WHITE, PAWN_REPRESENTATION);
	}
	public static Piece createBlackPawn() {
		return new Piece(Type.PAWN,Color.BLACK, PAWN_REPRESENTATION);
	}
	public static Piece createWhiteRook() {
		return new Piece(Type.ROOK,Color.WHITE, ROOK_REPRESENTATION);
	}
	public static Piece createBlacKnight() {
		return new Piece(Type.KNIGHT,Color.BLACK, KNIGHT_REPRESENTATION);
	}
	public static Piece createWhiteKnight() {
		return new Piece(Type.KNIGHT,Color.WHITE, KNIGHT_REPRESENTATION);
	}
	public static Piece createBlackRook() {
		return new Piece(Type.ROOK,Color.BLACK, ROOK_REPRESENTATION);
	}
	public static Piece createWhiteBishop() {
		return new Piece(Type.BISHOP,Color.WHITE, BISHOP_REPRESENTATION);
	}
	public static Piece createBlackBishop() {
		return new Piece(Type.BISHOP,Color.BLACK, BISHOP_REPRESENTATION);
	}
	public static Piece createWhiteQueen() {
		return new Piece(Type.QUEEN,Color.WHITE, QUEEN_REPRESENTATION);
	}
	public static Piece createBlackQueen() {
		return new Piece(Type.QUEEN,Color.BLACK, QUEEN_REPRESENTATION);
	}
	public static Piece createBlackKing() {
		return new Piece(Type.KING,Color.BLACK, KING_REPRESENTATION);
	}
	public static Piece createWhiteKing() {
		// TODO Auto-generated method stub
		return new Piece(Type.KING, Color.WHITE, KING_REPRESENTATION);
	}
	public static Piece noPiece() {
		return new Piece(Type.NO_PIECE, NO_PIECE_REPRESENTATION);
	}
	public char getRepresentation() {
		// TODO Auto-generated method stub
		return this.representation;
	}
	public Type getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
}
