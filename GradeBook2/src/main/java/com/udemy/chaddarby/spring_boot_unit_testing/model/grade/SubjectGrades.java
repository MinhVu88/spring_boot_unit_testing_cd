package com.udemy.chaddarby.spring_boot_unit_testing.model.grade;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class SubjectGrades {
	private List<Grade> mathGrades;
	private List<Grade> scienceGrades;
	private List<Grade> historyGrades;

	public SubjectGrades() {}

	public double totalGradesPerClass(List<Grade> grades) {
		double result = 0;

		for (Grade grade : grades) {
			result += grade.getGrade();
		}

		return result;
	}

	public double calculateAverageGrade(List<Grade> grades) {
		int numberOfGrades = grades.size();
		double sum = totalGradesPerClass(grades);
		double result = sum / numberOfGrades;
		BigDecimal resultRound = BigDecimal.valueOf(result);
		resultRound = resultRound.setScale(2, RoundingMode.HALF_UP);
		return resultRound.doubleValue();
	}

	public List<Grade> getMathGrades() {
		return mathGrades;
	}

	public void setMathGrades(List<Grade> mathGrades) {
		this.mathGrades = mathGrades;
	}

	public List<Grade> getScienceGrades() {
		return scienceGrades;
	}

	public void setScienceGrades(List<Grade> scienceGrades) {
		this.scienceGrades = scienceGrades;
	}

	public List<Grade> getHistoryGrades() {
		return historyGrades;
	}

	public void setHistoryGrades(List<Grade> historyGrades) {
		this.historyGrades = historyGrades;
	}

	@Override
	public String toString() {
		return "math grades: " + mathGrades +
					 " | science grades: " + scienceGrades +
					 " | history grades: " + historyGrades;
	}
}
