package ru.innopolis.refrigerator.service;

import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.core.model.refrigerator.RefrigeratorIngredient;

import java.util.List;

public interface SearchService {
	List<Recipe> searchRecipes(List<RefrigeratorIngredient> refrigeratorIngredients) throws Exception;
}
