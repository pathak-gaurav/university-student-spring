package com.gaurav.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gaurav.dao.BaseDAO;
import com.gaurav.dao.UserDAO;
import com.gaurav.domain.User;
import com.gaurav.rm.UserRowMapper;

@Service
public class UserServiceImpl extends BaseDAO implements UserService {

	@Autowired
	UserDAO userDAO;

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void register(User u) {
		userDAO.save(u);
	}

	@Override
	public User login(String login, String password) {
		String sql = "SELECT `userId`,`roleId`,`lastName`,`firstName`,`login`,`password`,`confirmPassword`,`mobile`,`gender`,`dob`,`lock`"
				+ " FROM `user` WHERE (login=:ln) AND (password=:pw)";
		Map m = new HashMap();
		m.put("ln", login);
		m.put("pw", password);
		try {
			User user = getNamedParameterJdbcTemplate().queryForObject(sql, m, new UserRowMapper());
			logger.info(user.toString());
			if (user.getLock().equals(LOCK_STATUS_ACTIVE)) {
				return user;
			}
			return user;
		} catch (EmptyResultDataAccessException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Contact administrator.");
		}
	}

	@Override
	public List<User> getUserList() {
		return userDAO.findAll();
	}

	@Override
	public void changeLoginStatus(Integer userId, Integer loginStatus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(User u) {
		userDAO.save(u);
	}

	@Override
	public void update(User u) {
		userDAO.update(u);
	}

	@Override
	public void delete(User u) {
		userDAO.delete(u);
	}

	@Override
	public void delete(Integer userId) {
		userDAO.delete(userId);
	}

	@Override
	public User findById(Integer userId) {
		return userDAO.findById(userId);
	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	public List<User> findByProperty(String propName, Object propValue) {
		return userDAO.findByProperty(propName, propValue);
	}
	
	@Override
	public List<User> findByListId(Integer userId){
		return userDAO.findByListId(userId);
	}

}
