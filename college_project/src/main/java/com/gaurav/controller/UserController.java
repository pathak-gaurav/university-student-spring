package com.gaurav.controller;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gaurav.command.LoginCommand;
import com.gaurav.domain.User;
import com.gaurav.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@RequestMapping(value = { "/", "/index" })
	public String index(Model m) {
		m.addAttribute("command", new LoginCommand());
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginHandle(@ModelAttribute("command") LoginCommand cmd, Model model, HttpSession session) {
		try {
			User login = userService.login(cmd.getLogin(), cmd.getPassword());
			if (login == null) {
				model.addAttribute("err", "Login Failed! Enter valid credentials");
				return "index";
			} else {
				if (login.getRoleId().equals(userService.ROLE_ADMIN)) {
					addUserInSession(login, session);
					return "redirect:admin/dashboard";
				} else if (login.getRoleId().equals(userService.ROLE_USER)) {
					addUserInSession(login, session);
					return "redirect:user/dashboard";
				} else if (login.getRoleId().equals(userService.ROLE_STUDENT)) {
					addUserInSession(login, session);
					return "redirect:student/dashboard";
				} else if (login.getRoleId().equals(userService.ROLE_KIOSK)) {
					addUserInSession(login, session);
					return "redirect:kiosk/dashboard";
				} else if (login.getRoleId().equals(userService.ROLE_COLLEGE)) {
					addUserInSession(login, session);
					return "redirect:college/dashboard";
				} else {
					model.addAttribute("err", "Invalid User Role!");
					return "index";
				}
			}
		} catch (Exception e) {
			model.addAttribute("err", e.getMessage());
			return "index";
		}
	}

	private void addUserInSession(User u, HttpSession session) {
		session.setAttribute("user", u);
		session.setAttribute("userId", u.getUserId());
		session.setAttribute("role", u.getRoleId());
	}

	@RequestMapping("/logout")
	private String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index?act=lo";
	}

	@RequestMapping("/user/dashboard")
	public String userDashboard() {
		return "dashboard_user";
	}

	@RequestMapping("/admin/dashboard")
	public String adminDashboard() {
		return "dashboard_admin";
	}

	@RequestMapping("/student/dashboard")
	public String studentDashboard() {
		return "dashboard_student";
	}

	@RequestMapping("/college/dashboard")
	public String collegeDashboard() {
		return "dashboard_college";
	}

	@RequestMapping("/kiosk/dashboard")
	public String kioskDashboard() {
		return "dashboard_kiosk";
	}

	@RequestMapping(value = "/user/user_form")
	public String userForm(Model model) {
		User user = new User();
		model.addAttribute("command", user);
		return "user_form";
	}

	@RequestMapping(value = "/user/edit_user")
	public String prepareEditForm(Model model, HttpSession session, @RequestParam("uid") Integer userId) {
		User findById = userService.findById(userId);
		model.addAttribute("command", findById);
		return "user_form";
	}
	
	/*
	 * Below method is depricated. 
	 *

	//@RequestMapping(value = "/user/save_user")
	public String saveContactOld(@ModelAttribute("command") User u, Model m, BindingResult result) {
		if (result.hasErrors()) {
			return "user-form";
		} else {
			userService.save(u);
			return "redirect:ulist?act=act";
		}
	}
	*
	*/

	// This is saving and update testing
	@RequestMapping(value = "/user/save_user")
	public String saveContact(@ModelAttribute("command") User u, Model m, BindingResult result) {
		Integer userId = u.getUserId();
		if (userId == null) {
			userService.save(u);
			return "redirect:ulist?act=act";
		}
		else {
			userService.update(u);
			return "redirect:ulist?act=act";
		}
	}

	@RequestMapping(value = "/user/ulist")
	public String collegeList(Model model, HttpSession session) {
		model.addAttribute("userList", userService.findAll());
		return "ulist";
	}

	@RequestMapping(value = "/user/del_user")
	public String deleteContact(@RequestParam("uid") Integer userId) {
		userService.delete(userId);
		return "redirect:ulist?act=del";
	}

	@RequestMapping(value = "/user/my_profile")
	public String getMarks(Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		logger.info("User Id is coming as:" + userId);
		model.addAttribute("userList", userService.findByListId(userId));
		return "my_profile";
	}

	@RequestMapping(value = "/user/home_user_form")
	public String home_user_form(Model model) {
		User user = new User();
		model.addAttribute("command", user);
		return "home_user_form";
	}

	@RequestMapping(value = "/user/save_home_user")
	public String saveHomePageUser(@ModelAttribute("command") User u, Model m, BindingResult result) {
		if (result.hasErrors()) {
			return "home_user_form";
		} else {
			userService.save(u);
			return "redirect:/index?act=succ";
		}
	}

}
