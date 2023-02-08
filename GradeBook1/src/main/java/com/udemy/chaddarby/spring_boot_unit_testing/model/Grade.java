package com.udemy.chaddarby.spring_boot_unit_testing.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class Grade {
	List<Double> mathGrades;

	public Grade() {}

	public Grade(List<Double> mathGrades) {
		this.mathGrades = mathGrades;
  }

	public double totalGradesPerClass(List<Double> grades) {
		double result = 0;

		for(double grade : grades) {
			result += grade;
		}

		return result;
	}

	public double calculateAverageGrade(List<Double> grades) {
		double sum = totalGradesPerClass(grades);
		double result = sum / grades.size();
		BigDecimal resultRound = BigDecimal.valueOf(result);
		resultRound = resultRound.setScale(2, RoundingMode.HALF_UP);
		return resultRound.doubleValue();
	}

	public Boolean isGreaterThan(double grade1, double grade2) {
		return grade1 > grade2;
	}

	public Object checkNull(Object obj) {
		return obj;
	}

	public List<Double> getMathGrades() {
		return mathGrades;
	}

	public void setMathGrades(List<Double> mathGrades) {
		this.mathGrades = mathGrades;
	}

	@Override
	public String toString() {
		return "math grades: " + mathGrades;
	}
}
