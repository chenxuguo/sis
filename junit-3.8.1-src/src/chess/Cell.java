package chess;

public class Cell {
	final static String WHITE = "white";
	final static String BLACK = "black";
	private String _color ;
	private int _row = 0;;
	private int _col = 0;
	
	public Cell() {
		_color = Cell.WHITE;
	}
	public Cell(String color) {
		_color = color;
	}
	public Cell(String color, int row, int col) {
		_color = color; 
		_row = row;
		_col = col;
	}
	public String getColor() {
		return _color;
	}
}
