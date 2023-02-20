package com.udemy.chaddarby.spring_boot_unit_testing.controller;

import com.udemy.chaddarby.spring_boot_unit_testing.model.gradebook.Gradebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradeBookController {
	private final Gradebook gradebook;

	public GradeBookController(Gradebook gradebook) {
		this.gradebook = gradebook;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getStudents(Model m) {
		return "index";
	}

	@GetMapping("/student-info/{id}")
	public String studentInformation(@PathVariable int id, Model model) {
		return "studentInfo";
		}
}
