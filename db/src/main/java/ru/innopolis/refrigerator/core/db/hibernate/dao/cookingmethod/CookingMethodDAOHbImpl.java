package ru.innopolis.refrigerator.core.db.hibernate.dao.cookingmethod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.dao.CookingMethodDAO;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.db.exception.CookingMethodDAOException;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;

public class CookingMethodDAOHbImpl extends ElementDAOImpl<CookingMethod> implements CookingMethodDAO{

	private static final Logger logger = LogManager.getLogger(CookingMethodDAOHbImpl.class.getName());
	public CookingMethodDAOHbImpl() {
		super(CookingMethod.class);
	}

	public CookingMethodDAOHbImpl(Class<CookingMethod> content) {
		super(content);
	}


	@Override
	public long getId(CookingMethod cookingMethod) throws CookingMethodDAOException {
		return 0;
	}
}