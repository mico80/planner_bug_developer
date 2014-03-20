package com.ebuero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional(readOnly=true)
public class MainController {
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView goHome() {
		return new ModelAndView("index");
	}
}
