package ru.innopolis.refrigerator.core.db.hibernate.dao.refrigerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import ru.innopolis.refrigerator.core.db.dao.refrigeratoringredient.RefrigeratorIngredientDAO;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.refrigerator.RefrigeratorIngredient;

public class RefrigeratorIngredientDAOImpl extends ElementDAOImpl<RefrigeratorIngredient> implements RefrigeratorIngredientDAO<RefrigeratorIngredient> {

	private static final Logger logger = LogManager.getLogger(RefrigeratorIngredientDAOImpl.class.getName());
	public RefrigeratorIngredientDAOImpl() {
		super(RefrigeratorIngredient.class);
	}
	private SessionFactory sessionFactory;

	public RefrigeratorIngredientDAOImpl(Class<RefrigeratorIngredient> content) {
		super(content);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		super.setSessionFactory(sessionFactory);
	}
}
