package com.udemy.chaddarby.spring_boot_unit_testing.service;

import com.udemy.chaddarby.spring_boot_unit_testing.model.student.CollegeStudent;
import com.udemy.chaddarby.spring_boot_unit_testing.repository.StudentDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StudentService {
	private final StudentDao studentDao;

	public StudentService(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void createStudent(
		String firstName,
		String lastName,
		String email
	) {
		CollegeStudent student = new CollegeStudent(firstName, lastName, email);
		student.setId(1);
		studentDao.save(student);
	}

	public boolean isStudentNull(int id) {
		Optional<CollegeStudent> student = studentDao.findById(id);
		return student.isPresent();
	}
}
