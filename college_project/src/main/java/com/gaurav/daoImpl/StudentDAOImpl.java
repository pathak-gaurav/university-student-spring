package com.gaurav.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.gaurav.dao.BaseDAO;
import com.gaurav.dao.StudentDAO;
import com.gaurav.domain.Student;
import com.gaurav.rm.StudentRowMapper;

@Repository
public class StudentDAOImpl extends BaseDAO implements StudentDAO {

	@Override
	public void saveStudent(Student s) {
		String sql = "INSERT INTO student (firstName,lastName,mobile,email,studentId,collegeId) "
				+ " VALUES (:firstName, :lastName, :mobile, :email, :studentId, :collegeId)";
		Map map = new HashMap();
		map.put("firstName", s.getFirstName());
		map.put("lastName", s.getLastName());
		map.put("mobile", s.getMobile());
		map.put("email", s.getEmail());
		//map.put("collegeName", s.getCollegeName());
		map.put("studentId", s.getStudentId());
		map.put("collegeId", s.getCollegeId());

		KeyHolder kh = new GeneratedKeyHolder();
		SqlParameterSource ps = new MapSqlParameterSource(map);
		super.getNamedParameterJdbcTemplate().update(sql, ps, kh);
		int studentId = kh.getKey().intValue();
		s.setStudentId(studentId);
	}

	@Override
	public void update(Student s) {
		String sql = "UPDATE student set firstName=:firstName,lastName=:lastName, mobile=:mobile, email=:email "
				+ " ,collegeId=:collegeId WHERE studentId=:studentId";
		Map map = new HashMap();
		map.put("firstName", s.getFirstName());
		map.put("lastName", s.getLastName());
		map.put("mobile", s.getMobile());
		map.put("email", s.getEmail());
		//map.put("collgeName", s.getCollegeName());
		map.put("studentId", s.getStudentId());
		map.put("collegeId", s.getCollegeId());
		getNamedParameterJdbcTemplate().update(sql, map);
	}

	@Override
	public void delete(Student s) {
		this.delete(s.getStudentId());
	}

	@Override
	public void delete(Integer studentId) {
		String sql = "DELETE FROM student WHERE studentId=?";
		getJdbcTemplate().update(sql, studentId);

	}

	@Override
	public Student findById(Integer studentId) {
		String sql = "SELECT studentId,collegeId,firstName,lastName,mobile,email,collegeName FROM student WHERE studentId=?";
		return getJdbcTemplate().queryForObject(sql, new StudentRowMapper(), studentId);
	}

	@Override
	public List<Student> findAll() {
		String sql = "SELECT firstName,lastName,mobile,email,collegeName,collegeId,studentId FROM student";
		return getJdbcTemplate().query(sql, new StudentRowMapper());
	}

	@Override
	public List<Student> findByProperty(String propName, Object propValue) {
		String sql = "SELECT firstName,lastName,mobile,email,collegeName,collegeId,studentId from student WHERE "
				+ propName + "=propValue";
		return getJdbcTemplate().query(sql, new StudentRowMapper(), propName);
	}
	
	@Override
	public List<Student> findByCollegeId(Integer collegeId){
		String sql = "SELECT s.firstName,s.lastName,s.mobile,s.email,s.collegeName,s.collegeId,studentId"
				+ " FROM student s INNER JOIN college c ON (s.collegeId = c.id) "
				+ " INNER JOIN user u ON (u.firstName=c.name) WHERE u.roleId='5' \r\n" + 
				"	AND u.userId=?";
		return getJdbcTemplate().query(sql, new StudentRowMapper(), collegeId);
	}

}
