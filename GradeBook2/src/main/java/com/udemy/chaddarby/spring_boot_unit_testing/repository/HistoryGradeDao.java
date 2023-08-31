package com.udemy.chaddarby.spring_boot_unit_testing.repository;

import org.springframework.data.repository.CrudRepository;
import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.HistoryGrade;

public interface HistoryGradeDao extends CrudRepository<HistoryGrade, Integer> {
  Iterable<HistoryGrade> findHistoryGradeByStudentId(int studentId);
  void deleteByStudentId(int studentId);
}
