package com.udemy.chaddarby.spring_boot_unit_testing.model.grade;

import javax.persistence.*;

@Entity
@Table(name = "math_grade")
public class MathGrade implements Grade {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int gradeId;
	@Column(name="student_id")
	private int studentId;
	@Column(name="grade")
	private double grade;

	public MathGrade() {

	}

	public MathGrade(double grade) {
		this.grade = grade;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Override
	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
}
