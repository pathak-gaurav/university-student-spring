package com.gaurav.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaurav.dao.CollegeDAO;
import com.gaurav.domain.College;

@Service
public class CollegeServiceImpl implements CollegeService {

	@Autowired
	CollegeDAO collegeDAO;
	
	@Override
	public void saveCollege(College c) {
		collegeDAO.saveCollege(c);
	}

	@Override
	public void update(College c) {
		collegeDAO.update(c);
	}

	@Override
	public void delete(College c) {
		collegeDAO.delete(c);
	}

	@Override
	public void delete(Integer collegeId) {
		collegeDAO.delete(collegeId);
	}

	@Override
	public College findCollegeById(Integer collegeId) {
		return collegeDAO.findCollegeById(collegeId);
	}

	@Override
	public List<College> findAllColleges() {
		return collegeDAO.findAllColleges();
	}

	@Override
	public List<College> findByProperty(String propName, Object propValue) {
		return collegeDAO.findByProperty(propName, propValue);
	}
	
	public College getCollegeIdBasedOnUserId(Integer userId) {
		return collegeDAO.getCollegeIdBasedOnUserId(userId);
	}

}
