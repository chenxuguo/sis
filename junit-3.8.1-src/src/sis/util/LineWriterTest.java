package sis.util;

import junit.framework.*;
import java.io.*;

public class LineWriterTest extends TestCase{
	public void testMultipleRecords() throws IOException {
		final String file = "LineWriter.testCreate.txt";
		try {
			LineWriter.write(file, new String[] {"a", "b"});
			
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				assertEquals("a", reader.readLine());
				assertEquals("b", reader.readLine());
				assertNull(reader.readLine());	
			}
			finally {
				if (reader != null)
					reader.close();
			}
		}
		finally {
			TestUtil.delete(file);
		}
	}

}
