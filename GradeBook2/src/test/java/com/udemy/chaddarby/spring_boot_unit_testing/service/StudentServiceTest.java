package com.udemy.chaddarby.spring_boot_unit_testing.service;

import com.udemy.chaddarby.spring_boot_unit_testing.GradeBookApp2;
import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.HistoryGrade;
import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.MathGrade;
import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.ScienceGrade;
import com.udemy.chaddarby.spring_boot_unit_testing.model.gradebook.CollegeStudentGradeBook;
import com.udemy.chaddarby.spring_boot_unit_testing.model.student.CollegeStudent;
import com.udemy.chaddarby.spring_boot_unit_testing.repository.HistoryGradeDao;
import com.udemy.chaddarby.spring_boot_unit_testing.repository.MathGradeDao;
import com.udemy.chaddarby.spring_boot_unit_testing.repository.ScienceGradeDao;
import com.udemy.chaddarby.spring_boot_unit_testing.repository.StudentDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = GradeBookApp2.class)
@TestPropertySource("/application.properties")
public class StudentServiceTest {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private MathGradeDao mathGradeDao;

	@Autowired
	private ScienceGradeDao scienceGradeDao;

	@Autowired
	private HistoryGradeDao historyGradeDao;

	@Value("${sql.script.create.student}")
	private String newStudent;

	@Value("${sql.script.delete.student}")
	private String removedStudent;

	@Value("${sql.script.create.math.grade}")
	private String newMathGrade;

	@Value("${sql.script.delete.math.grade}")
	private String removedMathGrade;

	@Value("${sql.script.create.science.grade}")
	private String newScienceGrade;

	@Value("${sql.script.delete.science.grade}")
	private String removedScienceGrade;

	@Value("${sql.script.create.history.grade}")
	private String newHistoryGrade;

	@Value("${sql.script.delete.history.grade}")
	private String removedHistoryGrade;

	@BeforeEach
	void setUp() {
		/*
		jdbcTemplate.execute(
			"insert into student(id, email, first_name, last_name)" +
			"values (1, 'keenan@tool.com', 'Maynard', 'Keenan')"
		);

		jdbcTemplate.execute(
			"insert into math_grade(id, student_id, grade)" +
			"values (1, 1, 100.00)"
		);

		jdbcTemplate.execute(
			"insert into science_grade(id, student_id, grade)" +
			"values (1, 1, 100.00)"
		);

		jdbcTemplate.execute(
			"insert into history_grade(id, student_id, grade)" +
			"values (1, 1, 100.00)"
		);
		*/

		this.jdbcTemplate.execute(newStudent);
		this.jdbcTemplate.execute(newMathGrade);
		this.jdbcTemplate.execute(newScienceGrade);
		this.jdbcTemplate.execute(newHistoryGrade);
	}

	@AfterEach
	void cleanUp() {
		/*
		jdbcTemplate.execute("delete from student");
		jdbcTemplate.execute("delete from math_grade");
		jdbcTemplate.execute("delete from science_grade");
		jdbcTemplate.execute("delete from history_grade");
		*/

		this.jdbcTemplate.execute(removedStudent);
		this.jdbcTemplate.execute(removedMathGrade);
		this.jdbcTemplate.execute(removedScienceGrade);
		this.jdbcTemplate.execute(removedHistoryGrade);
	}

	@Test
	@DisplayName("test createStudent()")
	void testCreateStudent() {
		String firstName = "Nikola";
		String lastName = "Tesla";
		String email = "tesla@genius.com";

		studentService.createNewStudent(firstName, lastName, email);

		CollegeStudent collegeStudent = this.studentDao.findByEmail(email);

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
	@DisplayName("test getStudent()")
	void testGetStudent() {
		int studentId = 1;
		String firstName = "Eric";
		String lastName = "Roby";
		String email = "eric.roby@luv2code_school.com";

		CollegeStudentGradeBook collegeStudentGradeBook = this.studentService.getStudent(studentId);

		assertNotNull(collegeStudentGradeBook);

		assertEquals(studentId, collegeStudentGradeBook.getId());
		assertEquals(firstName, collegeStudentGradeBook.getFirstName());
		assertEquals(lastName, collegeStudentGradeBook.getLastName());
		assertEquals(email, collegeStudentGradeBook.getEmail());

		assertTrue(collegeStudentGradeBook.getStudentGrades().getMathGrades().size() == 1);
		assertTrue(collegeStudentGradeBook.getStudentGrades().getScienceGrades().size() == 1);
		assertTrue(collegeStudentGradeBook.getStudentGrades().getHistoryGrades().size() == 1);
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
		int gradeId = 1;

		Optional<CollegeStudent> collegeStudent = this.studentDao.findById(studentId);
		Optional<MathGrade> mathGrade = this.mathGradeDao.findById(gradeId);
		Optional<ScienceGrade> scienceGrade = this.scienceGradeDao.findById(gradeId);
		Optional<HistoryGrade> historyGrade = this.historyGradeDao.findById(gradeId);

		assertTrue(collegeStudent.isPresent(), "collegeStudent exists");
		assertTrue(mathGrade.isPresent());
		assertTrue(scienceGrade.isPresent());
		assertTrue(historyGrade.isPresent());

		studentService.deleteStudent(studentId);

		Optional<CollegeStudent> removedCollegeStudent = this.studentDao.findById(studentId);
		Optional<MathGrade> removedMathGrade = this.mathGradeDao.findById(gradeId);
		Optional<ScienceGrade> removedScienceGrade = this.scienceGradeDao.findById(gradeId);
		Optional<HistoryGrade> removedHistoryGrade = this.historyGradeDao.findById(gradeId);

		assertFalse(removedCollegeStudent.isPresent(), "college student removed");
		assertFalse(removedMathGrade.isPresent(), "math grade removed");
		assertFalse(removedScienceGrade.isPresent(), "science grade removed");
		assertFalse(removedHistoryGrade.isPresent(), "history grade removed");
	}

	@Test
	void testInvalidStudent() {
		int studentId = 0;
		CollegeStudentGradeBook collegeStudentGradeBook = this.studentService.getStudent(studentId);
		
		assertNull(collegeStudentGradeBook);
	}

	@Test
	@DisplayName("test saveGrade()")
	void testSaveGrade() {
		String subject1 = "math";
		String subject2 = "science";
		String subject3 = "history";
		double grade = 80.5;
		int studentId = 1;

		assertTrue(studentService.saveGrade(grade, studentId, subject1));
		assertTrue(studentService.saveGrade(grade, studentId, subject2));
		assertTrue(studentService.saveGrade(grade, studentId, subject3));

		Iterable<MathGrade> mathGrades = mathGradeDao.findMathGradeByStudentId(studentId);
		Iterable<ScienceGrade> scienceGrades = scienceGradeDao.findScienceGradeByStudentId(studentId);
		Iterable<HistoryGrade> historyGrades = historyGradeDao.findHistoryGradeByStudentId(studentId);		

		assertTrue(mathGrades.iterator().hasNext(), "math grades are available");
		assertTrue(
			((Collection<MathGrade>) mathGrades).size() == 2, 
			"student has 2 math gardes"
		);

		assertTrue(scienceGrades.iterator().hasNext(), "science grades are available");
		assertTrue(
			((Collection<ScienceGrade>) scienceGrades).size() == 2, 
			"student has 2 science gardes"
		);

		assertTrue(historyGrades.iterator().hasNext(), "history grades are available");
		assertTrue(
			((Collection<HistoryGrade>) historyGrades).size() == 2, 
			"student has 2 history gardes"
		);
	}

	@Test
	void testInvalidGrade() {
		int invalidGrade1 = 105;
		int invalidGrade2 = -5;
		assertFalse(studentService.saveGrade(invalidGrade1, 1, "math"));
		assertFalse(studentService.saveGrade(invalidGrade2, 1, "math"));

		int invalidStudentId = 2;
		assertFalse(studentService.saveGrade(80.5, invalidStudentId, "math"));

		String invalidSubject = "sport";
		assertFalse(studentService.saveGrade(80.5, 1, invalidSubject));
	}

	@Test
	@DisplayName("test deleteGrade()")
	void testDeleteGrade() {
		int studentId = 1;
		int gradeId = 1;
		String subject1 = "math";
		String subject2 = "science";
		String subject3 = "history";

		assertEquals(
			studentId,
			studentService.deleteGrade(gradeId, subject1),
			"student id returned after math grade removed"
		);

		assertEquals(
			studentId,
			studentService.deleteGrade(gradeId, subject2),
			"student id returned after science grade removed"
		);

		assertEquals(
			studentId,
			studentService.deleteGrade(gradeId, subject3),
			"student id returned after history grade removed"
		);
	}

	@Test
	void testDeletingInvalidGrade() {
		int studentId = 0;
		int gradeId = 1;
		int invalidGradeId = 0;
		String subject = "math";
		String invalidSubject = "sport";

		assertEquals(
			studentId,
			studentService.deleteGrade(invalidGradeId, subject),
			"student id is 0, due to invalid grade id"
		);

		assertEquals(
			studentId,
			studentService.deleteGrade(gradeId, invalidSubject),
			"student id is 0, due to invalid subject"
		);
	}
}
