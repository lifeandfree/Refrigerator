package ru.innopolis.refrigerator.core.db.hibernate.dao.refrigerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.refrigerator.RefrigeratorIngredient;

public class RefrigeratorIngredientDAO extends ElementDAOImpl<RefrigeratorIngredient> {

	private static final Logger logger = LogManager.getLogger(RefrigeratorIngredientDAO.class.getName());
	public RefrigeratorIngredientDAO() {
		super(RefrigeratorIngredient.class);
	}

	public RefrigeratorIngredientDAO(Class<RefrigeratorIngredient> content) {
		super(content);
	}
}
