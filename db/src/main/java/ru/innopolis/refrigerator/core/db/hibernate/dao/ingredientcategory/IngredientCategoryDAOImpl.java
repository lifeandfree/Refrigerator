package ru.innopolis.refrigerator.core.db.hibernate.dao.ingredientcategory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import ru.innopolis.refrigerator.core.db.dao.ingredientcategory.IngredientCategoryDAO;
import ru.innopolis.refrigerator.core.db.exception.IngredientCategoryDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;

import java.util.Set;

public class IngredientCategoryDAOImpl extends ElementDAOImpl<IngredientCategory> implements IngredientCategoryDAO<IngredientCategory> {

	private static final Logger logger = LogManager.getLogger(IngredientCategoryDAOImpl.class.getName());
	private SessionFactory sessionFactory;

	public IngredientCategoryDAOImpl() {
		super(IngredientCategory.class);
	}

	public IngredientCategoryDAOImpl(Class<IngredientCategory> content) {
		super(content);
	}


	@Override
	public Set<IngredientCategory> getAllById(Set<Long> ids) throws IngredientCategoryDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new IngredientCategoryDAOException(msg);
	}

	@Override
	public Set<Long> getAllId(Set<IngredientCategory> ingredientCategories) throws IngredientCategoryDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new IngredientCategoryDAOException(msg);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		super.setSessionFactory(sessionFactory);
	}
}