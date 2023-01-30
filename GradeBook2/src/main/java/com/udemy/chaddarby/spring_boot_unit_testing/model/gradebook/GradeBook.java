package com.udemy.chaddarby.spring_boot_unit_testing.model.gradebook;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GradeBook {
	private List<CollegeStudentGradeBook> collegeStudents = new ArrayList<>();

	public GradeBook() {}

	public GradeBook(List<CollegeStudentGradeBook> collegeStudents) {
		this.collegeStudents = collegeStudents;
	}

	public List<CollegeStudentGradeBook> getCollegeStudents() {
		return collegeStudents;
	}

	public void setCollegeStudents(List<CollegeStudentGradeBook> collegeStudents) {
		this.collegeStudents = collegeStudents;
	}
}
