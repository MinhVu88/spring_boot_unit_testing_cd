package com.udemy.chaddarby.spring_boot_unit_testing.models;

public class CollegeStudent implements Student {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private Grade grade;

	public CollegeStudent() {}

	public CollegeStudent(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	private String getCollegeStudentCredentials() {
		return "Id: " + getId() + " | Name: " + getFirstName() + " " + getLastName();
	}

	@Override
	public String toString() {
		return "1st name: " + firstName +
		       " | last name: " + lastName +
					 " | email: " + email +
					 " | grades: " + grade;
	}

	@Override
	public String getStudentInfo() {
		return getFullName() + " " + getEmail();
	}

	@Override
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}
}
