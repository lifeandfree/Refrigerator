package ru.innopolis.refrigerator.xml.serialization;

import ru.innopolis.refrigerator.core.Constants;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;
import ru.innopolis.refrigerator.core.model.enumcls.Complexity;
import ru.innopolis.refrigerator.core.model.enumcls.Role;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.core.model.recipecategory.RecipeCategory;
import ru.innopolis.refrigerator.core.model.refrigerator.Refrigerator;
import ru.innopolis.refrigerator.core.model.user.User;
import ru.innopolis.refrigerator.xml.serialization.recipe.Recipes;
import ru.innopolis.refrigerator.xml.serialization.refrigerator.Refrigerators;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.*;

public class JaxbParserMain {

	public static void main(String[] args) {
		IngredientCategory ingredientCategory = new IngredientCategory("овощь");
		Set<IngredientCategory> ingredientCategorys = new HashSet<>();
		ingredientCategorys.add(ingredientCategory);

		Ingredient ingredient = new Ingredient("Морковь", "гр", ingredientCategorys);
		Ingredient ingredient2 = new Ingredient("Картошка", "гр", ingredientCategorys);
		Map<Ingredient, Double> ingredientStringMap = new HashMap<>();
		ingredientStringMap.put(ingredient, 100d);
		ingredientStringMap.put(ingredient2, 1000d);

		User user = new User("user1", "pass1", Role.user, "emael1");

		Refrigerator refrigerator = new Refrigerator("ref1", user, ingredientStringMap);
		List<Refrigerator> refrigeratorsList = new ArrayList<>();
		refrigeratorsList.add(refrigerator);
		Refrigerators refrigerators = new Refrigerators();
		refrigerators.setRefrigerators(refrigeratorsList);

		ObjectJaxbParser parser = new ObjectJaxbParser();
		try {
			parser.saveObject(new File(Constants.REFRIGERATORS_XML_FILENAME), refrigerators);
		}
		catch (JAXBException e) {
			e.printStackTrace();
		}

		try {
			Refrigerators parseRefregirator = (Refrigerators) parser.getObject(new File(Constants.REFRIGERATORS_XML_FILENAME), Refrigerators.class);
			System.out.println(parseRefregirator.toString());
		}
		catch (JAXBException e) {
			e.printStackTrace();
		}

		Set<RecipeCategory> recipeCategories = new HashSet<>();
		RecipeCategory recipeCategory = new RecipeCategory("гарниры");
		recipeCategories.add(recipeCategory);

		HashMap<Ingredient, Double> ingredients = new HashMap<>();
		ingredients.put(ingredient, 50d);
		ingredients.put(ingredient2, 500d);

		CookingMethod cookingMethod = new CookingMethod("Варить");

		Recipe recipe = new Recipe("name1", recipeCategories, Complexity.junior, 1000, ingredients, "1. сварить", null, cookingMethod, user);

		List<Recipe> recipersList = new ArrayList<>();
		recipersList.add(recipe);

		Recipes recipes = new Recipes();
		recipes.setRecipes(recipersList);

		ObjectJaxbParser parserRecipe = new ObjectJaxbParser();
		try {
			parserRecipe.saveObject(new File(Constants.RECIPES_XML_FILENAME), recipes);
		}
		catch (JAXBException e) {
			e.printStackTrace();
		}

		try {
			Recipes parseRecipes = (Recipes) parserRecipe.getObject(new File(Constants.RECIPES_XML_FILENAME), Recipes.class);
			System.out.println(parseRecipes.toString());
		}
		catch (JAXBException e) {
			e.printStackTrace();
		}


	}
}
