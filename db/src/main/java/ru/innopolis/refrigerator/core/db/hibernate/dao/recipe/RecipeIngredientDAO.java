package ru.innopolis.refrigerator.core.db.hibernate.dao.recipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.recipe.RecipeIngredient;

public class RecipeIngredientDAO extends ElementDAOImpl<RecipeIngredient> {

	private static final Logger logger = LogManager.getLogger(RecipeIngredientDAO.class.getName());
	public RecipeIngredientDAO() {
		super(RecipeIngredient.class);
	}

	public RecipeIngredientDAO(Class<RecipeIngredient> content) {
		super(content);
	}

}
