package ru.innopolis.refrigerator.service;

import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.refrigerator.core.db.DaoFactory;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.core.model.recipe.RecipeIngredient;
import ru.innopolis.refrigerator.core.model.refrigerator.RefrigeratorIngredient;

import java.util.ArrayList;
import java.util.List;

public class SearchServiceImpl implements SearchService {

	public static void main(String[] args) {

	}

	@Override
	@Transactional
	public List<Recipe> searchRecipes(List<RefrigeratorIngredient> refrigeratorIngredients) throws Exception {

		List<Ingredient> refIngredients = new ArrayList<>();
		for (RefrigeratorIngredient refrigeratorIngredient : refrigeratorIngredients) {
			refIngredients.add(refrigeratorIngredient.getIngredient());
		}

		List<Recipe> avalRecipes = new ArrayList<>();

		List<Recipe> recipes = (List<Recipe>) DaoFactory.getInstance().getRecipeDAO().getAll();
		for (Recipe recipe:recipes
			 ) {
			List<RecipeIngredient> recipeIngredients = DaoFactory.getInstance().getRecipeIngredientDAO().getIngredientByRecipe(recipe);
			List<Ingredient> ingredients = new ArrayList<>();
			for (RecipeIngredient recipeIngredient : recipeIngredients) {
				ingredients.add(recipeIngredient.getIngredient());
			}
			//List<Ingredient> ingredients = DaoFactory.getInstance().getIngredientDAO().getIngredientByRecipe(recipe);
			if (refIngredients.containsAll(ingredients)){
				avalRecipes.add(recipe);
			}
		}

		return avalRecipes;
	}

}
