package sis.studentinfo;
import junit.framework.*;
import java.util.Random;

public class PasswordGeneratorTest extends TestCase {

	public void testGeneratePassword() {
		PasswordGenerator generator = new PasswordGenerator();
		generator.setRandom(new MockRandom('A'));
		assertEquals("ABCDEFGH", generator.generatePassword());
		generator.setRandom(new MockRandom('C'));
		assertEquals("CDEFGHIJ", generator.generatePassword());
	}
	public void testRandom() {
		final Random rand = new Random(1);
		final Random rand2 = new Random();
		assertFalse(rand.nextInt() == rand2.nextInt());
	}
	
	class MockRandom extends java.util.Random {
		private int i;
		MockRandom( char startCharValue) {
			i = startCharValue - PasswordGenerator.LOW_END_PASSWORD_CHAR;
		}
		protected int next(int bite) {
			return i++;
		}
	}
}
