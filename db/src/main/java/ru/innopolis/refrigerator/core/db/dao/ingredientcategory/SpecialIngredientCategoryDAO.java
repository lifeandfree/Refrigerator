package ru.innopolis.refrigerator.core.db.dao.ingredientcategory;

import ru.innopolis.refrigerator.core.db.exception.IngredientCategoryDAOException;
import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;

import java.util.Set;

public interface SpecialIngredientCategoryDAO {

	Set<IngredientCategory> getAllById(Set<Long> ids) throws IngredientCategoryDAOException;

	Set<Long> getAllId(Set<IngredientCategory> ingredientCategories) throws IngredientCategoryDAOException;
}
