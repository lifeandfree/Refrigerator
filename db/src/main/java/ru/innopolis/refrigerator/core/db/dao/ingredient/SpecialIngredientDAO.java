package ru.innopolis.refrigerator.core.db.dao.ingredient;

import ru.innopolis.refrigerator.core.db.exception.IngredientDAOException;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;

import java.util.List;

public interface SpecialIngredientDAO {

	Long getId(Ingredient ingredient) throws IngredientDAOException;

	Ingredient getIngredientByNameDimension(String name, String dimension) throws IngredientDAOException;
}
