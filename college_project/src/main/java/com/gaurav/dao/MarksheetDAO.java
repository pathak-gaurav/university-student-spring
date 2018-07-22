package com.gaurav.dao;

import java.util.List;

import com.gaurav.domain.College;
import com.gaurav.domain.Marksheet;
import com.gaurav.domain.Student;
import com.gaurav.domain.User;

public interface MarksheetDAO {

	public void saveMarksheet(Marksheet m);

	public void update(Marksheet m);

	public void delete(Marksheet m);

	public void delete(Integer marksheetId);

	public Marksheet findMarksheetById(Integer marksheetId);

	public List<Marksheet> findAllMarksheet();

	public List<Marksheet> findByProperty(String propName, Object propValue);

	public Marksheet findByStudentId(Student studentId);

	public Marksheet findByCollegeId(College collegeId);
	
	public List<Marksheet> findByUserId(Integer userId);
	
	public List<Marksheet> MeritList();
	
	public List<Marksheet> findMarksheetByCollegeId(Integer userId);

}
