// Sections: 4 | 5 | 6
package com.udemy.chaddarby.spring_boot_unit_testing;

import com.udemy.chaddarby.spring_boot_unit_testing.dao.ApplicationDao;
import com.udemy.chaddarby.spring_boot_unit_testing.model.CollegeStudent;
import com.udemy.chaddarby.spring_boot_unit_testing.service.ApplicationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class GradeBookApp {
	public static void main(String[] args) {
		SpringApplication.run(GradeBookApp.class, args);
	}

	@Bean(name = "collegeStudent")
	@Scope(value = "prototype")
	CollegeStudent getCollegeStudent() {
		return new CollegeStudent();
	}

	@Bean(name = "applicationExample")
	ApplicationService getApplicationService() {
		return new ApplicationService();
	}

	@Bean(name = "applicationDao")
	ApplicationDao getApplicationDao() {
		return new ApplicationDao();
	}
}
