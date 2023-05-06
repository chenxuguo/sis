package sis.util;

import junit.framework.*;
import sis.studentinfo.Course;
import sis.ui.CoursesPanel;
import sis.ui.CoursesTableModel;
import sis.ui.Field;
import sis.ui.FieldCatalog;
import sis.ui.StatusBar;
import sis.ui.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

import static sis.ui.CoursesPanel.*;

public class CoursesPanelTest extends TestCase{
	private CoursesPanel panel;
	private boolean wasClicked;
	
	protected void setUp() {
		panel = new CoursesPanel();
	}
	public void testCreate( ) {
		assertEmptyTable(COURSES_TABLE_NAME);
		//assertEmptyList(COURSES_LIST_NAME);
		assertButtonText(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);

		String[] fields =
			{	FieldCatalog.DEPARTMENT_FIELD_NAME,
				FieldCatalog.NUMBER_FIELD_NAME,
			FieldCatalog.EFFECTIVE_DATE_FIELD_NAME };
		assertFields(fields);
		
		JButton button = panel.getButton(ADD_BUTTON_NAME);
		assertEquals(CoursesPanel.ADD_BUTTON_MNEMONIC, button.getMnemonic());
	}
		
	private void assertFields(String[] fieldNames) {
		StatusBar statusBar =
			(StatusBar)Util.getComponent(panel, StatusBar.NAME);
		FieldCatalog catalog = new FieldCatalog();
		for (String fieldName: fieldNames) {
			JTextField field = panel.getField(fieldName);
			Field fieldSpec = catalog.get(fieldName);
			// assertNotNull(panel.getField(fieldName));
			// can't compare two JTextField items for equality,
			// so we must go on faith here that CoursesPanel
			// creates them using TextFieldFactory
			assertEquals(fieldSpec.getInfo(), statusBar.getInfo(field));
			assertLabelText(fieldSpec.getLabelName(), fieldSpec.getLabel());
		}
	}
	public void testAddButtonClick() {
		JButton button = panel.getButton(ADD_BUTTON_NAME);
		
		wasClicked = false;
		panel.addCourseAddListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wasClicked = true;
			}
		});
		button.doClick();
		assertTrue(wasClicked);
	}
	
	public void testAddCourse() {
		Course course = new Course("ENGL", "101");
		panel.addCourse(course);
		//JList list = panel.getList(COURSES_LIST_NAME);
		JTable table = panel.getTable(COURSES_TABLE_NAME);
		//ListModel model = list.getModel();
		CoursesTableModel model = (CoursesTableModel)table.getModel();
		//assertEquals("ENGL-101", model.getElementAt(0).toString());
		assertEquals(course, model.get(0));
	}
	
	private void assertEmptyTable(String name) {
		JTable table = panel.getTable(name);
		assertEquals(0, table.getModel().getRowCount());
	}
	
	public void testEnableDisable() {
		panel.setEnabled(ADD_BUTTON_NAME, true);
		JButton button = panel.getButton(ADD_BUTTON_NAME);
		assertTrue(button.isEnabled());
		
		panel.setEnabled(ADD_BUTTON_NAME, false);
		assertFalse(button.isEnabled());
	}
	
	public void testAddListener() throws Exception {
		KeyListener listener = new KeyAdapter() {};
		panel.addFieldListener(DEPARTMENT_FIELD_NAME, listener);
		JTextField field = panel.getField(DEPARTMENT_FIELD_NAME);
		KeyListener[] listeners = field.getKeyListeners();
		assertEquals(1, listeners.length);
		assertSame(listener, listeners[0]);
	}
	
	private void assertField(String[] fieldNames) {
		
		FieldCatalog catalog = new FieldCatalog();
		for (String fieldName: fieldNames) {
			assertNotNull(panel.getField(fieldName));
			Field fieldSpec = catalog.get(fieldName);
			
			assertLabelText(fieldSpec.getLabelName(), fieldSpec.getLabel());
		}
	}
	private void assertLabelText(String name, String text) {
		JLabel label = panel.getLabel(name);
		assertEquals(text, label.getText());
	}
	
	private void assertEmptyField(String name) {
		JTextField field = panel.getField(name);
		assertEquals("", field.getText());
	}
		
	private void assertEmptyList(String name) {
		JList list =  panel.getList(name);
		assertEquals(0, list.getModel().getSize());		
	}

	private void assertButtonText(String name, String text) {
		JButton button = panel.getButton(name);
		assertEquals(text, button.getText());
	}

//		
//		JButton button = 
//				(JButton)Util.getComponent(panel, ADD_BUTTON_NAME);
//		
//		JLabel departmentLabel =
//				(JLabel)Util.getComponent(panel, DEPARTMENT_LABEL_NAME);
//		assertEquals(DEPARTMENT_LABEL_TEXT, departmentLabel.getText());
//		
//		JTextField departmentField =
//				(JTextField)Util.getComponent(panel, DEPARTMENT_FIELD_NAME);
//		assertEquals("", departmentField.getText());
//		
//		JLabel numberLabel =
//				(JLabel)Util.getComponent(panel, NUMBER_LABEL_NAME);
//		assertEquals(NUMBER_LABEL_TEXT, numberLabel.getText());
//		
//		JTextField numberField =
//				(JTextField)Util.getComponent(panel,NUMBER_FIELD_NAME);
//		assertEquals("", numberField.getText());
}
