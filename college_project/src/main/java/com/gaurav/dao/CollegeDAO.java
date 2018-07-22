package com.gaurav.dao;

import java.util.List;

import com.gaurav.domain.College;
import com.gaurav.domain.Student;

public interface CollegeDAO {

	public void saveCollege(College c);

	public void update(College c);

	public void delete(College c);

	public void delete(Integer collegeId);

	public College findCollegeById(Integer collegeId);

	public List<College> findAllColleges();
	
	public List<College> findByProperty(String propName, Object propValue);
	
	public College getCollegeIdBasedOnUserId(Integer userId);

}
