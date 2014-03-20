package com.ebuero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ebuero.dao.DeveloperDao;
import com.ebuero.model.Developer;

@Controller
@Transactional(readOnly=false)
public class DeveloperController {
	@Autowired
	private DeveloperDao devDao;
	
	@RequestMapping(value="/developers", method=RequestMethod.GET)
	public ModelAndView getDevelopers(ModelMap map) {
		List<Developer> developers = devDao.getAll();
		map.addAttribute("developers", developers);
		map.addAttribute("newDeveloper", new Developer());
		return new ModelAndView("developers");
	}
	
	@RequestMapping(value="/saveDeveloper", method=RequestMethod.POST)
	public @ResponseBody ModelAndView saveDeveloper(@ModelAttribute("newDeveloper") Developer newDeveloper, BindingResult result, SessionStatus status)
	{
		devDao.save(newDeveloper);
		return new ModelAndView("redirect:/developers.do");
	}

}
