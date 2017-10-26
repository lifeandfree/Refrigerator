package ru.innopolis.refrigerator.core.db.dao.recipecategory;

import ru.innopolis.refrigerator.core.db.exception.RecipeCategoryDAOException;
import ru.innopolis.refrigerator.core.model.recipecategory.RecipeCategory;

import java.util.Set;

public interface SpecialRecipeCategoryDAO {

	Set<Long> getIds(Set<RecipeCategory> recipeCategorys) throws RecipeCategoryDAOException;

}
