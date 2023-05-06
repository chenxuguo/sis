package sis.ui;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;

import sis.studentinfo.Course;

public class Sis {
	static final int WIDTH = 300;
	static final int HEIGHT = 500;
	public static final String COURSES_TITLE = "Course Listing";
	
	private JFrame frame = new JFrame(COURSES_TITLE);
	private CoursesPanel panel;
	
	private void initialize() {
		createCoursesPanel();
		
		Image image = ImageUtil.create("/images/courses.gif");
		frame.setIconImage(image);
		
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		createKeyListener();
		createInputFilters();
	}
	void createKeyListener() {
		KeyListener listener = new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				setAddButtonState();
			}
		};
		panel.addFieldListener(CoursesPanel.DEPARTMENT_FIELD_NAME,
				listener);
		panel.addFieldListener(CoursesPanel.NUMBER_FIELD_NAME, listener);
		setAddButtonState();
	}
	
//	private void verifyEfectiveDate() {
//		assertLabeltext(EFFECTIVE_DATE_LABEL_NAME,
//			EFFECTIVE_DATE_LABEL_TEXT);
//		
//		JFormattedTextField dateField =
//				(JFormattedTextField)panel.getField(EFFECTIVE_DATE_FIELD_NAME);
//		DateFormatter formatter = (DateFormatter)dateField.getFormatter();
//		SimpleDateFormat format = (SimpleDateFormat)formatter.getFormat();
//		assertEquals("MM/dd/yy", format.toPattern());
//		assertEquals(Date.class, dateField.getValue().getClass());
//	}
	private void createInputFilters() {
		JTextField field =
			panel.getField(CoursesPanel.DEPARTMENT_FIELD_NAME);
		AbstractDocument document = (AbstractDocument)field.getDocument();
		document.setDocumentFilter(new UpcaseFilter());
		
		field =	panel.getField(CoursesPanel.NUMBER_FIELD_NAME);
		document = (AbstractDocument)field.getDocument();
		document.setDocumentFilter(new LimitFilter(3));
	}
	void setAddButtonState() {

		panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME,
				!isEmpty(CoursesPanel.DEPARTMENT_FIELD_NAME) &&
				!isEmpty(CoursesPanel.NUMBER_FIELD_NAME));
	}
	
	private boolean isEmpty(String field) {
		String value = panel.getText(field);
		return value.trim().equals("");
	}
	
	void createCoursesPanel() {
		panel = new CoursesPanel();
		panel.addCourseAddListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addCourse();
				}
			}
		);
	}
	// THIS IS AN INADEQUATE SOLUTION
	private void addCourse() {
		Thread thread = new Thread() {
			public void run() {
				panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME, false);
				final Course course = basicAddCourse();
				panel.addCourse(course);
				panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME, true);
			}			
		};
		thread.start();
	}
	
	private Course basicAddCourse() {
	try { Thread.sleep(3000); } catch (InterruptedException e) {}
		Course course =
			new Course(
				panel.getText(CoursesPanel.DEPARTMENT_FIELD_NAME),
				panel.getText(CoursesPanel.NUMBER_FIELD_NAME));
		JFormattedTextField effectiveDateField =
			(JFormattedTextField)panel.getField(
					FieldCatalog.EFFECTIVE_DATE_FIELD_NAME);
		Date date = (Date)effectiveDateField.getValue();
		course.setEffectiveDate(date);
		
		return course;
		 
	}
	
	public Sis() {
		initialize();
	}
	public static void main(String[] args) {
		new Sis().show();
	}
	
	void show() {
		frame.setVisible(true);
	}
	public JFrame getFrame() {
		return frame;
	}
	void close() {
		frame.dispose();
	}
}
