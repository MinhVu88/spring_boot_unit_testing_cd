package com.udemy.chaddarby.spring_boot_unit_testing.model.gradebook;

import com.udemy.chaddarby.spring_boot_unit_testing.model.student.CollegeStudent;
import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.StudentGrades;

public class CollegeStudentGradeBook extends CollegeStudent {
	private int id;

	private StudentGrades studentGrades;

	public CollegeStudentGradeBook(
		String firstName,
		String lastName,
		String email
	) {
		super(firstName, lastName, email);
	}

	public CollegeStudentGradeBook(
		int id,
		String firstName,
		String lastName,
		String email,
		StudentGrades studentGrades
	) {
		super(firstName, lastName, email);
		this.studentGrades = studentGrades;
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public StudentGrades getStudentGrades() {
		return studentGrades;
	}

	public void setStudentGrades(StudentGrades studentGrades) {
		this.studentGrades = studentGrades;
	}
}
