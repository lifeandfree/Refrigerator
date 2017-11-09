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
import ru.innopolis.refrigerator.core.model.enumcls.Complexity;
import ru.innopolis.refrigerator.core.model.enumcls.ComplexityMethod;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.service.AuthorizationServiceImpl;
import ru.innopolis.refrigerator.service.RecipeService;
import ru.innopolis.refrigerator.service.RecipeServiceImpl;
import ru.innopolis.refrigerator.service.exception.RecipeServiceException;
import ru.innopolis.refrigerator.servlet.view.RecipeView;
import ru.innopolis.refrigerator.servlet.view.RecipesView;

import javax.persistence.ManyToMany;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RecipeController {

	private static final Logger logger = LogManager.getLogger(RecipeController.class.getName());
	private static RecipeService rc = new RecipeServiceImpl();

	@RequestMapping(value = "/recipe/add", method = RequestMethod.GET)
	public ModelAndView renderRecipeAddForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("recipe");
		modelAndView.addObject("complexityoptions", ComplexityMethod.getOptionsForSelect());
		return modelAndView;
	}

	@RequestMapping(value = "/recipe/edit", method = RequestMethod.GET)
	public ModelAndView renderRecipeEditForm(@RequestParam(name = "id", required = true) String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("edit");
		Recipe recipe = null;
		try {
			recipe = rc.getRecipe(Long.valueOf(id));
		}
		catch (Exception e) {
			logger.error(e);
			modelAndView.addObject("msgerror", e.getMessage());
		}
		modelAndView.addObject("complexityoptions", ComplexityMethod.getOptionsForSelect());
		modelAndView.addObject("recipename", recipe.getName());
		modelAndView.addObject("timerecipe", recipe.getTime());
		modelAndView.addObject("complexity", recipe.getComplexity());
		modelAndView.addObject("instructions", recipe.getInstructions());
		modelAndView.addObject("cookingmethod", recipe.getCookingMethod().getName());
		RecipeView recipeView = new RecipeView();
		try {
			modelAndView.addObject("ingredients", recipeView.getRenderIngredient(rc.getIngredients(recipe)));
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

	@RequestMapping(value = "/recipe/delete", method = RequestMethod.GET)
	public ModelAndView renderRecipeDeleteForm(@RequestParam(name = "id", required = true) String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/recipes");
		try {
			rc.removeRecipe(Long.valueOf(id));
		}
		catch (Exception e) {
			logger.error(e);
			modelAndView.addObject("msgerror", e.getMessage());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/recipe/add", method = RequestMethod.POST)
	public ModelAndView postAddRecipe(@RequestParam Map<String,String> allRequestParams,
									  @CookieValue(value = AuthorizationServiceImpl.COOKIE_UID, required = false) Cookie usid,
									  HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/recipes");

		String recipename = allRequestParams.get("recipename");
		String timerecipe = allRequestParams.get("timerecipe");
		String complexity = allRequestParams.get("complexity");
		String instructions = allRequestParams.get("instructions");
		String cookingmethod = allRequestParams.get("cookingmethod");

		Map<Ingredient, Double> ingredients = new HashMap<>();
		for (int i = 0; i < ((allRequestParams.size()-5)/2); i++) {
			String ingredientName = allRequestParams.get("ingredient" + i);
			if (ingredientName != null) {
				Double quantity = Double.parseDouble(allRequestParams.get("quantity" + i));
				String dimension = allRequestParams.get("dimension" + i);
				Ingredient ingredient = new Ingredient(ingredientName, dimension);
				ingredients.put(ingredient, quantity);
			}
		}

		try {
			rc.createRecipe(recipename, timerecipe, complexity, instructions, cookingmethod, ingredients, Long.parseLong(usid.getValue()));
		}
		catch (Exception e) {
			logger.error(e);
			modelAndView.addObject("msgerror", e.getMessage());
		}


		return modelAndView;
	}

	@RequestMapping(value = "/recipe/edit", method = RequestMethod.POST)
	public ModelAndView postEditRecipe(@RequestParam Map<String,String> allRequestParams,
									   @CookieValue(value = AuthorizationServiceImpl.COOKIE_UID, required = false) Cookie usid,
									   @RequestParam(name = "id", required = true) String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/recipes");

		String recipename = allRequestParams.get("recipename");
		String timerecipe = allRequestParams.get("timerecipe");
		String complexity = allRequestParams.get("complexity");
		String instructions = allRequestParams.get("instructions");
		String cookingmethod = allRequestParams.get("cookingmethod");

		Map<Ingredient, Double> ingredients = new HashMap<>();
		for (int i = 0; i < ((allRequestParams.size()-5)/2); i++) {
			String ingredientName = allRequestParams.get("ingredient" + i);
			if (ingredientName != null) {
				Double quantity = Double.parseDouble(allRequestParams.get("quantity" + i));
				String dimension = allRequestParams.get("dimension" + i);
				Ingredient ingredient = new Ingredient(ingredientName, dimension);
				ingredients.put(ingredient, quantity);
			}
		}

		try {
			rc.createRecipe(recipename, timerecipe, complexity, instructions, cookingmethod, ingredients, Long.parseLong(usid.getValue()));
		}
		catch (Exception e) {
			logger.error(e);
			modelAndView.addObject("msgerror", e.getMessage());
		}


		return modelAndView;
	}
}
