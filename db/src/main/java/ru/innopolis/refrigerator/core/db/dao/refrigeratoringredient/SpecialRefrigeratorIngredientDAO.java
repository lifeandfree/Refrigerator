package ru.innopolis.refrigerator.core.db.dao.refrigeratoringredient;

import ru.innopolis.refrigerator.core.db.exception.RefrigeratorIngredientDAOException;
import ru.innopolis.refrigerator.core.model.refrigerator.Refrigerator;
import ru.innopolis.refrigerator.core.model.refrigerator.RefrigeratorIngredient;

import java.util.List;

public interface SpecialRefrigeratorIngredientDAO {
	List<RefrigeratorIngredient> getRefIngrByRef(Refrigerator refrigerator) throws RefrigeratorIngredientDAOException;
}
