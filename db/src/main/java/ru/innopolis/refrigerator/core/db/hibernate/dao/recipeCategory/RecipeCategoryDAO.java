package ru.innopolis.refrigerator.core.db.hibernate.dao.recipeCategory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.recipecategory.RecipeCategory;

public class RecipeCategoryDAO extends ElementDAOImpl<RecipeCategory> {

	private static final Logger logger = LogManager.getLogger(RecipeCategoryDAO.class.getName());
	public RecipeCategoryDAO() {
		super(RecipeCategory.class);
	}

	public RecipeCategoryDAO(Class<RecipeCategory> content) {
		super(content);
	}


}