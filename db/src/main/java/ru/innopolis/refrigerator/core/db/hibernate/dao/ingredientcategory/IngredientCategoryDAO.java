package ru.innopolis.refrigerator.core.db.hibernate.dao.ingredientcategory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;

public class IngredientCategoryDAO extends ElementDAOImpl<IngredientCategory> {

	private static final Logger logger = LogManager.getLogger(IngredientCategoryDAO.class.getName());
	public IngredientCategoryDAO() {
		super(IngredientCategory.class);
	}

	public IngredientCategoryDAO(Class<IngredientCategory> content) {
		super(content);
	}


}