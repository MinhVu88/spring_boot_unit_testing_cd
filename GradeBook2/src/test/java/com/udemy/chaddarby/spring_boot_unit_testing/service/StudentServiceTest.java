package com.udemy.chaddarby.spring_boot_unit_testing.service;

import com.udemy.chaddarby.spring_boot_unit_testing.GradeBookApp2;
import com.udemy.chaddarby.spring_boot_unit_testing.model.student.CollegeStudent;
import com.udemy.chaddarby.spring_boot_unit_testing.repository.StudentDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = GradeBookApp2.class)
@TestPropertySource("/application-test.properties")
public class StudentServiceTest {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentDao studentDao;

	@BeforeEach
	void setUp() {
		jdbcTemplate.execute(
			"insert into student(id, email, first_name, last_name)" +
			"values (1, 'keenan@tool.com', 'Maynard', 'Keenan')"
		);
	}

	@AfterEach
	void cleanUp() {
		jdbcTemplate.execute("delete from student");
	}

	@Test
	@DisplayName("test createStudent()")
	void testCreateStudent() {
		String firstName = "Nikola";
		String lastName = "Tesla";
		String email = "tesla@genius.com";

		studentService.createNewStudent(firstName, lastName, email);

		CollegeStudent collegeStudent = studentDao.findByEmail(email);

		assertEquals(
			email,
			collegeStudent.getEmail(),
			"new student created & found by email"
		);
	}

	@Test
	@DisplayName("test isStudentExistent()")
	void testIsStudentExistent() {
		assertTrue(studentService.isStudentExistent(1));

		assertFalse(studentService.isStudentExistent(0));
	}

	@Test
	@Sql("/db/insertData.sql")
	@DisplayName("test getStudents()")
	void testGetStudents() {
		int numberOfCurrentCollegeStudents = 5;

		Iterable<CollegeStudent> collegeStudents = studentService.getStudents();

		List<CollegeStudent> studentList = new ArrayList<>();

		for(CollegeStudent collegeStudent : collegeStudents) {
			studentList.add(collegeStudent);
		}

		assertEquals(numberOfCurrentCollegeStudents, studentList.size());
	}

	@Test
	@DisplayName("test deleteStudent()")
	void testDeleteStudent() {
		int studentId = 1;

		Optional<CollegeStudent> collegeStudent = studentDao.findById(studentId);

		assertTrue(collegeStudent.isPresent(), "collegeStudent exists");

		studentService.deleteStudent(studentId);

		Optional<CollegeStudent> removedCollegeStudent = studentDao.findById(studentId);

		assertFalse(removedCollegeStudent.isPresent(), "collegeStudent removed");
	}
}
