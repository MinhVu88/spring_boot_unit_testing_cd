package com.udemy.chaddarby.spring_boot_unit_testing.controller;

import com.udemy.chaddarby.spring_boot_unit_testing.model.gradebook.GradeBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradeBookController {
	@Autowired
	private GradeBook gradebook;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getStudents(Model m) {
		return "index";
	}

	@GetMapping("/student-info/{id}")
	public String studentInformation(@PathVariable int id, Model m) {
		return "studentInfo";
		}
}
