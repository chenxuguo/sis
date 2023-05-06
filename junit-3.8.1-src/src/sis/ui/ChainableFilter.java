package sis.ui;

import javax.swing.text.DocumentFilter;

public class ChainableFilter extends DocumentFilter {
	private DocumentFilter next;
	public void setNext(DocumentFilter filter) {
		this.next = filter;
	}
	public DocumentFilter getNext() {
		return next;
	}
}

