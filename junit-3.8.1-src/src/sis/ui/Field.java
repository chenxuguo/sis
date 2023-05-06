package sis.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.DateFormatter;

public class Field{

	String fieldInfo;
	String fieldName;
	int columns;
	String labelText;
	Object initialValue;
	String initialString;
	int limit;
	boolean upcaseOnly = false;
	DateFormat format;
	
	public Field(String name){
		fieldName = name;
	}

	public void setInfo(String fieldInfo) {
		this.fieldInfo = fieldInfo;
	}
	public String getInfo() {
		return fieldInfo;
	}
	public void setLabel(String labelText) {
		// TODO Auto-generated method stub
		this.labelText = labelText;
	}
	public String getLabel() {
		return labelText;
	}
	public void setLimit(int fieldLimit) {
		// TODO Auto-generated method stub
		this.limit = fieldLimit;
	}

	public int getLimit() {
		return limit;
	}
	
	public void setColumns(int columns) {
		// TODO Auto-generated method stub
		this.columns = columns;
	}
	public int getColumns() {
		return columns;
	}
	public void setUpcaseOnly() {
		// TODO Auto-generated method stub
		upcaseOnly = true;
	}
	public boolean isUpcaseOnly() {
		return upcaseOnly;
	}
	public void setFormat(DateFormat dateFormat) {
		format = dateFormat;
		
	}
	public DateFormat getFormat() {
		return format;
	}
//	public void setInitialValue(Date date) {
//		// TODO Auto-generated method stub
//		this.initialValue = date; 
//	}
	public void setInitialValue(Object initialValue) {
		this.initialValue = initialValue;
	}
	public Object getInitialValue() {
		return this.initialValue;
	}
	
	public void setName(String name) {
		this.fieldName =  name;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return fieldName;
	}

//	public Date getInitialValue() {
//		// TODO Auto-generated method stub
//		return initialValue;
//	}
	public String getText() {
		// TODO Auto-generated method stub
		return initialString;
	}

	public String getLabelName() {
		// TODO Auto-generated method stub
		return labelText;
	}

	public String getLabelText() {
		// TODO Auto-generated method stub
		return labelText;
	}

	public String getShortName() {
		// TODO Auto-generated method stub
		return fieldName;
	}

}
