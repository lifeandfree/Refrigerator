package ru.innopolis.refrigerator.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.DaoFactory;
import ru.innopolis.refrigerator.core.db.exception.RecipeIngredientDAOException;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;
import ru.innopolis.refrigerator.core.model.enumcls.Complexity;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.core.model.recipe.RecipeIngredient;
import ru.innopolis.refrigerator.core.model.user.User;
import ru.innopolis.refrigerator.service.exception.RecipeServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeServiceImpl implements RecipeService {

	private static final Logger logger = LogManager.getLogger(RecipeServiceImpl.class.getName());

	@Override
	public boolean createRecipe(String name, String timerecipe, String complexity, String instructions, String cookingmethod, Map<Ingredient, Double> ingredients, long uid) throws Exception {
		if(name == null || name.length() == 0)
			throw new RecipeServiceException("Name is null");
		if (timerecipe == null)
			throw new RecipeServiceException("Time is null");
		if (complexity == null)
			throw new RecipeServiceException("complexity is null");
		if (instructions == null)
			throw new RecipeServiceException("instructions is null");
		if (cookingmethod == null)
			throw new RecipeServiceException("cookingmethod is null");
		if (ingredients == null || ingredients.size() == 0)
			throw new RecipeServiceException("ingredients is null");

		String[] timesplit = timerecipe.split(":");
		int time = Integer.parseInt(timesplit[0].replaceAll("^0", ""))*60 + Integer.parseInt(timesplit[1].replaceAll("^0", ""));
		Complexity complexityRecipe = Complexity.valueOf(complexity);

		CookingMethod cookingMethod = DaoFactory.getInstance().getCookingMethodDAO().getByName(name);
		if (cookingMethod == null){
			cookingMethod = (CookingMethod) DaoFactory.getInstance().getCookingMethodDAO().add(new CookingMethod(cookingmethod));
		}

		for (Map.Entry<Ingredient, Double> entry: ingredients.entrySet()) {
			//String key = (String) entry.getKey();
			Ingredient ingredient =  entry.getKey();
			Ingredient addIngredient = DaoFactory.getInstance().getIngredientDAO().getIngredientByNameDimension(ingredient.getName(), ingredient.getDimension());
			if (addIngredient == null){
				ingredient = (Ingredient) DaoFactory.getInstance().getIngredientDAO().add(ingredient);
			}
		}

		Recipe recipe = new Recipe();
		recipe.setName(name);
		recipe.setCookingMethod(cookingMethod);
		recipe.setTime(time);
		recipe.setIngredients(ingredients);
		recipe.setComplexity(complexityRecipe);
		recipe.setInstructions(instructions);
		recipe.setUser((User) DaoFactory.getInstance().getUserDAO().getById(uid));
		recipe = (Recipe) DaoFactory.getInstance().getIngredientDAO().add(recipe);


		for (Map.Entry<Ingredient, Double> entry: ingredients.entrySet()) {
			Double value = (Double) entry.getValue();
			Ingredient ingredient =  entry.getKey();
			RecipeIngredient recipeIngredient = new RecipeIngredient();
			recipeIngredient.setIngredient(ingredient);
			recipeIngredient.setQuantity(value);
			recipeIngredient.setRecipe(recipe);
			DaoFactory.getInstance().getRecipeIngredientDAO().add(recipeIngredient);
		}
		if (recipe == null){
			return true;
		}
		return true;
	}

	@Override
	public boolean removeRecipe(Long recipeId) throws Exception {
		Recipe recipe = (Recipe) DaoFactory.getInstance().getRecipeDAO().getById(recipeId);
		DaoFactory.getInstance().getRecipeIngredientDAO().deleteAllByRecipe(recipe);
		DaoFactory.getInstance().getRecipeDAO().delete(recipe);
		return true;
	}

	@Override
	public Recipe getRecipe(Long recipeId) throws Exception {
		return (Recipe) DaoFactory.getInstance().getRecipeDAO().getById(recipeId);
	}

	@Override
	public Map<Ingredient, Double> getIngredients(Recipe recipe) throws RecipeServiceException, RecipeIngredientDAOException {
		if (recipe == null) {
			String message = "Для запроса необходим рецепт.";
			logger.error(message);
			throw new RecipeServiceException(message);
		}
		List<RecipeIngredient> recipeIngredients = DaoFactory.getInstance().getRecipeIngredientDAO().getIngredientByRecipe(recipe);
		Map<Ingredient, Double> ingredients = new HashMap<>();
		for (RecipeIngredient recipeIngredient : recipeIngredients) {
			ingredients.put(recipeIngredient.getIngredient(), recipeIngredient.getQuantity());
		}
		return ingredients;
	}
}
