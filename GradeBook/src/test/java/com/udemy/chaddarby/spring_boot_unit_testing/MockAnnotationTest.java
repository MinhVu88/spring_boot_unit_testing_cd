// Sec 5: Mocking with Mockito
package com.udemy.chaddarby.spring_boot_unit_testing;

import com.udemy.chaddarby.spring_boot_unit_testing.dao.ApplicationDao;
import com.udemy.chaddarby.spring_boot_unit_testing.models.CollegeStudent;
import com.udemy.chaddarby.spring_boot_unit_testing.models.Grade;
import com.udemy.chaddarby.spring_boot_unit_testing.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MockAnnotationTest {
	@Autowired
	private ApplicationContext context;

	@Autowired
	private CollegeStudent collegeStudent;

	@Autowired
	private Grade grade;

	// @Mock
	@MockBean
	private ApplicationDao applicationDao;

	// @InjectMocks
	@Autowired
	private ApplicationService applicationService;

	@BeforeEach
	void setUpBeforeEach() {
		collegeStudent.setFirstName("Jerry");
		collegeStudent.setLastName("Cantrell");
		collegeStudent.setEmail("cantrell@aic.com");
		collegeStudent.setGrade(grade);
	}

	@Test
	@DisplayName("test total grades per class")
	void testTotalGradesPerClass() {
		when(
			applicationDao.totalGradesPerClass(grade.getMathGrades())
		).thenReturn(100.0);

		assertEquals(
			100,
			applicationService.totalGradesPerClass(collegeStudent.getGrade().getMathGrades())
		);

		verify(
			applicationDao,
			times(1)
		).totalGradesPerClass(grade.getMathGrades());
	}

	@Test
	@DisplayName("test average grade calculation")
	void testCalculateAverageGrade() {
		double expectedResult = 88.31;

		double averageGrade = applicationDao.calculateAverageGrade(grade.getMathGrades());

		when(averageGrade).thenReturn(expectedResult);

		assertEquals(
			expectedResult,
			applicationService.calculateAverageGrade(collegeStudent.getGrade().getMathGrades())
		);
	}

	@Test
	@DisplayName("test check null")
	void testCheckNull() {
		Object nullObj = applicationDao.checkNull(grade.getMathGrades());

		when(nullObj).thenReturn(true);

		assertNotNull(
			applicationService.checkNull(collegeStudent.getGrade().getMathGrades()),
			"Object shouldn't be null"
		);
	}

	@Test
	@DisplayName("test throwing runtime exception once for checkNull()")
	void testThrowingARuntimeErrorForCheckNull() {
		CollegeStudent nullStudent = (CollegeStudent) context.getBean("collegeStudent");

		doThrow(new RuntimeException()).when(applicationDao).checkNull(nullStudent);

		assertThrows(RuntimeException.class, () -> applicationService.checkNull(nullStudent));

		verify(applicationDao, times(1)).checkNull(nullStudent);
	}

	@Test
	@DisplayName("test multiple stubbing for checkNull()")
	void testStubbingConsecutiveCallsForCheckNull() {
		String msg = "Don't throw any exception for subsequent method calls after the 1st";

		CollegeStudent nullStudent = (CollegeStudent) context.getBean("collegeStudent");

		// when checkNull is 1st invoked, throw an exception,
		// subsequent calls to this method throw NO exceptions,
		// instead a message regarding this circumstance is returned.
		when(applicationDao.checkNull(nullStudent)).thenThrow(new RuntimeException()).thenReturn(msg);

		// the 1st call of checkNull
		assertThrows(RuntimeException.class, () -> applicationService.checkNull(nullStudent));

		// the 2nd call of checkNull
		assertEquals(msg, applicationService.checkNull(nullStudent));

		// verify that checkNull() has been called twice
		verify(applicationDao, times(2)).checkNull(nullStudent);
	}
}
