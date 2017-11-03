package ru.innopolis.refrigerator.core.db.hibernate.dao.recipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import ru.innopolis.refrigerator.core.db.dao.recipeingredient.RecipeIngredientDAO;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.recipe.RecipeIngredient;

public class RecipeIngredientDAOImpl extends ElementDAOImpl<RecipeIngredient> implements RecipeIngredientDAO<RecipeIngredient> {

	private static final Logger logger = LogManager.getLogger(RecipeIngredientDAOImpl.class.getName());
	private SessionFactory sessionFactory;

	public RecipeIngredientDAOImpl() {
		super(RecipeIngredient.class);
	}

	public RecipeIngredientDAOImpl(Class<RecipeIngredient> content) {
		super(content);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		super.setSessionFactory(sessionFactory);
	}
}
