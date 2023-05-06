package sis.ui;

import javax.swing.*;
import javax.swing.text.*;
import junit.framework.*;

public class UpcaseFilterTest extends TestCase {
	private DocumentFilter filter;
	protected DocumentFilter.FilterBypass byPass;
	protected AbstractDocument document;
	
	protected void setUp() {
		byPass = createByPass();
		document = (AbstractDocument)byPass.getDocument();
		filter = new UpcaseFilter();
	}
	
	public void testInsert() throws BadLocationException {
		filter.insertString(byPass,  0, "abc", null);
		assertEquals("ABC", documentText());
		
		filter.insertString(byPass, 1, "def", null);
		assertEquals("ADEFBC", documentText());	
	}
	
	protected String documentText() throws BadLocationException {
		return document.getText(0, document.getLength());
	}
	
	protected DocumentFilter.FilterBypass createByPass() {
		return new DocumentFilter.FilterBypass() {
			private AbstractDocument document = new PlainDocument();
			public Document getDocument() {
				return document;
			}
			public void insertString(
					int offset, String string, AttributeSet attr) {
				try {
					document.insertString(offset, string, attr);
				}
				catch (BadLocationException e) { }
			}
			public void remove(int offset, int length) {}
			public void replace(int offset, 
					int length, String string, AttributeSet attrs) {
				try {
					document.replace(offset,  length,  string,  attrs);
				}
				catch (BadLocationException e) {}
			}
		};
	}
	public void testReplace() throws BadLocationException {
		filter.insertString(byPass,  0, "XYX", null);
		filter.replace(byPass,  1, 2, "tc", null);
		assertEquals("XTC", documentText());
		
		filter.replace(byPass,  0, 3, "p8A", null);
		assertEquals("P8A", documentText());	
	}
}
