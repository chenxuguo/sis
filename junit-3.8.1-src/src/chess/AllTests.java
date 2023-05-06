package chess;

public class AllTests extends junit.framework.TestSuite{
	public static junit.framework.TestSuite suite() {
		
		junit.framework.TestSuite suite = new junit.framework.TestSuite();
		suite.addTestSuite(TestBoard.class);
		suite.addTestSuite(BoardTest.class);		
		suite.addTestSuite(chess.pieces.PieceTest.class);
		suite.addTestSuite(chess.pieces.TestPawn.class);
		return suite;
	}
	public static void main(String args) {
		junit.textui.TestRunner.run(suite());
	}
}
