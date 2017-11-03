package ru.innopolis.refrigerator.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecipeController {

	private static final Logger logger = LogManager.getLogger(RecipeController.class.getName());

	@RequestMapping(value = "/recipe/add", method = RequestMethod.GET)
	public ModelAndView renderRecipeAddForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("recipe");
		return modelAndView;
	}
}
