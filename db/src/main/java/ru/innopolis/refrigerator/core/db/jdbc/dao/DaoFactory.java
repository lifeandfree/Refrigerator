package ru.innopolis.refrigerator.core.db.jdbc.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.dao.cookingethod.CookingMethodDAO;
import ru.innopolis.refrigerator.core.db.jdbc.dao.ingredient.IngredientDAO;
import ru.innopolis.refrigerator.core.db.jdbc.dao.ingredient.IngredientIngredientCategoryDAO;
import ru.innopolis.refrigerator.core.db.jdbc.dao.ingredientcategory.IngredientCategoryDAO;
import ru.innopolis.refrigerator.core.db.jdbc.dao.recipe.RecipeDAO;
import ru.innopolis.refrigerator.core.db.jdbc.dao.recipecategory.RecipeCategoryDAO;
import ru.innopolis.refrigerator.core.db.jdbc.dao.refrigerator.RefrigeratorDAO;
import ru.innopolis.refrigerator.core.db.jdbc.dao.refrigerator.RefrigeratorIngredientDAO;
import ru.innopolis.refrigerator.core.db.jdbc.dao.session.SessionDAO;
import ru.innopolis.refrigerator.core.db.jdbc.dao.user.UserDAO;

public class DaoFactory {
	private static DaoFactory instance = null;
	private static final Logger logger = LogManager.getLogger(DaoFactory.class.getName());

	private UserDAO userDAO = new UserDAO();
	private CookingMethodDAO cookingMethodDAO = new CookingMethodDAO();
	private IngredientDAO ingredientDAO = new IngredientDAO();
	private IngredientCategoryDAO ingredientCategoryDAO = new IngredientCategoryDAO();
	private SessionDAO sessionDAO = new SessionDAO();
	private RecipeCategoryDAO recipeCategoryDAO = new RecipeCategoryDAO();
	private RecipeDAO recipeDAO = new RecipeDAO();
	private RefrigeratorDAO refrigeratorDAO = new RefrigeratorDAO();
	private RefrigeratorIngredientDAO refrigeratorIngredientDAO = new RefrigeratorIngredientDAO();
	private IngredientIngredientCategoryDAO ingredientIngredientCategoryDAO = new IngredientIngredientCategoryDAO();

	public static synchronized DaoFactory getInstance() {
		if (instance == null) {
			instance = new DaoFactory();
		}

		return instance;
	}

	public IngredientIngredientCategoryDAO getIngredientIngredientCategoryDAO() {
		return ingredientIngredientCategoryDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public CookingMethodDAO getCookingMethodDAO() {
		return cookingMethodDAO;
	}

	public IngredientDAO getIngredientDAO() {
		return ingredientDAO;
	}

	public IngredientCategoryDAO getIngredientCategoryDAO() {
		return ingredientCategoryDAO;
	}

	public SessionDAO getSessionDAO() {
		return sessionDAO;
	}

	public RecipeCategoryDAO getRecipeCategoryDAO() {
		return recipeCategoryDAO;
	}

	public RecipeDAO getRecipeDAO() {
		return recipeDAO;
	}

	public RefrigeratorDAO getRefrigeratorDAO() {
		return refrigeratorDAO;
	}

	public RefrigeratorIngredientDAO getRefrigeratorIngredientDAO() {
		return refrigeratorIngredientDAO;
	}
}
