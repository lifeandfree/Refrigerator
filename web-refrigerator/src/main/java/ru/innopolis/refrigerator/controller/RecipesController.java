package ru.innopolis.refrigerator.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.refrigerator.core.db.jdbc.exception.RecipeDAOException;
import ru.innopolis.refrigerator.service.AuthorizationServiceImpl;
import ru.innopolis.refrigerator.servlet.view.RecipesView;

import javax.servlet.http.Cookie;

@Controller
public class RecipesController {

	private static final Logger logger = LogManager.getLogger(RecipesController.class.getName());

	@RequestMapping(value = "/recipes", method = RequestMethod.GET)
	public ModelAndView renderRecipesForm(@CookieValue(value = AuthorizationServiceImpl.COOKIE_UID, required = false) Cookie usid) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("recipes");

		long uid = 0L;
		if (usid != null)
		{
			uid = Long.parseLong(usid.getValue());
		}
		RecipesView recipesView = new RecipesView(uid);
		try {
			modelAndView.addObject("recipes", recipesView.rendering());
		}
		catch (RecipeDAOException e) {
			logger.error(e.getMessage(), e.toString());
			modelAndView.addObject("recipes", e.getLocalizedMessage());
		}
		return modelAndView;
	}
}
