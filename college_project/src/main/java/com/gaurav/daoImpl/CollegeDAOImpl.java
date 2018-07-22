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
import com.gaurav.dao.CollegeDAO;
import com.gaurav.domain.College;
import com.gaurav.rm.CollegeRowMapper;

@Repository
public class CollegeDAOImpl extends BaseDAO implements CollegeDAO {

	@Override
	public void saveCollege(College c) {
		String sql = "INSERT INTO college (name,address,state,city,phone) "
				+ " VALUES(:name, :address, :state, :city, :phone)";
		Map map = new HashMap();
		map.put("name", c.getName());
		map.put("address", c.getAddress());
		map.put("state", c.getState());
		map.put("city", c.getCity());
		map.put("phone", c.getPhone());

		KeyHolder kh = new GeneratedKeyHolder();
		SqlParameterSource ps = new MapSqlParameterSource(map);
		super.getNamedParameterJdbcTemplate().update(sql, ps, kh);
		int collegeId = kh.getKey().intValue();
		c.setId(collegeId);
	}

	@Override
	public void update(College c) {
		String sql = "UPDATE college set name=:name, address=:address, state=:state, city=:city, phone=:phone WHERE"
				+ " id=:id";
		Map map = new HashMap();
		map.put("name", c.getName());
		map.put("address", c.getAddress());
		map.put("state", c.getState());
		map.put("city", c.getCity());
		map.put("phone", c.getPhone());
		map.put("id", c.getId());
		getNamedParameterJdbcTemplate().update(sql, map);
	}

	@Override
	public void delete(College c) {
		this.delete(c.getId());
	}

	@Override
	public void delete(Integer collegeId) {
		String sql = "DELETE from college where id=?";
		getJdbcTemplate().update(sql, collegeId);
	}

	@Override
	public College findCollegeById(Integer collegeId) {
		String sql = "SELECT id,name,address,state,city,phone FROM college WHERE id=?";
		return getJdbcTemplate().queryForObject(sql, new CollegeRowMapper(), collegeId);
	}

	@Override
	public List<College> findAllColleges() {
		String sql = "SELECT id,name,address,state,city,phone FROM college";
		return getJdbcTemplate().query(sql, new CollegeRowMapper());
	}

	@Override
	public List<College> findByProperty(String propName, Object propValue) {
		String sql = "SELECT id,name,address,state,city,phone FROM college WHERE " + propName + "=?";
		return getJdbcTemplate().query(sql, new CollegeRowMapper(), propValue);
	}

	@Override
	public College getCollegeIdBasedOnUserId(Integer userId) {
		String sql = "SELECT c.id,c.name,c.address,c.state,c.city,c.phone "
				+ " FROM college c INNER JOIN user u ON (c.name=u.firstName) WHERE u.roleId=5 and userId=? ";
		return getJdbcTemplate().queryForObject(sql, new CollegeRowMapper(), userId);
	}
	
}
