package ru.innopolis.refrigerator.service;

import ru.innopolis.refrigerator.core.db.DaoFactory;
import ru.innopolis.refrigerator.core.db.exception.RecipeDAOException;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;

import java.util.List;

public class RecipesServiceImpl implements RecipesService {
	@Override
	public List<Recipe> getRecipe(Long uid) throws RecipeDAOException {
		return DaoFactory.getInstance().getRecipeDAO().getAllByUserId(uid);
	}
}
