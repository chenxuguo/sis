package sis.studentinfo;

import java.io.Serializable;

import sis.studentinfo.Student.Grade;

public class BasicGradingStrategy implements GradingStrategy,Serializable{

//	public BasicGradingStrategy() {
//		super();
//	}
//	
	public int gradePointsFor(Student.Grade grade) {
		return basicGradePointsFor(grade);
	}
	public int basicGradePointsFor(Student.Grade grade) {
		return grade.getPoints();
	}

	public  int getGradePointsFor(Grade grade) {
		return basicGradePointsFor(grade);
	}
}