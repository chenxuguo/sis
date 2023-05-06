package sis.martin;
import java.lang.RuntimeException;
import java.io.*;
import junit.framework.*;
import java.io.IOException;

public class FileReaderTester extends TestCase{
	public FileReaderTester (String name) {
		super(name);
	}
	private FileReader _input;
	private FileReader _empty;
	private FileReader newEmptyFile() throws IOException {
		File empty = new File("empty.txt");
		FileOutputStream out = new FileOutputStream(empty);
		out.close();
		return new FileReader(empty);
	}
	protected void setUp() {
		try {
			_input = new FileReader("E:\\development\\java\\agil\\Junit_Source\\junit-3.8.1-src\\src\\sis\\martin\\sample.txt");
			_empty = newEmptyFile();
		} catch ( java.io.IOException e) {
			throw new RuntimeException("error on opening test file");
		}
	}
	protected void tearDown() {
		try {
			_input.close();
		} catch (java.io.IOException e) {
			throw new RuntimeException("error on closing test file");
		}
	}
	public void testRead() throws IOException {
		char ch = '&';
		for (int i = 0; i < 4; i++) 
			ch = (char) _input.read();
		assertEquals ('d' , ch );
	}
	public void testReadAtEnd() throws IOException {
		int ch = -1234;
		for (int i = 0; i < 141; i++)
			ch = _input.read();
		assertEquals(-1, _input.read());
	}
	public void testCount() throws IOException {
		final String FILENAME = "E:\\development\\java\\agil\\Junit_Source\\junit-3.8.1-src\\src\\sis\\martin\\sample.txt";
		BufferedReader inFile = new BufferedReader(new FileReader(FILENAME));
		int lines = 0;
		int bytes = 0;
		int words = 0;
		String currentLine;
		while ((currentLine=inFile.readLine()) != null)  {
			lines++;
			bytes+=currentLine.length();
			bytes+=2;  // window's line.separator \r\n
			String[] WORDS = currentLine.split("\t");
			words += WORDS.length;
			if (currentLine.length()==0) {
				words--;
			}
		}
		System.out.println("lines: " + lines + " bytes: " + bytes + " words: " + words);
	}
	public void testReadBoundaries() throws IOException {
		assertEquals("read first char", 'B', _input.read());
		int ch;
		for (int i = 1; i < 140; i++)
			ch = _input.read();
		assertEquals("read last char", '6', _input.read());
		assertEquals("read at end", -1,  _input.read());
		assertEquals("readpast end", -1, _input.read());
	}
	public void testEmptyRead() throws IOException {
		assertEquals(-1, _empty.read());
	}
	public void testReadAfterClose() throws IOException {
		_input.close();
		try {
			_input.read();
			fail("no exception for read past end");
		} catch (IOException io) {}
	}
}
