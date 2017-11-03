package ru.innopolis.refrigerator.core.db.hibernate.dao.recipeCategory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import ru.innopolis.refrigerator.core.db.dao.recipecategory.RecipeCategoryDAO;
import ru.innopolis.refrigerator.core.db.exception.RecipeCategoryDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.recipecategory.RecipeCategory;

import java.util.Set;

public class RecipeCategoryDAOImpl extends ElementDAOImpl<RecipeCategory> implements RecipeCategoryDAO<RecipeCategory> {

	private static final Logger logger = LogManager.getLogger(RecipeCategoryDAOImpl.class.getName());
	private SessionFactory sessionFactory;

	public RecipeCategoryDAOImpl() {
		super(RecipeCategory.class);
	}

	public RecipeCategoryDAOImpl(Class<RecipeCategory> content) {
		super(content);
	}


	@Override
	public Set<Long> getIds(Set<RecipeCategory> recipeCategorys) throws RecipeCategoryDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RecipeCategoryDAOException(msg);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		super.setSessionFactory(sessionFactory);
	}
}