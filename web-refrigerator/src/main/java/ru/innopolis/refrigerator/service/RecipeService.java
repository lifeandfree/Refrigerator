package ru.innopolis.refrigerator.service;

import ru.innopolis.refrigerator.core.db.exception.RecipeIngredientDAOException;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.service.exception.RecipeServiceException;

import java.util.Map;

public interface RecipeService {
	boolean createRecipe(String name, String timerecipe, String complexity, String instructions, String cookingmethod, Map<Ingredient, Double> ingredients, long uid) throws Exception;

	boolean removeRecipe(Long recipeId) throws Exception;

	Recipe getRecipe(Long recipeId) throws Exception;

	Map<Ingredient,Double> getIngredients(Recipe recipe) throws RecipeServiceException, RecipeIngredientDAOException;
}
