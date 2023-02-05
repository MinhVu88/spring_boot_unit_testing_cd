package com.udemy.chaddarby.spring_boot_unit_testing.controller;

import com.udemy.chaddarby.spring_boot_unit_testing.model.gradebook.GradeBook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradeBookController {
	private final GradeBook gradebook;

	public GradeBookController(GradeBook gradebook) {
		this.gradebook = gradebook;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getStudents(Model m) {
		return "index";
	}

	@GetMapping("/student-info/{id}")
	public String getStudent(@PathVariable int id, Model m) {
		return "studentInfo";
	}
}
