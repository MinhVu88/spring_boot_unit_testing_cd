package com.udemy.chaddarby.spring_boot_unit_testing.service;

import com.udemy.chaddarby.spring_boot_unit_testing.model.student.CollegeStudent;
import com.udemy.chaddarby.spring_boot_unit_testing.repository.StudentDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StudentService {
	private StudentDao studentDao;

	public StudentService(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void createNewStudent(
		String firstName,
		String lastName,
		String email
	) {
		CollegeStudent student = new CollegeStudent(firstName, lastName, email);
		student.setId(0);
		this.studentDao.save(student);
	}

	public boolean isStudentExistent(int studentId) {
		Optional<CollegeStudent> student = this.studentDao.findById(studentId);
		return student.isPresent();
	}

	public Iterable<CollegeStudent> getStudents() {
		return this.studentDao.findAll();
	}

	public void deleteStudent(int studentId) {
		if(isStudentExistent(studentId)) {
			this.studentDao.deleteById(studentId);
		}
	}
}
