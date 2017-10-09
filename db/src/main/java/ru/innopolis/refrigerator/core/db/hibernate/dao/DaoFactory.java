package ru.innopolis.refrigerator.core.db.hibernate.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.hibernate.dao.cookingmethod.CookingMethodDAO;
import ru.innopolis.refrigerator.core.db.hibernate.dao.ingredient.IngredientDAO;
import ru.innopolis.refrigerator.core.db.hibernate.dao.ingredientcategory.IngredientCategoryDAO;
import ru.innopolis.refrigerator.core.db.hibernate.dao.recipe.RecipeDAO;
import ru.innopolis.refrigerator.core.db.hibernate.dao.recipeCategory.RecipeCategoryDAO;
import ru.innopolis.refrigerator.core.db.hibernate.dao.refrigerator.RefrigeratorDAO;
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

	public static synchronized DaoFactory getInstance() {
		if (instance == null) {
			instance = new DaoFactory();
		}

		return instance;
	}


	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public CookingMethodDAO getCookingMethodDAO() {
		return cookingMethodDAO;
	}

	public void setCookingMethodDAO(CookingMethodDAO cookingMethodDAO) {
		this.cookingMethodDAO = cookingMethodDAO;
	}

	public IngredientDAO getIngredientDAO() {
		return ingredientDAO;
	}

	public void setIngredientDAO(IngredientDAO ingredientDAO) {
		this.ingredientDAO = ingredientDAO;
	}

	public IngredientCategoryDAO getIngredientCategoryDAO() {
		return ingredientCategoryDAO;
	}

	public void setIngredientCategoryDAO(IngredientCategoryDAO ingredientCategoryDAO) {
		this.ingredientCategoryDAO = ingredientCategoryDAO;
	}

	public SessionDAO getSessionDAO() {
		return sessionDAO;
	}

	public void setSessionDAO(SessionDAO sessionDAO) {
		this.sessionDAO = sessionDAO;
	}

	public RecipeCategoryDAO getRecipeCategoryDAO() {
		return recipeCategoryDAO;
	}

	public void setRecipeCategoryDAO(RecipeCategoryDAO recipeCategoryDAO) {
		this.recipeCategoryDAO = recipeCategoryDAO;
	}

	public RecipeDAO getRecipeDAO() {
		return recipeDAO;
	}

	public void setRecipeDAO(RecipeDAO recipeDAO) {
		this.recipeDAO = recipeDAO;
	}

	public RefrigeratorDAO getRefrigeratorDAO() {
		return refrigeratorDAO;
	}

	public void setRefrigeratorDAO(RefrigeratorDAO refrigeratorDAO) {
		this.refrigeratorDAO = refrigeratorDAO;
	}
}
