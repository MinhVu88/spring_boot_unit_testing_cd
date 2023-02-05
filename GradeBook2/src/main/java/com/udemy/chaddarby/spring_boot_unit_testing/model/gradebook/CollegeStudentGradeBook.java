// CollegeStudentGradeBook contains student credentials & a list of grades for each student.
package com.udemy.chaddarby.spring_boot_unit_testing.model.gradebook;

import com.udemy.chaddarby.spring_boot_unit_testing.model.student.CollegeStudent;

public class CollegeStudentGradeBook extends CollegeStudent {
	private int id;

	private SubjectGradeBook subjectGradeBook;

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
		SubjectGradeBook subjectGradeBook
	) {
		super(firstName, lastName, email);
		this.subjectGradeBook = subjectGradeBook;
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

	public SubjectGradeBook getStudentGrades() {
		return subjectGradeBook;
	}

	public void setStudentGrades(SubjectGradeBook subjectGradeBook) {
		this.subjectGradeBook = subjectGradeBook;
	}
}
