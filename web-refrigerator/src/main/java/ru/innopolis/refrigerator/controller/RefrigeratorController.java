package ru.innopolis.refrigerator.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.refrigerator.service.AuthorizationServiceImpl;
import ru.innopolis.refrigerator.service.RefrigeratorService;
import ru.innopolis.refrigerator.service.RefrigeratorServiceImpl;
import ru.innopolis.refrigerator.service.exception.RefrigeratorServiceException;
import ru.innopolis.refrigerator.servlet.view.RefrigeratorView;

import javax.servlet.http.Cookie;

@Controller
public class RefrigeratorController {

	private static final Logger logger = LogManager.getLogger(RefrigeratorController.class.getName());
	RefrigeratorService refrigeratorService = new RefrigeratorServiceImpl();

	@RequestMapping(value = "/refrigerator", method = RequestMethod.GET)
	public ModelAndView renderRefForm(@CookieValue(value = AuthorizationServiceImpl.COOKIE_UID, required = false) Cookie usid) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("refrigerator");
		RefrigeratorView refrigeratorView = new RefrigeratorView(Long.parseLong(usid.getValue()));
		try {
			modelAndView.addObject("refrigeratorlist", refrigeratorView.rendering());
		}
		catch (RefrigeratorServiceException e) {
			logger.error(e);
			modelAndView.addObject("msgerror", e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/refrigerator/delete", method = RequestMethod.GET)
	public ModelAndView renderRecipeDeleteForm(@RequestParam(name = "id", required = true) String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/refrigerator");
		try {
			refrigeratorService.removeRefrigeratorIngredient(Long.valueOf(id));
		}
		catch (Exception e) {
			logger.error(e);
			modelAndView.addObject("msgerror", e.getMessage());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/refrigerator/save", method = RequestMethod.POST)
	public ModelAndView postRefForm(@RequestParam(name = "ingredient", required = true) String ingredient,
									@RequestParam(name = "quantity", required = true) String quantity,
									@RequestParam(name = "dimension", required = true) String dimension,
									@CookieValue(value = AuthorizationServiceImpl.COOKIE_UID, required = false) Cookie usid) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/refrigerator");
		long uid = 0L;
		String msg;
		if (usid != null)
		{
			uid = Long.parseLong(usid.getValue());
		}
		try {
			if (!refrigeratorService.createRefrigeratorElement(ingredient, dimension, quantity, uid)){
				msg = "Error for create Refrigerator Element.";
				logger.error(msg);
				modelAndView.addObject("msgerror", msg);
			}
		}
		catch (RefrigeratorServiceException e) {
			msg = "Error for create Refrigerator Element.";
			logger.error(msg);
			modelAndView.addObject("msgerror", msg);
		}

		return modelAndView;
	}
}
