package com.gaurav.dao;

import java.util.List;

import com.gaurav.domain.Student;

public interface StudentDAO {

	public void saveStudent(Student s);

	public void update(Student s);

	public void delete(Student s);

	public void delete(Integer studentId);

	public Student findById(Integer studentId);

	public List<Student> findAll();

	public List<Student> findByProperty(String propName, Object propValue);
	
	public List<Student> findByCollegeId(Integer collegeId);
}
