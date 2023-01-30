package com.udemy.chaddarby.spring_boot_unit_testing.service;

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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application.properties")
public class StudentGradeServiceTest {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private StudentGradeService studentService;

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
	@DisplayName("test creating a new student in db")
	void testCreatingStudent() {
		String firstName = "Maynard";
		String lastName = "Keenan";
		String email = "keenan@tool.com";

		studentService.createStudent(firstName, lastName, email);

		CollegeStudent collegeStudent = studentDao.findByEmail(email);

		assertEquals(
			email,
			collegeStudent.getEmail(),
			"new student created & found by email"
		);
	}

	@Test
	@DisplayName("test if a student record is null")
	void testIsStudentNull() {
		assertTrue(studentService.isStudentNull(1));

		assertFalse(studentService.isStudentNull(0));
	}
}
