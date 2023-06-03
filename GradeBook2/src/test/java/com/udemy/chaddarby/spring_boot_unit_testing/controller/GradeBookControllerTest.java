package com.udemy.chaddarby.spring_boot_unit_testing.controller;

import com.udemy.chaddarby.spring_boot_unit_testing.GradeBookApp2;
import com.udemy.chaddarby.spring_boot_unit_testing.model.gradebook.CollegeStudentGradeBook;
import com.udemy.chaddarby.spring_boot_unit_testing.model.student.CollegeStudent;
import com.udemy.chaddarby.spring_boot_unit_testing.repository.StudentDao;
import com.udemy.chaddarby.spring_boot_unit_testing.service.StudentService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

@SpringBootTest(classes = GradeBookApp2.class)
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
public class GradeBookControllerTest {
	// any method annotated with @BeforeAll must be static, 
	// hence mockHttpServletRequest must also be static to be used within it
	private static MockHttpServletRequest mockHttpServletRequest;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private StudentDao studentDao;

	@Mock
	private StudentService studentServiceMock;

	@BeforeAll
	static void setUpBeforeAll() {
		mockHttpServletRequest = new MockHttpServletRequest();

		mockHttpServletRequest.setParameter("first_name", "Jerry");
		mockHttpServletRequest.setParameter("last_name", "Cantrell");
		mockHttpServletRequest.setParameter("email", "cantrell@aic.com");
	}

	@BeforeEach
	void setUpBeforeEach() {
		this.jdbcTemplate.execute(
			"insert into student(id, email, first_name, last_name)" +
			"values (1, 'keenan@tool.com', 'Maynard', 'Keenan')"
		);
	}

	@AfterEach
	void cleanUpAfterEach() {
		this.jdbcTemplate.execute("delete from student");
	}

	@Test
	@DisplayName("test getStudents()")
	void testGetStudents() throws Exception {
		CollegeStudent collegeStudent1 = new CollegeStudentGradeBook("Adam", "Jones", "jones@tool.com");
		CollegeStudent collegeStudent2 = new CollegeStudentGradeBook("Dan", "Carey", "carey@tool.com");

		List<CollegeStudent> collegeStudentList = new ArrayList<>(Arrays.asList(collegeStudent1, collegeStudent2));

		when(this.studentServiceMock.getStudents()).thenReturn(collegeStudentList);

		assertIterableEquals(collegeStudentList, this.studentServiceMock.getStudents());

		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
																 			.andExpect(MockMvcResultMatchers.status().isOk())
																 			.andReturn();

		ModelAndView modelAndView = mvcResult.getModelAndView();

		ModelAndViewAssert.assertViewName(modelAndView, "index");
	}

	@Test
	@DisplayName("test createStudent()")
	void testCreateStudent() throws Exception {
		CollegeStudent student = new CollegeStudent("Layne", "Staley", "staley@aic.com");

		List<CollegeStudent> students = new ArrayList<>(Arrays.asList(student));

		when(this.studentServiceMock.getStudents()).thenReturn(students);

		assertIterableEquals(students, this.studentServiceMock.getStudents());

		MvcResult mvcResult = this.mockMvc.perform(
			MockMvcRequestBuilders.post("/students")
														.contentType(MediaType.APPLICATION_JSON)
														.param("first_name", mockHttpServletRequest.getParameterValues("first_name"))
														.param("last_name", mockHttpServletRequest.getParameterValues("last_name"))
														.param("email", mockHttpServletRequest.getParameterValues("email"))
		).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		ModelAndView modelAndView = mvcResult.getModelAndView();

		ModelAndViewAssert.assertViewName(modelAndView, "index");

		CollegeStudent collegeStudent = this.studentDao.findByEmail("cantrell@aic.com");

		assertNotNull(collegeStudent, "student exists in db");
	}

	@Test
	@DisplayName("test deleteStudent()")
	void testDeleteStudent() throws Exception {
		assertTrue(this.studentDao.findById(1).isPresent());

		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/students/removed/{id}", 1))
																			.andExpect(MockMvcResultMatchers.status().isOk())
																			.andReturn();

		ModelAndView modelAndView = mvcResult.getModelAndView();

		ModelAndViewAssert.assertViewName(modelAndView, "index");

		assertFalse(this.studentDao.findById(1).isPresent());
	}

	@Test
	void testdeleteNullStudent() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/students/removed/{id}", 0))
																			.andExpect(MockMvcResultMatchers.status().isOk())
																			.andReturn();

		ModelAndView modelAndView = mvcResult.getModelAndView();

		ModelAndViewAssert.assertViewName(modelAndView, "error");
	}
}
