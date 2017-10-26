package ru.innopolis.refrigerator.service.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.hibernate.dao.DaoFactory;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;
import ru.innopolis.refrigerator.core.model.enumcls.Complexity;
import ru.innopolis.refrigerator.core.model.enumcls.Role;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.core.model.recipe.RecipeIngredient;
import ru.innopolis.refrigerator.core.model.recipecategory.RecipeCategory;
import ru.innopolis.refrigerator.core.model.refrigerator.Refrigerator;
import ru.innopolis.refrigerator.core.model.refrigerator.RefrigeratorIngredient;
import ru.innopolis.refrigerator.core.model.session.Session;
import ru.innopolis.refrigerator.core.model.user.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainHi {
	private static final Logger logger = LogManager.getLogger(MainHi.class.getName());
	public static void main(String[] args) throws Exception {
		writeBD();
	}

	public static void createBD() throws Exception {
		DaoFactory.getInstance().getUserDAO().getAll();
	}

	public static void writeBD() throws Exception {
		User user2 = new User("tmp2", PasswordEncoder.passwordEncode("pass2"), Role.user, "ddggg@ggg.ru");
		DaoFactory.getInstance().getUserDAO().add(user2);

		Session session = new Session("ssgtrhtrhrt", user2, "dvsdv", System.currentTimeMillis(), System.currentTimeMillis(), false);
		DaoFactory.getInstance().getSessionDAO().add(session);

		IngredientCategory ingredientCategory = new IngredientCategory("овощь");
		DaoFactory.getInstance().getIngredientCategoryDAO().add(ingredientCategory);

		IngredientCategory ingredientCategory2 = new IngredientCategory("фрукт");
		DaoFactory.getInstance().getIngredientCategoryDAO().add(ingredientCategory2);

		Set<IngredientCategory> ingredientCategorys = new HashSet<>();
		ingredientCategorys.add(ingredientCategory);
		Ingredient ingredient = new Ingredient("Морковь", "гр", ingredientCategorys);
		DaoFactory.getInstance().getIngredientDAO().add(ingredient);

		Ingredient ingredient3 = new Ingredient("Огурец", "гр", ingredientCategorys);
		DaoFactory.getInstance().getIngredientDAO().add(ingredient3);

		Set<IngredientCategory> ingredientCategorys2 = new HashSet<>();
		ingredientCategorys2.add(ingredientCategory);
		ingredientCategorys2.add(ingredientCategory2);
		Ingredient ingredient2 = new Ingredient("Картошка", "гр", ingredientCategorys2);
		DaoFactory.getInstance().getIngredientDAO().add(ingredient2);

		Map<Ingredient, Double> ingredientStringMap = new HashMap<>();
		double ingredientvalue = 100d;
		ingredientStringMap.put(ingredient, ingredientvalue);
		double ingredientvalue2 = 1000d;
		ingredientStringMap.put(ingredient2, ingredientvalue2);

		Map<Ingredient, Double> ingredientStringMap2 = new HashMap<>();
		double ingredient2value = 100d;
		ingredientStringMap2.put(ingredient, ingredient2value);
		double ingredient2value2 = 10000d;
		ingredientStringMap2.put(ingredient3, ingredient2value2);

		User user = new User("user1", PasswordEncoder.passwordEncode("pass1"), Role.user, "emael1");
		DaoFactory.getInstance().getUserDAO().add(user);

		Session session2 = new Session("ssgtrhtrhrt", user, "dvsdv", System.currentTimeMillis(), System.currentTimeMillis(), false);
		DaoFactory.getInstance().getSessionDAO().add(session2);

		Refrigerator refrigerator = new Refrigerator("ref1", user, ingredientStringMap); //TODO
		DaoFactory.getInstance().getRefrigeratorDAO().add(refrigerator);
//
		Refrigerator refrigerator2 = new Refrigerator("ref2", user2, ingredientStringMap2); //TODO
		DaoFactory.getInstance().getRefrigeratorDAO().add(refrigerator2);

		RefrigeratorIngredient refrigeratorIngredient = new RefrigeratorIngredient(refrigerator, ingredient, ingredientvalue);
		DaoFactory.getInstance().getRefrigeratorIngredientDAO().add(refrigeratorIngredient);

		RefrigeratorIngredient refrigeratorIngredient2 = new RefrigeratorIngredient(refrigerator, ingredient2, ingredientvalue2);
		DaoFactory.getInstance().getRefrigeratorIngredientDAO().add(refrigeratorIngredient2);

		RefrigeratorIngredient refrigeratorIngredient3 = new RefrigeratorIngredient(refrigerator2, ingredient, ingredient2value);
		DaoFactory.getInstance().getRefrigeratorIngredientDAO().add(refrigeratorIngredient3);

		RefrigeratorIngredient refrigeratorIngredient4 = new RefrigeratorIngredient(refrigerator2, ingredient3, ingredient2value2);
		DaoFactory.getInstance().getRefrigeratorIngredientDAO().add(refrigeratorIngredient4);

//		List<Refrigerator> refrigeratorsList = new ArrayList<>();
//		refrigeratorsList.add(refrigerator);

		RecipeCategory recipeCategory = new RecipeCategory("гарниры");
		DaoFactory.getInstance().getRecipeCategoryDAO().add(recipeCategory);

		RecipeCategory recipeCategory2 = new RecipeCategory("Основное блюдо");
		DaoFactory.getInstance().getRecipeCategoryDAO().add(recipeCategory2);

		Set<RecipeCategory> recipeCategories = new HashSet<>();
		recipeCategories.add(recipeCategory);

		Set<RecipeCategory> recipeCategories2 = new HashSet<>();
		recipeCategories2.add(recipeCategory2);

		CookingMethod cookingMethod = new CookingMethod("Варить");
		try {
			DaoFactory.getInstance().getCookingMethodDAO().add(cookingMethod);
		}
		catch (Exception e) {
			logger.error(e.toString());
		}

		CookingMethod cookingMethod2 = new CookingMethod("Жарить");
		try {
			DaoFactory.getInstance().getCookingMethodDAO().add(cookingMethod2);
		}
		catch (Exception e) {
			logger.error(e.toString());
		}

		CookingMethod cookingMethod3 = new CookingMethod(cookingMethod2);
		cookingMethod3.setName("парить");
		try {
			DaoFactory.getInstance().getCookingMethodDAO().add(cookingMethod3);
		}
		catch (Exception e) {
			logger.error(e.toString());
		}

		HashMap<Ingredient, Double> ingredients = new HashMap<>();
		double ingredientsrecipevalue1 = 50d;
		ingredients.put(ingredient, ingredientsrecipevalue1);
		double ingredientsrecipevalue2 = 500d;
		ingredients.put(ingredient2, ingredientsrecipevalue2);

		HashMap<Ingredient, Double> ingredients2 = new HashMap<>();
		double ingredientsrecipe2value1 = 50d;
		ingredients.put(ingredient, ingredientsrecipe2value1);
		double ingredientsrecip2evalue2 = 5000d;
		ingredients.put(ingredient3, ingredientsrecip2evalue2);

		Recipe recipe = new Recipe("name1", recipeCategories, Complexity.junior, 1000, ingredients, "1. сварить", null, cookingMethod, user);//TODO
		DaoFactory.getInstance().getRecipeDAO().add(recipe);

		Recipe recipe2 = new Recipe("name2", recipeCategories2, Complexity.middle, 1000, ingredients2, "1. сварить, 2. жарить", null, cookingMethod2, user2);//TODO
		DaoFactory.getInstance().getRecipeDAO().add(recipe2);

		RecipeIngredient recipeIngredient = new RecipeIngredient(recipe, ingredient, ingredientsrecipevalue1);
		DaoFactory.getInstance().getRecipeIngredientDAO().add(recipeIngredient);

		RecipeIngredient recipeIngredient2 = new RecipeIngredient(recipe, ingredient2, ingredientsrecipevalue2);
		DaoFactory.getInstance().getRecipeIngredientDAO().add(recipeIngredient2);

		RecipeIngredient recipeIngredient3 = new RecipeIngredient(recipe2, ingredient, ingredientsrecipe2value1);
		DaoFactory.getInstance().getRecipeIngredientDAO().add(recipeIngredient3);

		RecipeIngredient recipeIngredient4 = new RecipeIngredient(recipe2, ingredient3, ingredientsrecip2evalue2);
		DaoFactory.getInstance().getRecipeIngredientDAO().add(recipeIngredient4);



//		List<Recipe> recipersList = new ArrayList<>();
//		recipersList.add(recipe);


	}
}
