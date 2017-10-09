package ru.innopolis.refrigerator.core.db.hibernate.dao.cookingmethod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.CookingMethod;

public class CookingMethodDAO extends ElementDAOImpl<CookingMethod> {

	private static final Logger logger = LogManager.getLogger(CookingMethodDAO.class.getName());
	public CookingMethodDAO() {
		super(CookingMethod.class);
	}

	public CookingMethodDAO(Class<CookingMethod> content) {
		super(content);
	}


}