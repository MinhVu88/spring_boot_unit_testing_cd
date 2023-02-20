package com.udemy.chaddarby.spring_boot_unit_testing.model.gradebook;

import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.SubjectGrades;
import com.udemy.chaddarby.spring_boot_unit_testing.model.student.CollegeStudent;

public class CollegeStudentGradeBook extends CollegeStudent {
	private int id;
	private SubjectGrades subjectGrades;

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
		SubjectGrades subjectGrades
	) {
		super(firstName, lastName, email);
		this.subjectGrades = subjectGrades;
		this.id = id;
	}

	public SubjectGrades getStudentGrades() {
		return subjectGrades;
	}

	public void setStudentGrades(SubjectGrades subjectGrades) {
		this.subjectGrades = subjectGrades;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}
}
