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
import com.gaurav.dao.UserDAO;
import com.gaurav.domain.User;
import com.gaurav.rm.UserRowMapper;

@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO {

	@Override
	public void save(User u) {
		String sql = "INSERT INTO USER (firstName,lastName,login,password,confirmPassword,mobile,gender) "
				+ " VALUES(:firstName,:lastName,:login,:password,:confirmPassword,:mobile,:gender)";
		Map map = new HashMap();
		map.put("firstName", u.getFirstName());
		map.put("lastName", u.getLastName());
		map.put("login", u.getLogin());
		map.put("password", u.getPassword());
		map.put("confirmPassword", u.getConfirmPassword());
		map.put("mobile", u.getMobile());
		map.put("gender", u.getGender());

		KeyHolder kh = new GeneratedKeyHolder();
		SqlParameterSource ps = new MapSqlParameterSource(map);
		super.getNamedParameterJdbcTemplate().update(sql, ps, kh);
		int userId = kh.getKey().intValue();
		u.setUserId(userId);
	}

	@Override
	public void update(User u) {
		String sql = "UPDATE user set firstName=:firstName,lastName=:lastName,login=:login,password=:password,"
				+ " confirmPassword=:confirmPassword,mobile=:mobile,gender=:gender WHERE userId=:userId";
		Map map = new HashMap();
		map.put("firstName", u.getFirstName());
		map.put("lastName", u.getLastName());
		map.put("login", u.getLogin());
		map.put("password", u.getPassword());
		map.put("confirmPassword", u.getConfirmPassword());
		map.put("mobile", u.getMobile());
		map.put("gender", u.getGender());
		map.put("userId", u.getUserId());
		getNamedParameterJdbcTemplate().update(sql, map);
	}

	@Override
	public void delete(User u) {
		this.delete(u.getUserId());
	}

	@Override
	public void delete(Integer userId) {
		String sql = "DELETE FROM user WHERE userId=?";
		getJdbcTemplate().update(sql, userId);
	}

	@Override
	public User findById(Integer userId) {
		String sql = "SELECT `firstName`,`lastName`,`login`,`password`,`confirmPassword`,`mobile`,`gender`,`lock`,`dob`,`roleId`,`userId` FROM user WHERE userId=?";
		return getJdbcTemplate().queryForObject(sql, new UserRowMapper(), userId);
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT `firstName`,`lastName`,`login`,`password`,`confirmPassword`,`mobile`,`gender`,`lock`,`dob`,`roleId`,`userId` FROM user";
		return getJdbcTemplate().query(sql, new UserRowMapper());
	}

	@Override
	public List<User> findByProperty(String propName, Object propValue) {
		String sql = "SELECT firstName,lastName,login,password,confirmPassword,mobile,gender,roleId,lock FROM user WHERE "
				+ propName + "=propValue";
		return getJdbcTemplate().query(sql, new UserRowMapper(), propValue);
	}
	
	@Override
	public List<User> findByListId(Integer userId){
		String sql = "SELECT `firstName`,`lastName`,`login`,`password`,`confirmPassword`,`mobile`,`gender`,`lock`,`dob`,`roleId`,`userId` FROM user WHERE userId=?";
		return getJdbcTemplate().query(sql, new UserRowMapper(), userId);
	}
}
