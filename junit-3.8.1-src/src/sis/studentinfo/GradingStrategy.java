package sis.studentinfo;

import sis.studentinfo.Student.Grade;

public interface GradingStrategy {

	int gradePointsFor(Grade grade);

	int getGradePointsFor(Grade d);


}