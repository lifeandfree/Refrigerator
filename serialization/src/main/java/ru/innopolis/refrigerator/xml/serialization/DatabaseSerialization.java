package ru.innopolis.refrigerator.xml.serialization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.Constants;
import ru.innopolis.refrigerator.core.db.DaoFactory;
import ru.innopolis.refrigerator.core.db.exception.*;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.core.model.recipecategory.RecipeCategory;
import ru.innopolis.refrigerator.core.model.refrigerator.Refrigerator;
import ru.innopolis.refrigerator.core.model.session.Session;
import ru.innopolis.refrigerator.core.model.user.User;
import ru.innopolis.refrigerator.xml.serialization.cookingmethod.CookingMethods;
import ru.innopolis.refrigerator.xml.serialization.ingredient.Ingredients;
import ru.innopolis.refrigerator.xml.serialization.ingredientcategory.IngredientCategories;
import ru.innopolis.refrigerator.xml.serialization.recipe.Recipes;
import ru.innopolis.refrigerator.xml.serialization.recipecategory.RecipeCategories;
import ru.innopolis.refrigerator.xml.serialization.refrigerator.Refrigerators;
import ru.innopolis.refrigerator.xml.serialization.session.Sessions;
import ru.innopolis.refrigerator.xml.serialization.user.Users;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

public class DatabaseSerialization {

	public static void main(String[] args) throws Exception {
		createXmlSerealization();
	}

	private static final Logger logger = LogManager.getLogger(DatabaseSerialization.class.getName());

	public static void restoreFromXmlToDB() throws Exception {

		ObjectJaxbParser parser = new ObjectJaxbParser();

		Users users = new Users();
		try {
			users = (Users) parser.getObject(new File(Constants.USERS_XML_FILENAME), Users.class);
		}
		catch (JAXBException e) {
			logger.error("I can not get of all Users from the xml" + e.toString());
		}
		try {
			DaoFactory.getInstance().getUserDAO().addAll(users.getUsers());
		}
		catch (UserDAOException e) {
			logger.error("I can not set of all Users to the database" + e.toString());
		}

		CookingMethods cookingMethods = new CookingMethods();
		try {
			cookingMethods = (CookingMethods) parser.getObject(new File(Constants.COOKINGMETHODS_XML_FILENAME), CookingMethods.class);
		}
		catch (JAXBException e) {
			logger.error("I can not get of all CookingMethods from the xml" + e.toString());
		}
		try {
			DaoFactory.getInstance().getCookingMethodDAO().addAll(cookingMethods.getCookingMethods());
		}
		catch (CookingMethodDAOException e) {
			logger.error("I can not set of all CookingMethods to the database" + e.toString());
		}
		catch (Exception e) {
			logger.error(e.toString());
		}

		IngredientCategories ingredientCategories = new IngredientCategories();
		try {
			ingredientCategories = (IngredientCategories) parser.getObject(new File(Constants.INGRREDIENTCATEGORIES_XML_FILENAME), IngredientCategories.class);
		}
		catch (JAXBException e) {
			logger.error("I can not get of all IngredientCategories from the xml" + e.toString());
		}
		try {
			DaoFactory.getInstance().getIngredientCategoryDAO().addAll(ingredientCategories.getIngredientCategories());
		}
		catch (IngredientCategoryDAOException e) {
			logger.error("I can not set of all IngredientCategories to the database" + e.toString());
		}
		catch (Exception e) {
			logger.error(e.toString());
		}

		RecipeCategories recipeCategories = new RecipeCategories();
		try {
			recipeCategories =  (RecipeCategories) parser.getObject(new File(Constants.RECIPECATEGORIES_XML_FILENAME), RecipeCategories.class);
		}
		catch (JAXBException e) {
			logger.error("I can not get of all RecipeCategories from the xml" + e.toString());
		}
		try {
			DaoFactory.getInstance().getRecipeCategoryDAOJDBCImpl().addAll(recipeCategories.getRecipeCategories());
		}
		catch (RecipeCategoryDAOException e) {
			logger.error("I can not set of all RecipeCategories to the database" + e.toString());
		}

		Sessions sessions = new Sessions();
		try {
			sessions = (Sessions) parser.getObject(new File(Constants.SESSIONS_XML_FILENAME), Sessions.class);
		}
		catch (JAXBException e) {
			logger.error("I can not get of all Sessions from the xml" + e.toString());
		}
		try {
			DaoFactory.getInstance().getSessionDAO().addAll(sessions.getSessions());
		}
		catch (SessionDAOException e) {
			logger.error("I can not set of all Sessions to the database" + e.toString());
		}

		Ingredients ingredients = new Ingredients();
		try {
			ingredients = (Ingredients) parser.getObject(new File(Constants.INGREDIENTS_XML_FILENAME), Ingredients.class);
		}
		catch (JAXBException e) {
			logger.error("I can not get of all Sessions from the xml" + e.toString());
		}
		try {
			DaoFactory.getInstance().getIngredientDAO().addAll(ingredients.getIngredients());
		}
		catch (IngredientDAOException e) {
			logger.error("I can not set of all ingredients to the database" + e.toString());
		}

		Refrigerators refrigerators = new Refrigerators();
		try {
			refrigerators =  (Refrigerators) parser.getObject(new File(Constants.REFRIGERATORS_XML_FILENAME), Refrigerators.class);
		}
		catch (JAXBException e) {
			logger.error("I can not get of all Sessions from the xml" + e.toString());
		}
		try {
			DaoFactory.getInstance().getRefrigeratorDAOJDBCImpl().addAll(refrigerators.getRefrigerators());
		}
		catch (RefrigeratorDAOException e) {
			logger.error("I can not set of all refrigerators to the database" + e.toString());
		}

		Recipes recipes = new Recipes();
		try {
			recipes =  (Recipes) parser.getObject(new File(Constants.RECIPES_XML_FILENAME), Recipes.class);
		}
		catch (JAXBException e) {
			logger.error("I can not get of all Sessions from the xml" + e.toString());
		}
		try {
			DaoFactory.getInstance().getRecipeDAOJDBCImpl().addAll(recipes.getRecipes());
		}
		catch (RecipeDAOException e) {
			logger.error("I can not set of all Recipes to the database" + e.toString());
		}
	}

	public static void createXmlSerealization() throws Exception {

		ObjectJaxbParser parser = new ObjectJaxbParser();

		Refrigerators refrigerators = new Refrigerators();
		try {
			refrigerators.setRefrigerators((List<Refrigerator>) DaoFactory.getInstance().getRefrigeratorDAOJDBCImpl().getAll());
		}
		catch (RefrigeratorDAOException e) {
			logger.error("I can not get of all Refrigerators to the database" + e.toString());
		}
		try {
			parser.saveObject(new File(Constants.REFRIGERATORS_XML_FILENAME), refrigerators);
		}
		catch (JAXBException e) {
			logger.error("I can not set of all items to the xml" + e.toString());
		}


		Recipes recipes = new Recipes();
		try {
			recipes.setRecipes((List<Recipe>) DaoFactory.getInstance().getRecipeDAOJDBCImpl().getAll());
		}
		catch (RecipeDAOException e) {
			logger.error("I can not get of all Recipe to the database" + e.toString());
		}
		try {
			parser.saveObject(new File(Constants.RECIPES_XML_FILENAME), recipes);
		}
		catch (JAXBException e) {
			logger.error("I can not set of all items to the xml" + e.toString());
		}

		CookingMethods cookingMethods = new CookingMethods();
		try {
			cookingMethods.setCookingMethods((List<CookingMethod>) DaoFactory.getInstance().getCookingMethodDAO().getAll());
		}
		catch (Exception e) {
			logger.error(e.toString());
		}
		try {
			parser.saveObject(new File(Constants.COOKINGMETHODS_XML_FILENAME), cookingMethods);
		}
		catch (JAXBException e) {
			logger.error("I can not set of all items to the xml" + e.toString());
		}

		Ingredients ingredients = new Ingredients();
		try {
			ingredients.setIngredients((List<Ingredient>) DaoFactory.getInstance().getIngredientDAO().getAll());
		}
		catch (IngredientDAOException e) {
			logger.error("I can not get of all Ingredient to the database" + e.toString());
		}
		try {
			parser.saveObject(new File(Constants.INGREDIENTS_XML_FILENAME), ingredients);
		}
		catch (JAXBException e) {
			logger.error("I can not set of all items to the xml" + e.toString());
		}

		IngredientCategories ingredientCategories = new IngredientCategories();
		try {
			ingredientCategories.setIngredientCategories((List<IngredientCategory>) DaoFactory.getInstance().getIngredientCategoryDAO().getAll());
		}
		catch (IngredientCategoryDAOException e) {
			logger.error("I can not get of all IngredientCategoryD to the database" + e.toString());
		}
		catch (Exception e) {
			logger.error(e.toString());
		}
		try {
			parser.saveObject(new File(Constants.INGRREDIENTCATEGORIES_XML_FILENAME), ingredientCategories);
		}
		catch (JAXBException e) {
			logger.error("I can not set of all items to the xml" + e.toString());
		}

		RecipeCategories recipeCategories = new RecipeCategories();
		try {
			recipeCategories.setRecipeCategories((List<RecipeCategory>) DaoFactory.getInstance().getRecipeCategoryDAOJDBCImpl().getAll());
		}
		catch (RecipeCategoryDAOException e) {
			logger.error("I can not get of all RecipeCategory to the database" + e.toString());
		}
		try {
			parser.saveObject(new File(Constants.RECIPECATEGORIES_XML_FILENAME), recipeCategories);
		}
		catch (JAXBException e) {
			logger.error("I can not set of all items to the xml" + e.toString());
		}

		Sessions sessions = new Sessions();
		try {
			sessions.setSessions((List<Session>) DaoFactory.getInstance().getSessionDAO().getAll());
		}
		catch (SessionDAOException e) {
			logger.error("I can not get of all Session to the database" + e.toString());
		}
		try {
			parser.saveObject(new File(Constants.SESSIONS_XML_FILENAME), sessions);
		}
		catch (JAXBException e) {
			logger.error("I can not set of all items to the xml" + e.toString());

		}

		Users users = new Users();
		try {
			users.setUsers((List<User>) DaoFactory.getInstance().getUserDAO().getAll());
		}
		catch (UserDAOException e) {
			logger.error("I can not get of all User to the database" + e.toString());
		}
		try {
			parser.saveObject(new File(Constants.USERS_XML_FILENAME), users);
		}
		catch (JAXBException e) {
			logger.error("I can not set of all items to the xml" + e.toString());
		}
	}
}
