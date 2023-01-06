package com.udemy.chaddarby.spring_boot_unit_testing.service;

import com.udemy.chaddarby.spring_boot_unit_testing.dao.ApplicationDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ApplicationService {
	@Autowired
	private ApplicationDao applicationDao;

	public double totalGradesPerClass(List<Double> numbers) {
		return applicationDao.totalGradesPerClass(numbers);
	}

	public double calculateAverageGrade (List<Double> grades ) {
		return applicationDao.calculateAverageGrade(grades);
	}

	public Object checkNull(Object obj) {
		return applicationDao.checkNull(obj);
	}
}
