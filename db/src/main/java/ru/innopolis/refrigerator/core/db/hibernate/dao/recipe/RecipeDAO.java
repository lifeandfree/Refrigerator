package ru.innopolis.refrigerator.core.db.hibernate.dao.recipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;

public class RecipeDAO extends ElementDAOImpl<Recipe> {

	private static final Logger logger = LogManager.getLogger(RecipeDAO.class.getName());
	public RecipeDAO() {
		super(Recipe.class);
	}

	public RecipeDAO(Class<Recipe> content) {
		super(content);
	}


}