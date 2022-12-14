package com.udemy.chaddarby.spring_boot_unit_testing;

import com.udemy.chaddarby.spring_boot_unit_testing.models.CollegeStudent;
import com.udemy.chaddarby.spring_boot_unit_testing.models.Grade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GradeTest {
	private static int counter = 0;

	@Value("${info.app.name}")
	private String appName;

	@Value("${info.app.description}")
	private String appDescription;

	@Value("${info.app.version}")
	private String appVersion;

	@Value("${info.school.name}")
	private String schoolName;

	@Autowired
	ApplicationContext context;

	@Autowired
	CollegeStudent collegeStudent;

	@Autowired
	Grade grade;

	@BeforeEach
	void beforeEach() {
		counter = counter + 1;

		System.out.println(
			"testing app " + appName +
			" | description: " + appDescription +
			" | version: " + appVersion +
			" | school: " + schoolName +
			" | test method #" + counter
		);

		grade.setMathGrades(new ArrayList<>(Arrays.asList(100.0, 85.0, 76.5, 91.75)));

		collegeStudent.setFirstName("Maynard");
		collegeStudent.setLastName("Keenan");
		collegeStudent.setEmail("keenan@tool.com");
		collegeStudent.setGrade(grade);
	}

	@Test
	@DisplayName("test total math grades per class | equal")
	void testTotalGradesPerClass1() {
		double expectedResult = grade.getMathGrades().stream().mapToDouble(Double::doubleValue).sum();

		double actualResult = grade.totalGradesPerClass(collegeStudent.getGrade().getMathGrades());

		assertEquals(expectedResult, actualResult);
	}

	@Test
	@DisplayName("test total math grades per class | unequal")
	void testTotalGradesPerClass2() {
		double expectedResult = grade.getMathGrades().stream().reduce(0.0, Double::sum);

		double actualResult = grade.totalGradesPerClass(collegeStudent.getGrade().getMathGrades());

		assertNotEquals(expectedResult - 1, actualResult);
	}

	@Test
	@DisplayName("test if grade 1 is greater than grade 2")
	void testIsGreaterThan1() {
		double grade1 = 90;
		double grade2 = 75;

		boolean result = grade.isGreaterThan(grade1, grade2);

		assertTrue(result, grade1 + " should be greater than " + grade2);
	}

	@Test
	@DisplayName("test if grade 2 is greater than grade 1")
	void testIsGreaterThan2() {
		double grade1 = 90;
		double grade2 = 75;

		boolean result = grade.isGreaterThan(grade2, grade1);

		assertFalse(result, grade2 + " should not be greater than " + grade1);
	}

	@Test
	@DisplayName("test if math grades are not null")
	void testCheckNull() {
		assertNotNull(
			grade.checkNull(collegeStudent.getGrade().getMathGrades()),
			"students' math grades shouldn't be null"
		);
	}

	@Test
	@DisplayName("test non-graded student")
	void testNonGradedStudent() {
		CollegeStudent nonGradedStudent = context.getBean("collegeStudent", CollegeStudent.class);

		nonGradedStudent.setFirstName("Adam");
		nonGradedStudent.setLastName("Jones");
		nonGradedStudent.setEmail("jones@tool.com");

		assertNotNull(nonGradedStudent.getFirstName());
		assertNotNull(nonGradedStudent.getLastName());
		assertNotNull(nonGradedStudent.getEmail());
		assertNull(grade.checkNull(nonGradedStudent.getGrade()));
	}

	@Test
	@DisplayName("test if a student is a prototyped bean")
	void testPrototypedBeanStudent() {
		CollegeStudent studentBean = context.getBean("collegeStudent", CollegeStudent.class);

		assertNotSame(collegeStudent, studentBean);
	}

	@Test
	@DisplayName("test average grade")
	void testCalculateAverageGrade() {
		double expectedTotalGrades = grade.getMathGrades().stream().reduce(0.0, Double::sum);
		double expectedAverageGrade = grade.getMathGrades().stream().mapToDouble(grade -> grade).average().orElse(0);

		double actualTotalGrades = grade.totalGradesPerClass(collegeStudent.getGrade().getMathGrades());
		double actualAverageGrade = grade.calculateAverageGrade(collegeStudent.getGrade().getMathGrades());

		assertAll(
			"test all results from assertEquals",
			() -> assertEquals(expectedTotalGrades, actualTotalGrades),
			() -> assertEquals(Math.round(expectedAverageGrade * 100.0) / 100.0, actualAverageGrade)
		);
	}
}
