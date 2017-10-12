package ru.innopolis.refrigerator.core.db.hibernate.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.hibernate.dao.cookingmethod.CookingMethodDAO;
import ru.innopolis.refrigerator.core.db.hibernate.dao.ingredient.IngredientDAO;
import ru.innopolis.refrigerator.core.db.hibernate.dao.ingredientcategory.IngredientCategoryDAO;
import ru.innopolis.refrigerator.core.db.hibernate.dao.recipe.RecipeDAO;
import ru.innopolis.refrigerator.core.db.hibernate.dao.recipe.RecipeIngredientDAO;
import ru.innopolis.refrigerator.core.db.hibernate.dao.recipeCategory.RecipeCategoryDAO;
import ru.innopolis.refrigerator.core.db.hibernate.dao.refrigerator.RefrigeratorDAO;
import ru.innopolis.refrigerator.core.db.hibernate.dao.refrigerator.RefrigeratorIngredientDAO;
import ru.innopolis.refrigerator.core.db.hibernate.dao.session.SessionDAO;
import ru.innopolis.refrigerator.core.db.hibernate.dao.user.UserDAO;

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
	private RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAO();

	public static synchronized DaoFactory getInstance() {
		if (instance == null) {
			instance = new DaoFactory();
		}

		return instance;
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

	public RecipeIngredientDAO getRecipeIngredientDAO() {
		return recipeIngredientDAO;
	}
}
