package chess;

import java.util.*;

import chess.pieces.Piece;
import chess.pieces.Piece.Type;

public class Board {
	static final int ROW = 8;
	static final int COLUMN = 8;
	Piece cells[][] = new Piece[Board.ROW][Board.COLUMN];
	ArrayList<Piece> pieces = new ArrayList<>(Board.COLUMN * Board.ROW);
	ArrayList<Piece> blackPieces = new ArrayList<>(Board.COLUMN * Board.ROW);
	ArrayList<Piece> whitePieces = new ArrayList<>(Board.COLUMN * Board.ROW);
	public static enum Move { LEFT, RIGHT, UP, DOWN};
	
	
	public void initialize() {
		put(new Piece(Piece.Type.ROOK,Piece.Color.WHITE,Piece.ROOK_REPRESENTATION), 7, 0);
		put(new Piece(Piece.Type.ROOK,Piece.Color.BLACK, Piece.ROOK_REPRESENTATION), 0, 0);
		put(new Piece(Piece.Type.ROOK,Piece.Color.WHITE, Piece.ROOK_REPRESENTATION), 7, 7);
		put(new Piece(Piece.Type.ROOK,Piece.Color.BLACK, Piece.ROOK_REPRESENTATION), 0, 7);
		
		put(new Piece(Piece.Type.KNIGHT,Piece.Color.WHITE,Piece.KNIGHT_REPRESENTATION), 7, 1);
		put(new Piece(Piece.Type.KNIGHT,Piece.Color.BLACK, Piece.KNIGHT_REPRESENTATION), 0, 1);
		put(new Piece(Piece.Type.KNIGHT,Piece.Color.WHITE, Piece.KNIGHT_REPRESENTATION), 7, 6);
		put(new Piece(Piece.Type.KNIGHT,Piece.Color.BLACK, Piece.KNIGHT_REPRESENTATION), 0, 6);

		put(new Piece(Piece.Type.BISHOP, Piece.Color.WHITE,Piece.BISHOP_REPRESENTATION), 7, 2);
		put(new Piece(Piece.Type.BISHOP, Piece.Color.BLACK,Piece.BISHOP_REPRESENTATION), 0, 2);
		put(new Piece(Piece.Type.BISHOP, Piece.Color.WHITE,Piece.BISHOP_REPRESENTATION), 7, 5);
		put(new Piece(Piece.Type.BISHOP, Piece.Color.BLACK,Piece.BISHOP_REPRESENTATION), 0, 5);
		
		put(new Piece(Piece.Type.QUEEN, Piece.Color.WHITE,Piece.QUEEN_REPRESENTATION), 7, 3);
		put(new Piece(Piece.Type.QUEEN, Piece.Color.BLACK, Piece.QUEEN_REPRESENTATION), 0, 3);
		put(new Piece(Piece.Type.KING, Piece.Color.WHITE, Piece.KING_REPRESENTATION), 7, 4);
		put(new Piece(Piece.Type.KING, Piece.Color.BLACK, Piece.KING_REPRESENTATION), 0, 4);
		
		for( int i = 0; i < Board.COLUMN; i++) {
			put(new Piece(Piece.Type.PAWN,Piece.Color.WHITE, Piece.PAWN_REPRESENTATION), 6, i);
			put(new Piece(Piece.Type.PAWN,Piece.Color.BLACK, Piece.PAWN_REPRESENTATION), 1, i);
		}
	}
	
	public Board() {
		for(int i = 0; i < Board.COLUMN; i++) {
			pieces = new ArrayList<>(8);
			for(int j = 0; j < Board.ROW; j++) {
				cells[i][j] = Piece.noPiece();
			}
		}
		//initialize();
	}
	
//	public Pawn get(int indexOfRows, int indexOfColumns) {
//		return new Pawn();
//	}
	
	public void put(Piece piece , int i, int j) {
		pieces.add(piece);
		if(piece.isBlack()) blackPieces.add(piece);
		if(piece.isWhite()) whitePieces.add(piece);
		cells[i][j] = piece;
	}

	public int getNumberOfPawns() {
		// TODO Auto-generated method stub
		return pieces.size();
	}
	
	public String toString() {
		String str ="";
		for (int i = 0; i < Board.ROW; i++) {
			for(int j = 0; j < Board.COLUMN; j++)
				str += cells[i][j];
			str += StringUtil.NEWLINE;
		}
		return str;
	}
//	public int[][] getPawnPositions(){
//		int[][] result = {{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0},
//				{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0},
//				{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0},
//				{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0},
//				{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0},
//				{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0},
//				{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0},
//				{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}
//		};
//		int indexOfRows = 0;
//		for(int i = 0; i < 8; i++)
//			for(int j = 0; j < 8; j++)
//				if(_cells[i][j] instanceof Pawn) {
//					result[indexOfRows][0] = i;
//					result[indexOfRows][1] = j;
//					indexOfRows++;
//				}
//		return result;
//	}

	public int pieceCount() {
		return pieces.size();
	}

	public String print() {
		// TODO Auto-generated method stub
		return toString();
	}

	public Piece get(int i, int j) {
		// TODO Auto-generated method stub
		return cells[i][j];
	}

	public Piece getPiece(String string) throws Exception {
		if(string.length() == 2) {
			int i = 8 - string.charAt(1) + '0' ; 
			int j = string.charAt(0) - 'a';
			if (i < 0 || i > 8) throw new Exception("Out of bound error: string " + string + " at 0:  i = " + i);
			if( j < 0 || j > 8) throw new Exception("Out of bound error: j = " + j);
			return cells[i][j];
			
		} else {
			throw new Exception("illegal string: " + string);
		}
	}
	
	public void putPiece(Piece piece, String string) {
		pieces.add(piece);
		if (piece.isBlack()) blackPieces.add(piece);
		if (piece.isWhite()) whitePieces.add(piece);
		cells[8 - string.charAt(1) + '0'][string.charAt(0) - 'a'] = piece;
	}

	public double getBlackScores(Piece.Color color) {
		double score = 0.0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (cells[i][j].getColor() == color) {
					if (cells[i][j].getType() == Piece.Type.PAWN)
						score += hasPawnsInTheSameColumn(i, j, color) ? 0.5 : 1.0;
					else 
						score += Piece.SCORES.get(cells[i][j].getType());
				}
			}
		}
		return score;
	}

	private boolean hasPawnsInTheSameColumn(int i, int j, Piece.Color color) {
		for (int k = 0; k < 8; k++) {
			if (cells[k][j].getColor() == color  && cells[k][j].getType() == Piece.Type.PAWN && k != i)
				return true;
		}
		return false;
	}
	public double getWhiteScores() {
		// TODO Auto-generated method stub
		double score = 0.0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (cells[i][j].isWhite()) {
					
					if (cells[i][j].getType() == Piece.Type.PAWN)
						score += hasWhitePawnsInTheSameColumn(i, j) ? 0.5 : 1;
					else {
						score += Piece.SCORES.get(cells[i][j].getType());
					}
		System.out.println(" i= " + i + " j= " + j + "cells is " + cells[i][j] + " score: " + score);
				}
			}
		}
System.out.println("\r\n");
		return score;
	}	
	private boolean hasWhitePawnsInTheSameColumn(int i, int j) {
		return hasPawnInTheSameColumn(i, j, Piece.Color.WHITE);
	}

	private boolean hasPawnInTheSameColumn(int i, int j, Piece.Color color) {
		for (int k = 0; k < 8; k++) {
			if (cells[k][j].getColor() == color && cells[k][j].getType() == Piece.Type.PAWN && k != i) {
				System.out.println("k = " + k + "j = " + j + "i = " + i);
				return true;
			}
		}
		return false;
	}
	public void move(String from, String to) throws Exception  {
	// TODO Auto-generated method stub
		Piece piece = null;
		try {
			piece = getPiece(from);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		if (piece.getType() == Type.NO_PIECE.KING) {
			if (
					to.charAt(1) - from.charAt(1) > 1 ||
					to.charAt(1) - from.charAt(1) < -1 ||
					to.charAt(0) - from.charAt(0) > 1 )
				throw new Exception("King can only move one step");
			else {
				putPiece(piece, to);
				putPiece(Piece.noPiece(), from);
			}
		} else {
			throw new Exception("It should be no piece here");
		}
		return;
	}

//	public void movePieceKing(Move left) {
//		// TODO Auto-generated method stub
//		putPiece(Piece.noPiece(), "");
//		putPiece(Piece.)
//	}
}
