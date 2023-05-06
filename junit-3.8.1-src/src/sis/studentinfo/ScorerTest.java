package sis.studentinfo;

import junit.framework.*;

public class ScorerTest extends TestCase {
	public void testCaptureScore() {
		Scorer scorer = new Scorer();
		assertEquals(75, scorer.score("75"));
	}
	public void testBadScoreEntered() {
		Scorer scorer = new Scorer();
		try {
			scorer.score("abd");
		}
		catch (NumberFormatException success) {
			
		}
	}
	public void testIsValid() {
		Scorer scorer = new Scorer();
		assertTrue(scorer.isValid("75"));
		assertFalse(scorer.isValid("bd"));
	}
}
