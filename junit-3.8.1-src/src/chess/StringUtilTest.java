package chess;
import static chess.StringUtil.*;
import junit.framework.TestCase;

public class StringUtilTest extends TestCase{
	public void testPalindrome() {
		
		assertFalse(isPalindrome("abcdef"));
		assertFalse(isPalindrome("abccda"));
		assertTrue(isPalindrome("abccba"));
		assertTrue(isPalindrome("a"));
		assertTrue(isPalindrome("aa"));
		assertFalse(isPalindrome("ab"));
		assertTrue(isPalindrome("aba"));
		assertTrue(isPalindrome("abbba"));
		assertTrue(isPalindrome("abba"));
		assertFalse(isPalindrome("abbaa"));
		assertFalse(isPalindrome("abcda"));
	}
}
