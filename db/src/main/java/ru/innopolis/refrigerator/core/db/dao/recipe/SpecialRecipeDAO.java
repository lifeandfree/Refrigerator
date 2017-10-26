package ru.innopolis.refrigerator.core.db.dao.recipe;

import ru.innopolis.refrigerator.core.db.exception.RecipeDAOException;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;

import java.util.List;

public interface SpecialRecipeDAO {

	Long getId(Recipe recipe, long userId, long cookingMethodId) throws RecipeDAOException;

	List<Recipe> getAllByUserId(Long uid) throws RecipeDAOException;

	void add(Recipe recipe, long userId, long cookingMethodId) throws RecipeDAOException;

}
