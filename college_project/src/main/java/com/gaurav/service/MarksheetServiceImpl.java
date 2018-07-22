package com.gaurav.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaurav.dao.MarksheetDAO;
import com.gaurav.domain.College;
import com.gaurav.domain.Marksheet;
import com.gaurav.domain.Student;

@Service
public class MarksheetServiceImpl implements MarksheetService {

	@Autowired
	MarksheetDAO marksheetDAO;

	@Override
	public void saveMarksheet(Marksheet m) {
		marksheetDAO.saveMarksheet(m);
	}

	@Override
	public void update(Marksheet m) {
		marksheetDAO.update(m);
	}

	@Override
	public void delete(Marksheet m) {
		marksheetDAO.delete(m);
	}

	@Override
	public void delete(Integer marksheetId) {
		marksheetDAO.delete(marksheetId);
	}

	@Override
	public Marksheet findMarksheetById(Integer marksheetId) {
		return marksheetDAO.findMarksheetById(marksheetId);
	}

	@Override
	public List<Marksheet> findAllMarksheet() {
		return marksheetDAO.findAllMarksheet();
	}

	@Override
	public List<Marksheet> findByProperty(String propName, Object propValue) {
		return marksheetDAO.findByProperty(propName, propValue);
	}

	@Override
	public Marksheet findByStudentId(Student studentId) {
		return marksheetDAO.findByStudentId(studentId);
	}

	@Override
	public Marksheet findByCollegeId(College collegeId) {
		return marksheetDAO.findByCollegeId(collegeId);
	}

	@Override
	public List<Marksheet> findByUserId(Integer userId) {
		return marksheetDAO.findByUserId(userId);
	}

	@Override
	public List<Marksheet> MeritList() {
		return marksheetDAO.MeritList();
	}

	@Override
	public List<Marksheet> findMarksheetByCollegeId(Integer userId){
		return marksheetDAO.findMarksheetByCollegeId(userId);
	}
}
