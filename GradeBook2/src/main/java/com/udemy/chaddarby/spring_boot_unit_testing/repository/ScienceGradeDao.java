package com.udemy.chaddarby.spring_boot_unit_testing.repository;

import org.springframework.data.repository.CrudRepository;
import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.ScienceGrade;

public interface ScienceGradeDao extends CrudRepository<ScienceGrade, Integer> {
  Iterable<ScienceGrade> findScienceGradeByStudentId(int studentId);
  void deleteByStudentId(int studentId);
}
