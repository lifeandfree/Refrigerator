package ru.innopolis.refrigerator.xml.serialization.refrigerator;

import ru.innopolis.refrigerator.core.Constants;
import ru.innopolis.refrigerator.core.model.*;
import ru.innopolis.refrigerator.xml.serialization.ObjectJaxbParser;
import ru.innopolis.refrigerator.xml.serialization.recipe.Recipes;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefrigeratorJaxbParserMain {

	public static void main(String[] args) {

		IngredientCategory ingredientCategory = new IngredientCategory("овощь");
		List<IngredientCategory> ingredientCategorys = new ArrayList<IngredientCategory>();
		ingredientCategorys.add(ingredientCategory);

		Ingredient ingredient = new Ingredient("Морковь", "гр", ingredientCategorys);
		Ingredient ingredient2 = new Ingredient("Картошка", "гр", ingredientCategorys);
		Map<Ingredient, Double> ingredientStringMap = new HashMap<>();
		ingredientStringMap.put(ingredient, 100d);
		ingredientStringMap.put(ingredient2, 1000d);

		User user = new User("user1", "pass1", (byte) 1, "emael1");

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

		List<RecipeCategory> recipeCategories = new ArrayList<>();
		RecipeCategory recipeCategory = new RecipeCategory("гарниры");
		recipeCategories.add(recipeCategory);

		Map<Ingredient, Double> ingredients = new HashMap<>();
		ingredients.put(ingredient, 50d);
		ingredients.put(ingredient2, 500d);

		CookingMethod cookingMethod = new CookingMethod("Варить");

		Recipe recipe = new Recipe("name1", recipeCategories, (byte) 3, 1000, ingredients, "1. сварить", null, cookingMethod);

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
