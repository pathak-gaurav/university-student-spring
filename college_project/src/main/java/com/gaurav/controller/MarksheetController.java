package com.gaurav.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gaurav.domain.Marksheet;
import com.gaurav.service.MarksheetService;

@Controller
public class MarksheetController {

	@Autowired
	MarksheetService marksheetService;

	Logger logger = Logger.getLogger(this.getClass().getName());

	@RequestMapping(value = "/marksheet/marksheet_form")
	public String markSheetForm(Model model) {
		Marksheet marksheet = new Marksheet();
		model.addAttribute("command", marksheet);
		return "marksheet_form";
	}

	/** Below /marksheet/edit_marksheet is incomplete do not use */
	@RequestMapping(value = "/marksheet/edit_marksheet")
	public String prepareEditForm(Model model, HttpSession session, @RequestParam("mid") Integer marksheetId) {
		Marksheet findMarksheetById = marksheetService.findMarksheetById(marksheetId);
		System.out.println("Marksheet Id is :"+marksheetId);
		model.addAttribute("user",findMarksheetById);
		return "marksheet_form";
	}

	@RequestMapping(value = "/marksheet/save_marksheet")
	public String saveContact(@ModelAttribute("command") Marksheet m, Model model, BindingResult result) {
			Integer id = m.getId();
			if (id == null) {
				marksheetService.saveMarksheet(m);
				return "redirect:mlist?act=act";
			}else {
				marksheetService.update(m);
				return "redirect:mlist?act=act";
			}
			
	}

	@RequestMapping(value = "/marksheet/mlist")
	public String collegeList(Model model, HttpSession session) {
		model.addAttribute("marksheetList", marksheetService.findAllMarksheet());
		return "mlist";
	}

	@RequestMapping(value = "/marksheet/del_marksheet")
	public String deleteContact(@RequestParam("mid") Integer marksheetId) {
		marksheetService.delete(marksheetId);
		return "redirect:mlist?act=del";
	}

	
	@RequestMapping(value = "/marksheet/my_marks")
	public String getMarks(Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		logger.info("User Id is coming as:"+userId);
		model.addAttribute("marksheetList", marksheetService.findByUserId(userId));
		return "my_marks";
	}
	
	@RequestMapping(value = "/marksheet/merit_list")
	public String getMeritList(Model model, HttpSession session) {
		model.addAttribute("marksheetList", marksheetService.MeritList());
		return "merit_list";
	}

}
