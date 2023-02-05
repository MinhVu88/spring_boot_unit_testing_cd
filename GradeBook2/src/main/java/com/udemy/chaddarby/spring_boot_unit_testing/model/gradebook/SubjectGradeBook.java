// SubjectGradeBook (the grade list) contains grades for each subject
package com.udemy.chaddarby.spring_boot_unit_testing.model.gradebook;

import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.Grade;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class SubjectGradeBook {
	private List<Grade> mathGradeResults;

	private List<Grade> scienceGradeResults;

	private List<Grade> historyGradeResults;

	public SubjectGradeBook() {}

	public double totalGradesPerClass(List<Grade> grades) {
		double result = 0;

		for(Grade grade : grades) {
			result += grade.getGrade();
		}

		return result;
	}

	public double calculateAverageGrade(List<Grade> grades) {
		int numberOfGrades = grades.size();
		double sum = totalGradesPerClass(grades);
		double result = sum / numberOfGrades;

		// add a round function
		BigDecimal resultRound = BigDecimal.valueOf(result);
		resultRound = resultRound.setScale(2, RoundingMode.HALF_UP);
		return resultRound.doubleValue();
	}

	public List<Grade> getMathGradeResults() {
		return mathGradeResults;
	}

	public void setMathGradeResults(List<Grade> mathGradeResults) {
		this.mathGradeResults = mathGradeResults;
	}

	public List<Grade> getScienceGradeResults() {
		return scienceGradeResults;
	}

	public void setScienceGradeResults(List<Grade> scienceGradeResults) {
		this.scienceGradeResults = scienceGradeResults;
	}

	public List<Grade> getHistoryGradeResults() {
		return historyGradeResults;
	}

	public void setHistoryGradeResults(List<Grade> historyGradeResults) {
		this.historyGradeResults = historyGradeResults;
	}

	@Override
	public String toString() {
		return "math grade results: " + mathGradeResults +
					 "science grade results: " + scienceGradeResults +
					 "history grade results: " + historyGradeResults;
	}
}
