package ru.innopolis.refrigerator.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.refrigerator.core.db.exception.RecipeIngredientDAOException;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.core.model.refrigerator.RefrigeratorIngredient;
import ru.innopolis.refrigerator.service.*;
import ru.innopolis.refrigerator.service.exception.RecipeServiceException;
import ru.innopolis.refrigerator.service.exception.RefrigeratorServiceException;
import ru.innopolis.refrigerator.servlet.view.RecipeView;
import ru.innopolis.refrigerator.servlet.view.RecipesView;

import javax.servlet.http.Cookie;
import java.util.List;

@Controller
public class SearchController {

	private static final Logger logger = LogManager.getLogger(SearchController.class.getName());
	SearchService searchService = new SearchServiceImpl();
	RefrigeratorService refrigeratorService = new RefrigeratorServiceImpl();
	private static RecipeService rc = new RecipeServiceImpl();


	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView renderSearchForm(@CookieValue(value = AuthorizationServiceImpl.COOKIE_UID, required = false) Cookie usid) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("search");

		Long uid = Long.parseLong(usid.getValue());

		List<RefrigeratorIngredient> refrigeratorIngredient = null;

		try {
			refrigeratorIngredient = refrigeratorService.getRefrigeratorIngredient(uid);
			}
		catch (RefrigeratorServiceException e) {
			logger.error(e);
			modelAndView.addObject("msgerror", e);
		}

		try {
			RecipesView recipesView = new RecipesView();
			modelAndView.addObject("recipes", recipesView.renderingForSearch(searchService.searchRecipes(refrigeratorIngredient)));
		}
		catch (Exception e) {
			logger.error(e);
			modelAndView.addObject("msgerror", e);
		}

		return modelAndView;
	}


	@RequestMapping(value = "/search/open", method = RequestMethod.GET)
	public ModelAndView renderSearchForm(@RequestParam(name = "id", required = true) String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("readrecipe");
		Recipe recipe = null;
		try {
			recipe = rc.getRecipe(Long.valueOf(id));
		}
		catch (Exception e) {
			logger.error(e);
			modelAndView.addObject("msgerror", e.getMessage());
		}
		modelAndView.addObject("recipename", recipe.getName());
		modelAndView.addObject("timerecipe", recipe.getTime());
		modelAndView.addObject("complexity", recipe.getComplexity());
		modelAndView.addObject("instructions", recipe.getInstructions());
		modelAndView.addObject("cookingmethod", recipe.getCookingMethod().getName());
		RecipeView recipeView = new RecipeView();
		try {
			modelAndView.addObject("ingredients", recipeView.getNotVisibleRenderIngredient(rc.getIngredients(recipe)));
		}
		catch (RecipeServiceException e) {
			logger.error(e);
			modelAndView.addObject("msgerror", e.getMessage());
		}
		catch (RecipeIngredientDAOException e) {
			logger.error(e);
			modelAndView.addObject("msgerror", e.getMessage());
		}


		return modelAndView;
	}
}
