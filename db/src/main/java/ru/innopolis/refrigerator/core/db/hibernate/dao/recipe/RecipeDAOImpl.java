package ru.innopolis.refrigerator.core.db.hibernate.dao.recipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.dao.recipe.RecipeDAO;
import ru.innopolis.refrigerator.core.db.exception.RecipeDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;

import java.util.List;

public class RecipeDAOImpl extends ElementDAOImpl<Recipe> implements RecipeDAO<Recipe> {

	private static final Logger logger = LogManager.getLogger(RecipeDAOImpl.class.getName());
	public RecipeDAOImpl() {
		super(Recipe.class);
	}

	public RecipeDAOImpl(Class<Recipe> content) {
		super(content);
	}


	@Override
	public Long getId(Recipe recipe, long userId, long cookingMethodId) throws RecipeDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RecipeDAOException(msg);
	}

	@Override
	public List<Recipe> getAllByUserId(Long uid) throws RecipeDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RecipeDAOException(msg);
	}

	@Override
	public void add(Recipe recipe, long userId, long cookingMethodId) throws RecipeDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RecipeDAOException(msg);
	}
}