package com.gaurav.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gaurav.domain.Student;
import com.gaurav.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	StudentService studentService;

	Logger logger = Logger.getLogger(this.getClass().getName());

	@RequestMapping(value = "/student/student_form")
	public String studentForm(Model model) {
		Student student = new Student();
		model.addAttribute("command", student);
		return "student_form";
	}

	@RequestMapping(value = "/student/edit_student")
	public String prepareEditForm(Model model, HttpSession session, @RequestParam("sid") Integer studentId) {
		Student findById = studentService.findById(studentId);
		model.addAttribute("command", findById);
		return "student_form";
	}
	
	/**
	@RequestMapping(value = "/student/save_student")
	public String saveContact(@ModelAttribute("command") Student s, Model m, BindingResult result) {
		if (result.hasErrors()) {
			return "student-form";
		} else {
			studentService.saveStudent(s);
			return "redirect:slist?act=act";
		}
	}
	*/

	@RequestMapping(value = "/student/save_student")
	public String saveContacts(@ModelAttribute("command") Student s, Model m, BindingResult result) {
		Integer studentId = s.getStudentId();
		if (studentId == null) {
			studentService.saveStudent(s);
			return "redirect:slist?act=act";
		} else {
			studentService.update(s);
			return "redirect:slist?act=act";
		}
	}

	@RequestMapping(value = "/student/slist")
	public String collegeList(Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		model.addAttribute("studentList", studentService.findAll());
		return "slist";
	}

	@RequestMapping(value = "/student/del_student")
	public String deleteContact(@RequestParam("sid") Integer studentId) {
		studentService.delete(studentId);
		return "redirect:slist?act=del";
	}

}
