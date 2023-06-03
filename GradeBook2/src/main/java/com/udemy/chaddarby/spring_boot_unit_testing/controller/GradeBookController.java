package com.udemy.chaddarby.spring_boot_unit_testing.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.udemy.chaddarby.spring_boot_unit_testing.model.gradebook.GradeBook;
import com.udemy.chaddarby.spring_boot_unit_testing.model.student.CollegeStudent;
import com.udemy.chaddarby.spring_boot_unit_testing.service.StudentService;

@RestController
@RequestMapping("students")
public class GradeBookController {
	private GradeBook gradeBook;
	private StudentService studentService;

	public GradeBookController(GradeBook gradeBook, StudentService studentService) {
		this.gradeBook = gradeBook;
		this.studentService = studentService;
	}

	@PostMapping
	public ModelAndView createStudent(
		Model model,
		@ModelAttribute("student") CollegeStudent collegeStudent
	) {
		this.studentService.createNewStudent(
			collegeStudent.getFirstName(), 
			collegeStudent.getLastName(), 
			collegeStudent.getEmail()
		);

		Iterable<CollegeStudent> collegeStudents = this.studentService.getStudents();
		model.addAttribute("students", collegeStudents);
		return new ModelAndView("index");
	}

	@GetMapping
	public ModelAndView getStudents(Model model) {
		Iterable<CollegeStudent> collegeStudents = this.studentService.getStudents();
		model.addAttribute("students", collegeStudents);
		return new ModelAndView("index");
	}

	@GetMapping("/info/{id}")
	public ModelAndView getStudent(@PathVariable int id, Model model) {
		return new ModelAndView("studentInfo");
	}

	@GetMapping("/removed/{id}")
	public ModelAndView deleteStudent(@PathVariable int id, Model model) {
		if(!this.studentService.isStudentExistent(id)) {
			return new ModelAndView("error");
		}

		this.studentService.deleteStudent(id);
		Iterable<CollegeStudent> collegeStudents = this.studentService.getStudents();
		model.addAttribute("students", collegeStudents);
		return new ModelAndView("index");
	}
}
