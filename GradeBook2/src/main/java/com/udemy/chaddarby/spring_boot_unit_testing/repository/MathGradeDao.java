package com.udemy.chaddarby.spring_boot_unit_testing.repository;

import org.springframework.data.repository.CrudRepository;
import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.MathGrade;

public interface MathGradeDao extends CrudRepository<MathGrade, Integer> {
  Iterable<MathGrade> findMathGradeByStudentId(int studentId);
  void deleteByStudentId(int studentId);
}
