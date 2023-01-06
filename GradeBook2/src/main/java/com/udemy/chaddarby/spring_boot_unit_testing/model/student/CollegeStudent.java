package com.udemy.chaddarby.spring_boot_unit_testing.model.student;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class CollegeStudent implements Student {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column(name="email")
	private String email;

	public CollegeStudent() {}

	public CollegeStudent(
		String firstName,
		String lastName,
		String email
	) {
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

	public String getStudentName() {
		return "student's 1st name: " + getFirstName() +
					 " | last name: " + getLastName();
	}

	public String getStudentNameAndEmail() {
		return "student's full name: " + getStudentName() +
					 " | email: " + getEmail();
	}

	@Override
	public String toString() {
		return "student id: " + id +
					 " | first name: " + firstName +
					 " | last name: " + lastName +
					 " | email: " + email;
	}
}
