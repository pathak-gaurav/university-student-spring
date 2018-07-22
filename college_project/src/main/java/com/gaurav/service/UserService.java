package com.gaurav.service;

import java.util.List;

import com.gaurav.domain.User;

public interface UserService {
	
	public static final Integer LOCK_STATUS_ACTIVE = 1;
	public static final Integer LOCK_STATUS_BLOCKED = 2;

	public static final Integer ROLE_ADMIN = 1;
	public static final Integer ROLE_USER = 2;
	public static final Integer ROLE_STUDENT = 3;
	public static final Integer ROLE_KIOSK = 4;
	public static final Integer ROLE_COLLEGE = 5;

	public void register(User u);

	public User login(String loginName, String password);

	public List<User> getUserList();

	public void changeLoginStatus(Integer userId, Integer loginStatus);
	
	public void save(User u);

	public void update(User u);

	public void delete(User u);

	public void delete(Integer userId);

	public User findById(Integer userId);

	public List<User> findAll();

	public List<User> findByProperty(String propName, Object propValue);
	
	public List<User> findByListId(Integer userId);


}
