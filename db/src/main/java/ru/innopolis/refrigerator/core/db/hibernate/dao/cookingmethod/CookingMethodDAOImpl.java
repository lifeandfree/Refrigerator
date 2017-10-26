package ru.innopolis.refrigerator.core.db.hibernate.dao.cookingmethod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.dao.cookingmethod.CookingMethodDAO;
import ru.innopolis.refrigerator.core.db.exception.CookingMethodDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;

public class CookingMethodDAOImpl extends ElementDAOImpl<CookingMethod> implements CookingMethodDAO<CookingMethod>{

	private static final Logger logger = LogManager.getLogger(CookingMethodDAOImpl.class.getName());
	public CookingMethodDAOImpl() {
		super(CookingMethod.class);
	}

	public CookingMethodDAOImpl(Class<CookingMethod> content) {
		super(content);
	}

	@Override
	public long getId(CookingMethod cookingMethod) throws CookingMethodDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new CookingMethodDAOException(msg);
	}
}