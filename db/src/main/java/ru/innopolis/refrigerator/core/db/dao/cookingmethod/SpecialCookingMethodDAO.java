package ru.innopolis.refrigerator.core.db.dao.cookingmethod;

import ru.innopolis.refrigerator.core.db.exception.CookingMethodDAOException;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;

public interface SpecialCookingMethodDAO {

	long getId(CookingMethod cookingMethod) throws CookingMethodDAOException;

	CookingMethod getByName(String name) throws CookingMethodDAOException;
}
