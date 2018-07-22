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
import com.gaurav.dao.MarksheetDAO;
import com.gaurav.domain.College;
import com.gaurav.domain.Marksheet;
import com.gaurav.domain.Student;
import com.gaurav.rm.MarksheetRowMapper;

@Repository
public class MarksheetDAOImpl extends BaseDAO implements MarksheetDAO {

	@Override
	public void saveMarksheet(Marksheet m) {
		String sql = "INSERT INTO marksheet (name, rollno, studentId,physics,chemistry,maths) "
				+ " VALUES (:name, :rollno, :studentId, :physics, :chemistry, :maths ) ";
		Map map = new HashMap();
		map.put("name", m.getName());
		map.put("rollno", m.getRollno());
		map.put("studentId", m.getStudentId());
		map.put("physics", m.getPhysics());
		map.put("chemistry", m.getChemistry());
		map.put("maths", m.getMaths());

		KeyHolder kh = new GeneratedKeyHolder();
		SqlParameterSource ps = new MapSqlParameterSource(map);
		super.getNamedParameterJdbcTemplate().update(sql, ps, kh);
		int marksheetId = kh.getKey().intValue();
		m.setId(marksheetId);
	}

	@Override
	public void update(Marksheet m) {
		String sql = "UPDATE marksheet SET name=:name,rollno=:rollno, physics=:physics, chemistry=:chemistry"
				+ " maths=:maths WHERE studentId=:studentId";
		Map map = new HashMap();
		map.put("name", m.getName());
		map.put("rollno", m.getRollno());
		map.put("studentId", m.getStudentId());
		map.put("physics", m.getPhysics());
		map.put("chemistry", m.getChemistry());
		map.put("maths", m.getMaths());
		getNamedParameterJdbcTemplate().update(sql, map);
	}

	@Override
	public void delete(Marksheet m) {
		this.delete(m.getId());
	}

	@Override
	public void delete(Integer marksheetId) {
		String sql = "DELETE FROM marksheet where id=?";
		getJdbcTemplate().update(sql, marksheetId);
	}

	@Override
	public Marksheet findMarksheetById(Integer marksheetId) {
		String sql = "SELECT name, rollno, studentId,physics,chemistry, maths,id FROM marksheet WHERE id=?";
		return getJdbcTemplate().queryForObject(sql, new MarksheetRowMapper(), marksheetId);
	}

	@Override
	public List<Marksheet> findAllMarksheet() {
		String sql = "SELECT * FROM marksheet ";
		return getJdbcTemplate().query(sql, new MarksheetRowMapper());
	}

	@Override
	public List<Marksheet> findByProperty(String propName, Object propValue) {
		String sql = "SELECT name, rollno, studentId,physics,chemistry, maths,id FROM marksheet WHERE" + propName
				+ "=?";
		return getJdbcTemplate().query(sql, new MarksheetRowMapper(), propValue);
	}

	@Override
	public Marksheet findByStudentId(Student studentId) {
		String sql = "SELECT name, rollno, studentId,physics,chemistry, maths,id FROM marksheet WHERE studentId=?";
		return getJdbcTemplate().queryForObject(sql, new MarksheetRowMapper(), studentId);
	}

	@Override
	public Marksheet findByCollegeId(College collegeId) {
		String sql = "SELECT name, rollno, studentId,physics,chemistry, maths,id FROM marksheet WHERE id=?";
		return getJdbcTemplate().queryForObject(sql, new MarksheetRowMapper(), collegeId);
	}

	@Override
	public List<Marksheet> findByUserId(Integer userId) {
		String sql = "SELECT m.rollNo,m.name,m.physics,m.chemistry,m.maths,m.id,m.studentId from student s "
				+ " INNER JOIN user u ON (s.firstName=u.firstName) AND (s.lastName=u.lastName)\r\n" + 
				"	INNER JOIN marksheet m ON(m.studentId=s.studentId) AND u.userId=? ";
		return getJdbcTemplate().query(sql, new MarksheetRowMapper(), userId);
	}
	
	@Override
	public List<Marksheet> MeritList(){
		String sql = "SELECT m.rollNo,m.name,m.physics,m.chemistry,m.maths,m.id,m.studentId "
				+ " FROM marksheet m WHERE physics>=80 AND chemistry>=80 and maths>=80";
		return  getJdbcTemplate().query(sql, new MarksheetRowMapper());
	}
	
	@Override
	public List<Marksheet> findMarksheetByCollegeId(Integer userId){
		String sql = "SELECT m.id,m.rollno,m.studentId,m.name,m.physics,m.chemistry,m.maths \r\n" + 
				" FROM marksheet m INNER JOIN student s ON (m.studentId=s.studentId) \r\n" + 
				" INNER JOIN college c ON (s.collegeId=c.id) \r\n" + 
				" INNER JOIN user u ON (c.name = u.firstName) AND roleId=5 AND userId=?";
		return getJdbcTemplate().query(sql, new MarksheetRowMapper(), userId);
	}
	

}
