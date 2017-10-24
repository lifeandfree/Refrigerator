package ru.innopolis.refrigerator.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	private static final Logger logger = LogManager.getLogger(MainController.class.getName());

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView renderMainForm() {
		ModelAndView modelAndViewRec = new ModelAndView("redirect:recipes");
		return  modelAndViewRec;
	}


}
