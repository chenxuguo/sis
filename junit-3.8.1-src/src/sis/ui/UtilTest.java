package sis.ui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import junit.framework.TestCase;

public class UtilTest extends TestCase {
	private JPanel panel;
	
	protected void setUp() {
		panel = new JPanel();
	}
	
	public void testNotfound() {
		assertNull(Util.getComponent(panel, "abc"));
	}
	
	public void testDirectlyEmbeddedComponent() {
		final String name = "a";
		Component component = new JLabel("x");
		component.setName(name);
		panel.add(component);
		assertEquals(component, Util.getComponent(panel, name));	
	}
	
	public void testSubcomponent() {
		final String name = "a";
		Component component = new JLabel("x");
		component.setName(name);
		
		JPanel subpanel = new JPanel();
		subpanel.add(component);
		
		panel.add(subpanel);
		
		assertEquals(component, Util.getComponent(panel, name));
	}
}