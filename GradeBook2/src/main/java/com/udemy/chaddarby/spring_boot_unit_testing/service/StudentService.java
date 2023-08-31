package com.udemy.chaddarby.spring_boot_unit_testing.service;

import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.Grade;
import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.HistoryGrade;
import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.MathGrade;
import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.ScienceGrade;
import com.udemy.chaddarby.spring_boot_unit_testing.model.grade.SubjectGrades;
import com.udemy.chaddarby.spring_boot_unit_testing.model.gradebook.CollegeStudentGradeBook;
import com.udemy.chaddarby.spring_boot_unit_testing.model.student.CollegeStudent;
import com.udemy.chaddarby.spring_boot_unit_testing.repository.HistoryGradeDao;
import com.udemy.chaddarby.spring_boot_unit_testing.repository.MathGradeDao;
import com.udemy.chaddarby.spring_boot_unit_testing.repository.ScienceGradeDao;
import com.udemy.chaddarby.spring_boot_unit_testing.repository.StudentDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {
	private StudentDao studentDao;
	private MathGradeDao mathGradeDao;
	private MathGrade mathGrade;
	private ScienceGradeDao scienceGradeDao;
	private ScienceGrade scienceGrade;
	private HistoryGradeDao historyGradeDao;
	private HistoryGrade historyGrade;
	private SubjectGrades subjectGrades;

	public StudentService(
		StudentDao studentDao,
		MathGradeDao mathGradeDao, 
		MathGrade mathGrade,
		ScienceGradeDao scienceGradeDao,
		ScienceGrade scienceGrade,
		HistoryGradeDao historyGradeDao,
		HistoryGrade historyGrade,
		SubjectGrades subjectGrades
	) {
		this.studentDao = studentDao;
		this.mathGradeDao = mathGradeDao;
		this.mathGrade = mathGrade;
		this.scienceGradeDao = scienceGradeDao;
		this.scienceGrade = scienceGrade;
		this.historyGradeDao = historyGradeDao;
		this.historyGrade = historyGrade;
		this.subjectGrades = subjectGrades;
	}

	public void createNewStudent(
		String firstName,
		String lastName,
		String email
	) {
		CollegeStudent student = new CollegeStudent(firstName, lastName, email);
		student.setId(0);
		this.studentDao.save(student);
	}

	public boolean isStudentExistent(int studentId) {
		Optional<CollegeStudent> student = this.studentDao.findById(studentId);
		return student.isPresent();
	}

	public CollegeStudentGradeBook getStudent(int studentId) {
		if(!isStudentExistent(studentId)) {
			return null;
		}

		Optional<CollegeStudent> collegeStudent = this.studentDao.findById(studentId);

		Iterable<MathGrade> mathGradeIterable = this.mathGradeDao.findMathGradeByStudentId(studentId);
		List<Grade> mathGradeList = new ArrayList<>();
		mathGradeIterable.forEach(mathGradeList::add);

		Iterable<ScienceGrade> scienceGradeIterable = this.scienceGradeDao.findScienceGradeByStudentId(studentId);
		List<Grade> scienceGradeList = new ArrayList<>();
		scienceGradeIterable.forEach(scienceGradeList::add);

		Iterable<HistoryGrade> historyGradeIterable = this.historyGradeDao.findHistoryGradeByStudentId(studentId);
		List<Grade> historyGradeList = new ArrayList<>();
		historyGradeIterable.forEach(historyGradeList::add);

		this.subjectGrades.setMathGrades(mathGradeList);
		this.subjectGrades.setScienceGrades(scienceGradeList);
		this.subjectGrades.setHistoryGrades(historyGradeList);
		
		CollegeStudentGradeBook collegeStudentGradeBook = new CollegeStudentGradeBook(
			collegeStudent.get().getId(), 
			collegeStudent.get().getFirstName(), 
			collegeStudent.get().getLastName(), 
			collegeStudent.get().getEmail(),
			this.subjectGrades 
		);

		return collegeStudentGradeBook;
	}

	public Iterable<CollegeStudent> getStudents() {
		return this.studentDao.findAll();
	}

	public void deleteStudent(int studentId) {
		if(isStudentExistent(studentId)) {
			this.studentDao.deleteById(studentId);
			this.mathGradeDao.deleteByStudentId(studentId);
			this.scienceGradeDao.deleteByStudentId(studentId);
			this.historyGradeDao.deleteByStudentId(studentId);
		}
	}

  public boolean saveGrade(double grade, int studentId, String subject) {
		if(!isStudentExistent(studentId)) {
			return false;
		}

		if(grade >= 0 && grade <= 100) {
			if(subject.equalsIgnoreCase("math")) {
				this.mathGrade.setId(0);
				this.mathGrade.setGrade(grade);
				this.mathGrade.setStudentId(studentId);
				this.mathGradeDao.save(this.mathGrade);
				return true;
			}

			if(subject.equalsIgnoreCase("science")) {
				this.scienceGrade.setId(0);
				this.scienceGrade.setGrade(grade);
				this.scienceGrade.setStudentId(studentId);
				this.scienceGradeDao.save(this.scienceGrade);
				return true;
			}

			if(subject.equalsIgnoreCase("history")) {
				this.historyGrade.setId(0);
				this.historyGrade.setGrade(grade);
				this.historyGrade.setStudentId(studentId);
				this.historyGradeDao.save(this.historyGrade);
				return true;
			}
		}

    return false;
  }

	public int deleteGrade(int gradeId, String subject) {
		int studentId = 0;
		
		if(subject.equalsIgnoreCase("math")) {
			Optional<MathGrade> mathGrade = this.mathGradeDao.findById(gradeId);

			if(!mathGrade.isPresent()) {
				return studentId;
			}

			studentId = mathGrade.get().getStudentId();
			this.mathGradeDao.deleteById(gradeId);
		}

		if(subject.equalsIgnoreCase("science")) {
			Optional<ScienceGrade> scienceGrade = this.scienceGradeDao.findById(gradeId);

			if(!scienceGrade.isPresent()) {
				return studentId;
			}

			studentId = scienceGrade.get().getStudentId();
			this.scienceGradeDao.deleteById(gradeId);
		}

		if(subject.equalsIgnoreCase("history")) {
			Optional<HistoryGrade> historyGrade = this.historyGradeDao.findById(gradeId);

			if(!historyGrade.isPresent()) {
				return studentId;
			}

			studentId = historyGrade.get().getStudentId();
			this.historyGradeDao.deleteById(gradeId);
		}

		return studentId;
	}
}
