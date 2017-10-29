package ru.innopolis.refrigerator.core.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.innopolis.refrigerator.core.db.dao.cookingmethod.CookingMethodDAO;
import ru.innopolis.refrigerator.core.db.dao.ingredient.IngredientDAO;
import ru.innopolis.refrigerator.core.db.dao.ingredientcategory.IngredientCategoryDAO;
import ru.innopolis.refrigerator.core.db.dao.recipe.RecipeDAO;
import ru.innopolis.refrigerator.core.db.dao.recipecategory.RecipeCategoryDAO;
import ru.innopolis.refrigerator.core.db.dao.refrigerator.RefrigeratorDAO;
import ru.innopolis.refrigerator.core.db.dao.refrigeratoringredient.RefrigeratorIngredientDAO;
import ru.innopolis.refrigerator.core.db.dao.session.SessionDAO;
import ru.innopolis.refrigerator.core.db.dao.user.UserDAO;
import ru.innopolis.refrigerator.core.db.jdbc.dao.ingredient.IngredientIngredientCategoryDAO;

//@Component(value = "DaoFactory")
public class DaoFactory {
	private static DaoFactory instance = null;
	private static final Logger logger = LogManager.getLogger(DaoFactory.class.getName());

	private DaoFactory() {
	}

	public DaoFactory(UserDAO userDAO, CookingMethodDAO cookingMethodDAO, IngredientDAO ingredientDAO, SessionDAO sessionDAO, RecipeCategoryDAO recipeCategoryDAO, RecipeDAO recipeDAO, RefrigeratorDAO refrigeratorDAO, RefrigeratorIngredientDAO refrigeratorIngredientDAO, IngredientCategoryDAO ingredientCategoryDAO, IngredientIngredientCategoryDAO ingredientIngredientCategoryDAO) {
		this.userDAO = userDAO;
		this.cookingMethodDAO = cookingMethodDAO;
		this.ingredientDAO = ingredientDAO;
		this.sessionDAO = sessionDAO;
		this.recipeCategoryDAO = recipeCategoryDAO;
		this.recipeDAO = recipeDAO;
		this.refrigeratorDAO = refrigeratorDAO;
		this.refrigeratorIngredientDAO = refrigeratorIngredientDAO;
		this.ingredientCategoryDAO = ingredientCategoryDAO;
		this.ingredientIngredientCategoryDAO = ingredientIngredientCategoryDAO;
	}

//	@Autowired
//	@Qualifier(value = "userdao")
	private UserDAO userDAO;

//	@Autowired
//	@Qualifier(value = "cookingethoddao")
	private CookingMethodDAO cookingMethodDAO;

//	@Autowired
//	@Qualifier(value = "ingredientdao")
	private IngredientDAO ingredientDAO;

//	@Autowired
//	@Qualifier(value = "sessiondao")
	private SessionDAO sessionDAO;

//	@Autowired
//	@Qualifier(value = "recipecategorydao")
	private RecipeCategoryDAO recipeCategoryDAO;

//	@Autowired
//	@Qualifier(value = "recipedao")
	private RecipeDAO recipeDAO;

//	@Autowired
//	@Qualifier(value = "refrigeratordao")
	private RefrigeratorDAO refrigeratorDAO;

//	@Autowired
//	@Qualifier(value = "refrigeratoringredientdao")
	private RefrigeratorIngredientDAO refrigeratorIngredientDAO;

//	@Autowired
//	@Qualifier(value = "ingredientcategorydao")
	private IngredientCategoryDAO ingredientCategoryDAO;

//	@Autowired
//	@Qualifier(value = "ingredientingredientcategorydao")
	private IngredientIngredientCategoryDAO ingredientIngredientCategoryDAO;

	public static synchronized DaoFactory getInstance() {
		if (instance == null) {
			ApplicationContext applicationContext =
					new ClassPathXmlApplicationContext("bean.xml");
			instance =(DaoFactory) applicationContext.getBean("DaoFactory");
//			instance = new DaoFactory();
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

	public IngredientCategoryDAO getIngredientCategoryDAO() {
		return ingredientCategoryDAO;
	}

	public IngredientIngredientCategoryDAO getIngredientIngredientCategoryDAO() {
		return ingredientIngredientCategoryDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setCookingMethodDAO(CookingMethodDAO cookingMethodDAO) {
		this.cookingMethodDAO = cookingMethodDAO;
	}

	public void setIngredientDAO(IngredientDAO ingredientDAO) {
		this.ingredientDAO = ingredientDAO;
	}

	public void setSessionDAO(SessionDAO sessionDAO) {
		this.sessionDAO = sessionDAO;
	}

	public void setRecipeCategoryDAO(RecipeCategoryDAO recipeCategoryDAO) {
		this.recipeCategoryDAO = recipeCategoryDAO;
	}

	public void setRecipeDAO(RecipeDAO recipeDAO) {
		this.recipeDAO = recipeDAO;
	}

	public void setRefrigeratorDAO(RefrigeratorDAO refrigeratorDAO) {
		this.refrigeratorDAO = refrigeratorDAO;
	}

	public void setRefrigeratorIngredientDAO(RefrigeratorIngredientDAO refrigeratorIngredientDAO) {
		this.refrigeratorIngredientDAO = refrigeratorIngredientDAO;
	}

	public void setIngredientCategoryDAO(IngredientCategoryDAO ingredientCategoryDAO) {
		this.ingredientCategoryDAO = ingredientCategoryDAO;
	}

	public void setIngredientIngredientCategoryDAO(IngredientIngredientCategoryDAO ingredientIngredientCategoryDAO) {
		this.ingredientIngredientCategoryDAO = ingredientIngredientCategoryDAO;
	}
}
