package sis.ui;

import java.awt.Point;
import java.awt.Robot;
import junit.framework.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import sis.util.*;
import javax.swing.JPanel;

public class StatusBarTest extends TestCase {
	private JTextField field1;
	private JTextField field2;
	private StatusBar  statusBar;
	private JFrame frame;
	
	protected void setUp() {
		field1 = new JTextField(10);
		field2 = new JTextField(10);
		statusBar = new StatusBar();
		
		JPanel panel = SwingUtil.createPanel(field1, field2, statusBar);
		frame = SwingUtil.createFrame(panel);
	}
	
	protected void tearDown() {
		frame.dispose();
	}
	
	public void testMousever() throws Exception {
		final String text1 = "text1";
		final String text2 = "text2";
		
		statusBar.setInfo(field1, text1);
		statusBar.setInfo(field2, text2);
		
		Robot robot = new Robot();
		
		Point field1Location = field1.getLocationOnScreen();
		
		robot.mouseMove(field1Location.x - 1, field1Location.y - 1);
		assertEquals("", statusBar.getText().trim());
		
		robot.mouseMove(field1Location.x + 1, field1Location.y + 1);
		robot.delay(100);
		assertEquals(text1, statusBar.getText());
		
		Point field2Location = field2.getLocationOnScreen();
		
		robot.mouseMove(field2Location.x + 1, field2Location.y + 1);
		robot.delay(100);
		assertEquals(text2, statusBar.getText());
	}
}
