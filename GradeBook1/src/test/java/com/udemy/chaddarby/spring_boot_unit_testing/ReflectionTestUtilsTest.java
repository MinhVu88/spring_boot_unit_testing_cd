package com.udemy.chaddarby.spring_boot_unit_testing;

import com.udemy.chaddarby.spring_boot_unit_testing.model.CollegeStudent;
import com.udemy.chaddarby.spring_boot_unit_testing.model.Grade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = GradeBookApp1.class)
public class ReflectionTestUtilsTest {
	@Autowired
	private CollegeStudent collegeStudent;

	@Autowired
	private Grade grade;

	@BeforeEach
	void setUpBeforeEach() {
		collegeStudent.setFirstName("Adam");
		collegeStudent.setLastName("Jones");
		collegeStudent.setEmail("jones@tool.com");
		collegeStudent.setGrade(grade);

		String privateFieldName1 = "id";
		int privateFieldValue1 = 1;

		ReflectionTestUtils.setField(collegeStudent, privateFieldName1, privateFieldValue1);

		String privateFieldName2 = "grade";
		Grade privateFieldValue2 = new Grade(new ArrayList<>(Arrays.asList(100.0, 85.0, 76.5, 91.75)));

		ReflectionTestUtils.setField(collegeStudent, privateFieldName2, privateFieldValue2);
	}

	@Test
	@DisplayName("test getting private field id")
	void testGettingPrivateField() {
		int expectedResult = 1;
		String privateFieldName = "id";

		Object actualResult = ReflectionTestUtils.getField(collegeStudent, privateFieldName);

		assertEquals(expectedResult, actualResult);
	}

	@Test
	@DisplayName("test invoking private getCollegeStudentCredentials")
	void testInvokingPrivateMethod() {
		String expectedResult = "Id: " + collegeStudent.getId() +
														" | Name: " + collegeStudent.getFirstName() +
														" " + collegeStudent.getLastName();

		String privateMethodName = "getCollegeStudentCredentials";

		Object actualResult = ReflectionTestUtils.invokeMethod(collegeStudent, privateMethodName);

		assertEquals(expectedResult, actualResult, "failed to call the private method");
	}
}
