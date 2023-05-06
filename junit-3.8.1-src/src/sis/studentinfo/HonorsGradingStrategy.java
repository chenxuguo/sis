package sis.studentinfo;

import sis.studentinfo.Student.Grade;

public class HonorsGradingStrategy extends BasicGradingStrategy {
	//public HonorsGradingStrategy() {};
	@Override
	public int gradePointsFor(Grade grade) {
		int points = basicGradePointsFor(grade);
		if (points > 0)
			points += 1;
		return points;
	}
	@Override
	public int getGradePointsFor(Grade grade) {
		int points = super.getGradePointsFor(grade);
		if (points > 0)
			points += 1;
		return points;		
	}
}

