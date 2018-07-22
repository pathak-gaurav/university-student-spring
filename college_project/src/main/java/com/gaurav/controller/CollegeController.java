package com.gaurav.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gaurav.domain.College;
import com.gaurav.domain.Student;
import com.gaurav.service.CollegeService;
import com.gaurav.service.MarksheetService;
import com.gaurav.service.StudentService;

@Controller
public class CollegeController {

	@Autowired
	CollegeService collegeService;
	
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@RequestMapping(value = "/college/college_form")
	public String contactForm(Model model) {
		College college = new College();
		model.addAttribute("command", college);
		return "college_form";
	}

	@RequestMapping(value = "/college/edit_college")
	public String prepareEditForm(Model model, HttpSession session, @RequestParam("cid") Integer collegeId) {
		session.setAttribute("aCollegeId", collegeId);
		College c = collegeService.findCollegeById(collegeId);
		model.addAttribute("command", c);
		return "college_form";
	}
	
	@RequestMapping(value = "/college/save_college")
	public String saveContact(@ModelAttribute("command") College c, Model m, HttpSession session) {
		Integer collegeId = (Integer) session.getAttribute("aCollegeId");
		if (collegeId == null) {
			try {
				Integer userId = (Integer) session.getAttribute("userId");
				c.setId(userId);
				collegeService.saveCollege(c);
				return "redirect:clist?act=sv";
			} catch (Exception e) {
				e.printStackTrace();
				m.addAttribute("err", "Unable to save the college!");
				return "college_form";
			}
		} else {

			try {
				c.setId(collegeId);
				collegeService.update(c);
				return "redirect:clist?act=ed";
			} catch (Exception e) {
				e.printStackTrace();
				m.addAttribute("err", "Failed to edit the college!");
				return "college_form";
			}
		}
	}
	
	@RequestMapping(value = "/college/clist")
	public String collegeList(Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		model.addAttribute("collegeList", collegeService.findAllColleges());
		return "clist";
	}
	
	@RequestMapping(value = "/college/del_college")
	public String deleteContact(@RequestParam("cid") Integer collegeId) {
		collegeService.delete(collegeId);
		return "redirect:clist?act=del";
	}
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/college/college_students")
	public String getCollegeStudents(Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		logger.info("User Id of college is coming as:"+userId);
		model.addAttribute("studentList", studentService.findByCollegeId(userId));
		return "college_students";
	}
	
	@Autowired
	private MarksheetService marksheetService;
	
	@RequestMapping(value = "/college/college_student_marksheet")
	public String getCollegeStudentMarksheet(Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		logger.info("User Id of college is coming as:"+userId);
		model.addAttribute("marksheetList", marksheetService.findMarksheetByCollegeId(userId));
		return "college_student_marksheet";
	}
	
	@RequestMapping(value = "/college/student_byCollege")
	public String studentAddByCollege(Model model) {
		Student student = new Student();
		model.addAttribute("command", student);
		return "student_byCollege";
	}
	
	@RequestMapping(value = "/college/student_addByCollege")
	public String studentAddByCollege(Model model, HttpSession session,@ModelAttribute("command") Student s) {
				Integer userId = (Integer) session.getAttribute("userId");
				College collegeIdBasedOnUserId = collegeService.getCollegeIdBasedOnUserId(userId);
				s.setCollegeId(collegeIdBasedOnUserId.getId());
				studentService.saveStudent(s);
				return "redirect:college_students?act=sv";
	}
}
