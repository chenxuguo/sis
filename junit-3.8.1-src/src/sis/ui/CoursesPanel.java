package sis.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.TableModel;

import sis.studentinfo.Course;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
//import java.io.ObjectInputFilter.Status;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.awt.GridBagConstraints.*;

public class CoursesPanel extends JPanel {
	static final String NAME = "coursesPanel";
	public static final String LABEL_TEXT = "Courses";
	public static final String LABEL_NAME = "coursesLabel";
	public static final String COURSES_LIST_NAME = "coursesList";
	public static final String ADD_BUTTON_TEXT = "Add";
	public static final String ADD_BUTTON_NAME = "addButton";
	public static final String DEPARTMENT_FIELD_NAME = "deptField";
	public static final String NUMBER_FIELD_NAME = "numberField";
	public static final String DEPARTMENT_LABEL_NAME = "deptLabel";
	public static final String NUMBER_LABEL_NAME = "numberLabel";
	public static final String DEPARTMENT_LABEL_TEXT = "Department";
	public static final String NUMBER_LABEL_TEXT = "Number";
	public static final String COURSES_LABEL_TEXT = "Courses";
	public static final String COURSES_LABEL_NAME = "coursesLabel";
	private JButton addButton;
	private  DefaultListModel coursesModel = new DefaultListModel();
	private CoursesTableModel coursesTableModel = new CoursesTableModel();
	public static final char ADD_BUTTON_MNEMONIC = 'A';
	private static final String EFFECTIVE_DATE_FIELD_NAME = "date";
	private static final String EFFECTIVE_DATE_LABEL_NAME = "dateLabel";
	private static final String EFFECTIVE_DATE_LABEL_TEXT = "date";
	public static final String COURSES_TABLE_NAME = "coursesTable";
	private StatusBar statusBar = new StatusBar();
	
	public static void main(String[] args) {
		show(new CoursesPanel());
	}
	
	
	private static void show(JPanel panel) {
		JFrame frame = new JFrame();
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	
	public CoursesPanel() {
		setName(NAME);
		createLayout();
	}
	
	private void createLayout() {
//		JLabel coursesLabel = 
//				createLabel(COURSES_LABEL_NAME, COURSES_LABEL_TEXT);
//		
//		JList coursesList = createList(COURSES_LIST_NAME, coursesModel);
		JTable coursesTable = createCoursesTable();
//		JScrollPane coursesScroll = new JScrollPane(coursesList);
		JScrollPane coursesScroll = new JScrollPane(coursesTable);

		coursesScroll.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setLayout(new BorderLayout());
		
		final int pad = 6;
		Border emptyBorder = 
				BorderFactory.createEmptyBorder(pad, pad, pad, pad);
		Border bevelBorder =
				BorderFactory.createBevelBorder(BevelBorder.RAISED);
		Border titledBorder =
				BorderFactory.createTitledBorder(bevelBorder, COURSES_LABEL_TEXT);
				
		setBorder(BorderFactory.createCompoundBorder(emptyBorder, 
				titledBorder));
		//add(coursesLabel, BorderLayout.NORTH);
		add(coursesScroll, BorderLayout.CENTER);
		add(createBottomPanel(), BorderLayout.SOUTH);
			
	}
	
	JPanel createBottomPanel() {
		//JLabel statusBar = new StatusBar();
		statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
		//status = new Status(statusBar);
		
		addButton = createButton(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);
		addButton.setMnemonic(ADD_BUTTON_MNEMONIC);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(addButton, BorderLayout.NORTH);
		//panel.add(createFieldsPanel(), BorderLayout.SOUTH);
		panel.add(createFieldsPanel(), BorderLayout.CENTER);
		panel.add(statusBar, BorderLayout.SOUTH);
		//panel.add(createInputPanel(), BorderLayout.CENTER);
		return panel;
	}
	
	private JTable createCoursesTable() {
		JTable table = new JTable(coursesTableModel);
		table.setName(COURSES_TABLE_NAME);
		table.setShowGrid(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return table;
	}
//	JPanel createBottomPanel() {
//		addButton = createButton(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);
//		
//		JPanel panel = new JPanel();
//		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
//		
//		panel.add(Box.createRigidArea(new Dimension(0, 4)));
//		addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//		panel.add(addButton);
//		panel.add(Box.createRigidArea(new Dimension(0,4)));
//		panel.add(createFieldsPanel());
//		
//		panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
//		return panel;
//	}

	JPanel createFieldsPanel() {
		GridBagLayout layout = new GridBagLayout();
		
		JPanel panel = new JPanel(layout);
		int i = 0;
		FieldCatalog catalog = new FieldCatalog();
		
		for (String fieldName: getFieldNames()) {
			Field fieldSpec = catalog.get(fieldName);
			JTextField textField = TextFieldFactory.create(fieldSpec);
			addField(panel, layout, i++, 
					createLabel(fieldSpec),
					textField);
			statusBar.setInfo(textField, fieldSpec.getInfo());
		}
		
		//		int columns = 20;
//		
//		addField(panel, layout, 0,
//				DEPARTMENT_LABEL_NAME, DEPARTMENT_LABEL_TEXT,
//				DEPARTMENT_FIELD_NAME, columns);
//		addField(panel, layout, 1,
//				NUMBER_LABEL_NAME, NUMBER_LABEL_TEXT,
//				NUMBER_FIELD_NAME, columns);
//		
//		Format format = new SimpleDateFormat("MM/dd/yy");
//		JFormattedTextField dateField = new JFormattedTextField(format);
//		dateField.setValue(new Date());
//		dateField.setColumns(columns);
//		dateField.setName(EFFECTIVE_DATE_FIELD_NAME);
//		
//		addField(panel, layout, 2, 
//				EFFECTIVE_DATE_LABEL_NAME, EFFECTIVE_DATE_LABEL_TEXT,
//				dateField);
		
		return panel;

	}

	private JLabel createLabel(Field fieldSpec) {
		JLabel label = new JLabel(fieldSpec.getLabelText());
		label.setName(fieldSpec.getLabelText());
		return label;
	}


	private String[] getFieldNames() {
		return new String[]
			{	FieldCatalog.DEPARTMENT_FIELD_NAME,
				FieldCatalog.NUMBER_FIELD_NAME,
				FieldCatalog.EFFECTIVE_DATE_FIELD_NAME };			
	}
	
	private void addField(
			JPanel panel, GridBagLayout layout, int row,
			JLabel label, JTextField field) {
		
		Insets insets = new Insets(3, 3, 3, 3);	// top-level-bottom-right
		layout.setConstraints(label,
				new GridBagConstraints(
				0, row,	// x, y
				1, 1,	// gridwidth, gridheight
				40, 1,	// weightx, weighty
				LINE_END, 	// anchor
				NONE,	// fill
				insets,	// top-left-bottom-right
				0, 0));	// padx, ipady
		layout.setConstraints(field, 
				new GridBagConstraints(1, row, 2, 1, 60, 1,
						CENTER, HORIZONTAL,
						insets, 0, 0));
		panel.add(label);
		panel.add(field);
	}
	
	public void addCourseAddListener(ActionListener actionListener) {
		addButton.addActionListener(actionListener);
	}
	
	
	private JLabel createLabel(String name, String text) {
		JLabel label = new JLabel(text);
		label.setName(name);
		return label;
		
	}
	
	public void addCourse(Course course) {
		//coursesModel.addElement(new CourseDisplayAdapter(course));
		coursesTableModel.add(course);
	}
	
	private JList createList(String name, ListModel model) {
		JList list = new JList(model);
		list.setName(name);
		return list;
	}
	
	private JButton createButton(String name, String text) {
		JButton button = new JButton(text);
		button.setName(name);
		return button;
	}
	
	private JTextField createField(String name, int columns) {
		JTextField field = new JTextField(columns);
		field.setName(name);
		return field;
	}
	
	public JLabel getLabel(String name) {
		return (JLabel)Util.getComponent(this, name);
	}
	
	public JList getList(String name) {
		return (JList)Util.getComponent(this, name);
	}
	
	public JButton getButton(String name) {
		return (JButton)Util.getComponent(this, name);
	}
	
	public JTextField getField(String name) {
		return (JTextField)Util.getComponent(this, name);
	}
	
	public Course getCourse(int index) {
//		Course adapter = (CourseDisplayAdapter)coursesModel.getElementAt(index);
//		return adapter;
		return coursesTableModel.get(index);
	}
	
	void setText(String textFieldName, String text) {
		getField(textFieldName).setText(text);
	}

	public String getText(String textFieldName) {
		// TODO Auto-generated method stub
		return getField(textFieldName).getText();
	}


	public void addFieldListener(String fieldName, KeyListener listener) {
		getField(fieldName).addKeyListener(listener);
		
	}


	public void setEnabled(String name, boolean state) {
		getButton(name).setEnabled(state);;
		
	}


	public JTable getTable(String coursesTableName) {
		// TODO Auto-generated method stub
		return (JTable)Util.getComponent(this, coursesTableName);
	}


	public int getCoursesCount() {
		// TODO Auto-generated method stub
		return coursesTableModel.getRowCount();
	}
}
