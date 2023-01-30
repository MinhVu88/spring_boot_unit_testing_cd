package com.udemy.chaddarby.spring_boot_unit_testing.service;

import com.udemy.chaddarby.spring_boot_unit_testing.model.student.CollegeStudent;
import com.udemy.chaddarby.spring_boot_unit_testing.repository.StudentDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentGradeService {
	private final StudentDao studentDao;

	public StudentGradeService(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void createStudent(
		String firstName,
		String lastName,
		String email
	) {
		CollegeStudent newStudent = new CollegeStudent(firstName, lastName, email);

		newStudent.setId(0);

		studentDao.save(newStudent);
	}

	public boolean isStudentNull(int studentId) {
		return studentDao.findById(studentId).isPresent();
	}
}
