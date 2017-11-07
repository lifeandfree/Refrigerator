package ru.innopolis.refrigerator.service;

import ru.innopolis.refrigerator.core.db.exception.RecipeDAOException;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;

import java.util.List;

public interface RecipesService {
	List<Recipe> getRecipe(Long uid) throws RecipeDAOException;
}
