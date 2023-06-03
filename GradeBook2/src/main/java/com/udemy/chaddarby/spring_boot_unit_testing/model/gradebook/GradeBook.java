package com.udemy.chaddarby.spring_boot_unit_testing.model.gradebook;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GradeBook {
	private List<CollegeStudentGradeBook> students = new ArrayList<>();

	public GradeBook() {}

	public GradeBook(List<CollegeStudentGradeBook> students) {
		this.students = students;
	}

	public List<CollegeStudentGradeBook> getStudents() {
		return students;
	}

	public void setStudents(List<CollegeStudentGradeBook> students) {
		this.students = students;
	}
}
