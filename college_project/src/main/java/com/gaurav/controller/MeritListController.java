package com.gaurav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gaurav.service.MarksheetService;

@Controller
public class MeritListController {
	
	@Autowired
	MarksheetService marksheetService;
	
	
}
