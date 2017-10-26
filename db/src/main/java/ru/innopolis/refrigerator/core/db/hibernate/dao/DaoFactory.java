package ru.innopolis.refrigerator.core.db.hibernate.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.dao.cookingmethod.CookingMethodDAO;
import ru.innopolis.refrigerator.core.db.dao.ingredient.IngredientDAO;
import ru.innopolis.refrigerator.core.db.dao.ingredientcategory.IngredientCategoryDAO;
import ru.innopolis.refrigerator.core.db.dao.recipe.RecipeDAO;
import ru.innopolis.refrigerator.core.db.dao.recipecategory.RecipeCategoryDAO;
import ru.innopolis.refrigerator.core.db.dao.recipeingredient.RecipeIngredientDAO;
import ru.innopolis.refrigerator.core.db.dao.refrigerator.RefrigeratorDAO;
import ru.innopolis.refrigerator.core.db.dao.refrigeratoringredient.RefrigeratorIngredientDAO;
import ru.innopolis.refrigerator.core.db.dao.session.SessionDAO;
import ru.innopolis.refrigerator.core.db.dao.user.UserDAO;
import ru.innopolis.refrigerator.core.db.hibernate.dao.cookingmethod.CookingMethodDAOImpl;
import ru.innopolis.refrigerator.core.db.hibernate.dao.ingredient.IngredientDAOImpl;
import ru.innopolis.refrigerator.core.db.hibernate.dao.ingredientcategory.IngredientCategoryDAOImpl;
import ru.innopolis.refrigerator.core.db.hibernate.dao.recipe.RecipeDAOImpl;
import ru.innopolis.refrigerator.core.db.hibernate.dao.recipe.RecipeIngredientDAOImpl;
import ru.innopolis.refrigerator.core.db.hibernate.dao.recipeCategory.RecipeCategoryDAOImpl;
import ru.innopolis.refrigerator.core.db.hibernate.dao.refrigerator.RefrigeratorDAOImpl;
import ru.innopolis.refrigerator.core.db.hibernate.dao.refrigerator.RefrigeratorIngredientDAOImpl;
import ru.innopolis.refrigerator.core.db.hibernate.dao.session.SessionDAOImpl;
import ru.innopolis.refrigerator.core.db.hibernate.dao.user.UserDAOImpl;

public class DaoFactory {
	private static DaoFactory instance = null;
	private static final Logger logger = LogManager.getLogger(DaoFactory.class.getName());

	private UserDAO userDAO = new UserDAOImpl();
	private CookingMethodDAO cookingMethodDAO = new CookingMethodDAOImpl();
	private IngredientDAO ingredientDAO = new IngredientDAOImpl();
	private IngredientCategoryDAO ingredientCategoryDAO = new IngredientCategoryDAOImpl();
	private SessionDAO sessionDAO = new SessionDAOImpl();
	private RecipeCategoryDAO recipeCategoryDAO = new RecipeCategoryDAOImpl();
	private RecipeDAO recipeDAO = new RecipeDAOImpl();
	private RefrigeratorDAO refrigeratorDAO = new RefrigeratorDAOImpl();
	private RefrigeratorIngredientDAO refrigeratorIngredientDAO = new RefrigeratorIngredientDAOImpl();
	private RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAOImpl();

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
