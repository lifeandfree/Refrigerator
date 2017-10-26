package ru.innopolis.refrigerator.core.db.dao.ingredient;

import ru.innopolis.refrigerator.core.db.exception.IngredientDAOException;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;

public interface SpecialIngredientDAO {

	Long getId(Ingredient ingredient) throws IngredientDAOException;
}
