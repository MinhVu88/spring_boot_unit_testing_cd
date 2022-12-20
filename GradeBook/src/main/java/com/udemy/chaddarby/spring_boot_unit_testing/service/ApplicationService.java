package com.udemy.chaddarby.spring_boot_unit_testing.service;

import com.udemy.chaddarby.spring_boot_unit_testing.dao.ApplicationDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ApplicationService {
	@Autowired
	private ApplicationDao applicationDao;

	public double addGradeResultsForSingleClass(List<Double> numbers) {
		return applicationDao.addGradeResultsForSingleClass(numbers);
	}

	public double findGradePointAverage (List<Double> grades ) {
		return applicationDao.findGradePointAverage(grades);
	}

	public Object checkNull(Object obj) {
		return applicationDao.checkNull(obj);
	}
}
