package com.udemy.chaddarby.spring_boot_unit_testing;

import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.Grade;
import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.HistoryGrade;
import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.MathGrade;
import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.ScienceGrade;
import com.udemy.chaddarby.spring_boot_unit_testing.model.student.CollegeStudent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class GradeBookApp {
	public static void main(String[] args) {
		SpringApplication.run(GradeBookApp.class, args);
	}

	@Bean
	@Scope(value = "prototype")
	CollegeStudent getCollegeStudent() {
		return new CollegeStudent();
	}

	@Bean
	@Scope(value = "prototype")
	Grade getMathGrade(double grade) {
		return new MathGrade(grade);
	}

	@Bean
	@Scope(value = "prototype")
	@Qualifier("mathGrades")
	MathGrade getGrade() {
		return new MathGrade();
	}

	@Bean
	@Scope(value = "prototype")
	@Qualifier("scienceGrades")
	ScienceGrade getScienceGrade() {
		return new ScienceGrade();
	}

	@Bean
	@Scope(value = "prototype")
	@Qualifier("historyGrades")
	HistoryGrade getHistoryGrade() {
		return new HistoryGrade();
	}
}
