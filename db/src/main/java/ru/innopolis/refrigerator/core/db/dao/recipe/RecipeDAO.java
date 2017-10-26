package ru.innopolis.refrigerator.core.db.dao.recipe;

import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAO;

public interface RecipeDAO<E> extends ElementDAO<E>, SpecialRecipeDAO {
}
