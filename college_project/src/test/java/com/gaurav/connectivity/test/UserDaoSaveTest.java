package com.gaurav.connectivity.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gaurav.config.SpringRootConfig;
import com.gaurav.dao.UserDAO;
import com.gaurav.domain.User;

public class UserDaoSaveTest {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				SpringRootConfig.class)) {
			UserDAO bean = context.getBean(UserDAO.class);
			User u = new User();
			u.setFirstName("Zoella");
			u.setLastName("Sugg");
			u.setGender("M");
			u.setLogin("zoella");
			u.setPassword("zoe");
			u.setConfirmPassword("zoe");
			u.setLock(1);
			u.setMobile("5555");
			u.setRoleId(1);
			bean.save(u);

			System.out.println("User is saved");
		}
	}

}