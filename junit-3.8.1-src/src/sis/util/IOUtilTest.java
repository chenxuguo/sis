package sis.util;

import junit.framework.*;
import java.io.*;

public class IOUtilTest extends TestCase {
	static final String FILENAME1 = "IOUtilTest.txt";
	static final String FILENAME2 = "IOUtilTest2.txt";
	
	public void testDelteSingleFile() throws IOException {
		create(FILENAME1);
		assertTrue(IOUtil.delete(FILENAME1));
		TestUtil.assertGone(FILENAME1);
		
	}
	public void testDelteMultipleFiles() throws IOException {
		create(FILENAME1, FILENAME2);
		assertTrue(IOUtil.delete(FILENAME1, FILENAME2));
		TestUtil.assertGone(FILENAME1, FILENAME2);
	}
	public void testDelteNoFile() throws IOException {
		TestUtil.delete(FILENAME1);
		assertFalse(IOUtil.delete(FILENAME1));
	}
	
	public void create(String... filenames) throws IOException {
		for (String filename: filenames) {
			TestUtil.delete(filename);
			new File(filename).createNewFile();
		}
	}
}
