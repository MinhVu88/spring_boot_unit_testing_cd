package com.udemy.chaddarby.spring_boot_unit_testing.model.grade;

public interface Grade {
	double getGrade();

	int getId();

	void setId(int id);

	int getStudentId();

	void setStudentId(int studentId);

	void setGrade(double grade);
}
