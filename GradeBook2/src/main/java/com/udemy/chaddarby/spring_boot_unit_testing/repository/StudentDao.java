package com.udemy.chaddarby.spring_boot_unit_testing.repository;

import com.udemy.chaddarby.spring_boot_unit_testing.model.student.CollegeStudent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository<CollegeStudent, Integer> {
	CollegeStudent findByEmail(String email);
}
