package ru.innopolis.refrigerator.core.db.jdbc.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.dao.cookingmethod.CookingMethodDAO;
import ru.innopolis.refrigerator.core.db.dao.ingredient.IngredientDAO;
import ru.innopolis.refrigerator.core.db.dao.ingredientcategory.IngredientCategoryDAO;
import ru.innopolis.refrigerator.core.db.dao.recipe.RecipeDAO;
import ru.innopolis.refrigerator.core.db.dao.recipecategory.RecipeCategoryDAO;
import ru.innopolis.refrigerator.core.db.dao.refrigerator.RefrigeratorDAO;
import ru.innopolis.refrigerator.core.db.dao.refrigeratoringredient.RefrigeratorIngredientDAO;
import ru.innopolis.refrigerator.core.db.dao.session.SessionDAO;
import ru.innopolis.refrigerator.core.db.dao.user.UserDAO;
import ru.innopolis.refrigerator.core.db.jdbc.dao.cookingethod.CookingMethodDAOJDBCImpl;
import ru.innopolis.refrigerator.core.db.jdbc.dao.ingredient.IngredientDAOJDBCImpl;
import ru.innopolis.refrigerator.core.db.jdbc.dao.ingredient.IngredientIngredientCategoryDAO;
import ru.innopolis.refrigerator.core.db.jdbc.dao.ingredientcategory.IngredientCategoryDAOJDBCImpl;
import ru.innopolis.refrigerator.core.db.jdbc.dao.recipe.RecipeDAOJDBCImpl;
import ru.innopolis.refrigerator.core.db.jdbc.dao.recipecategory.RecipeCategoryDAOJDBCImpl;
import ru.innopolis.refrigerator.core.db.jdbc.dao.refrigerator.RefrigeratorDAOJDBCImpl;
import ru.innopolis.refrigerator.core.db.jdbc.dao.refrigerator.RefrigeratorIngredientDAOJDBCImpl;
import ru.innopolis.refrigerator.core.db.jdbc.dao.session.SessionDAOJDBCImpl;
import ru.innopolis.refrigerator.core.db.jdbc.dao.user.UserDAOJDBCImpl;

public class DaoFactory {
	private static DaoFactory instance = null;
	private static final Logger logger = LogManager.getLogger(DaoFactory.class.getName());

	private UserDAO userDAO = new UserDAOJDBCImpl();
	private CookingMethodDAO cookingMethodDAO = new CookingMethodDAOJDBCImpl();
	private IngredientDAO ingredientDAO = new IngredientDAOJDBCImpl();
	private SessionDAO sessionDAO = new SessionDAOJDBCImpl();
	private RecipeCategoryDAO recipeCategoryDAOJDBCImpl = new RecipeCategoryDAOJDBCImpl();
	private RecipeDAO recipeDAOJDBCImpl = new RecipeDAOJDBCImpl();
	private RefrigeratorDAO refrigeratorDAOJDBCImpl = new RefrigeratorDAOJDBCImpl();
	private RefrigeratorIngredientDAO refrigeratorIngredientDAOJDBCImpl = new RefrigeratorIngredientDAOJDBCImpl();
	private IngredientCategoryDAO ingredientCategoryDAO = new IngredientCategoryDAOJDBCImpl();
	private IngredientIngredientCategoryDAO ingredientIngredientCategoryDAO = new IngredientIngredientCategoryDAO();

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

	public SessionDAO getSessionDAO() {
		return sessionDAO;
	}

	public RecipeCategoryDAO getRecipeCategoryDAOJDBCImpl() {
		return recipeCategoryDAOJDBCImpl;
	}

	public RecipeDAO getRecipeDAOJDBCImpl() {
		return recipeDAOJDBCImpl;
	}

	public RefrigeratorDAO getRefrigeratorDAOJDBCImpl() {
		return refrigeratorDAOJDBCImpl;
	}

	public RefrigeratorIngredientDAO getRefrigeratorIngredientDAOJDBCImpl() {
		return refrigeratorIngredientDAOJDBCImpl;
	}

	public IngredientCategoryDAO getIngredientCategoryDAO() {
		return ingredientCategoryDAO;
	}

	public IngredientIngredientCategoryDAO getIngredientIngredientCategoryDAO() {
		return ingredientIngredientCategoryDAO;
	}
}
