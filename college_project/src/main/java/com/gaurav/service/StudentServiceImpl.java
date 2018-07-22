package com.gaurav.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaurav.dao.StudentDAO;
import com.gaurav.domain.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDAO studentDAO;

	@Override
	public void saveStudent(Student s) {
		studentDAO.saveStudent(s);
	}

	@Override
	public void update(Student s) {
		studentDAO.update(s);
	}

	@Override
	public void delete(Student s) {
		studentDAO.delete(s);
	}

	@Override
	public void delete(Integer studentId) {
		studentDAO.delete(studentId);
	}

	@Override
	public Student findById(Integer studentId) {
		return studentDAO.findById(studentId);
	}

	@Override
	public List<Student> findAll() {
		return studentDAO.findAll();
	}

	@Override
	public List<Student> findByProperty(String propName, Object propValue) {
		return findByProperty(propName, propValue);
	}
	
	@Override
	public List<Student> findByCollegeId(Integer collegeId){
		return studentDAO.findByCollegeId(collegeId);
	}
}
