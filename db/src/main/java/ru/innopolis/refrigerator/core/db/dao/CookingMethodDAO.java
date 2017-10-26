package ru.innopolis.refrigerator.core.db.dao;

import ru.innopolis.refrigerator.core.db.exception.CookingMethodDAOException;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;

public interface CookingMethodDAO {

	public long getId(CookingMethod cookingMethod) throws CookingMethodDAOException;

}
