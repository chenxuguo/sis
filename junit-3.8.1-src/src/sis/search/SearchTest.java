package sis.search;

import junit.framework.*;
import java.net.*;
import java.io.*;
import sis.util.*;

public class SearchTest extends TestCase {
	
	public static final String[] TEST_HTML = {
			"<HTML>",
			"<body>",
			"Book: Agil Java, By Jeff Langr<br />",
			"Synopsis: Mr Langr teaches you<br />",
			"Java via test-driven development. <br />",
			"</body><html>"};
	
	public static final String separator = File.separator;
	static String userHome = System.getProperties().getProperty("user.home");
	public static final String FILE = userHome + separator + "testFileSearch.html";
	public static final String URL = "file:" + FILE;

	protected void setUp() throws IOException {
		TestUtil.delete(FILE);
		LineWriter.write(FILE, TEST_HTML);
	}
	
	protected void tearDown() throws IOException {
		TestUtil.delete(FILE);
	}
	public void testCreate() throws IOException {
		Search search = new Search(URL, "*");
		assertEquals(new URL(URL).toString(), search.getUrl());
		assertEquals("*", search.getText());
	}
	public void testPositiveSearch() throws IOException {
		Search search = new Search(URL, "Jeff Langr");
		search.execute();
		assertTrue(search.matches() >= 1);
		assertFalse(search.errored());
	}
	public void testNegativeSearch() throws IOException {
		final String unlikelyText = "name cass elliott";
		Search search = new Search(URL, unlikelyText);
		search.execute();
		assertEquals(0, search.matches());
		assertFalse(search.errored());
	}
	public void testErroredSearch() throws IOException {
		final String badUrl = URL + "\\z3468.html";
		Search search = new Search(badUrl, "whatever");
		search.execute();
		assertTrue(search.errored());
		assertEquals(FileNotFoundException.class,
				search.getError().getClass());
	}
}
