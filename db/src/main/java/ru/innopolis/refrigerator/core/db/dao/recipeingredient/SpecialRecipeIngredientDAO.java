package ru.innopolis.refrigerator.core.db.dao.recipeingredient;

import ru.innopolis.refrigerator.core.db.exception.RecipeIngredientDAOException;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.core.model.recipe.RecipeIngredient;

import java.util.List;

public interface SpecialRecipeIngredientDAO {
	boolean deleteAllByRecipe(Recipe recipe) throws Exception;

	List<RecipeIngredient> getIngredientByRecipe(Recipe recipe) throws RecipeIngredientDAOException;
}
