package ru.innopolis.refrigerator.core.db.hibernate.dao.ingredient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.Ingredient;

public class IngredientDAO extends ElementDAOImpl<Ingredient> {

	private static final Logger logger = LogManager.getLogger(IngredientDAO.class.getName());
	public IngredientDAO() {
		super(Ingredient.class);
	}

	public IngredientDAO(Class<Ingredient> content) {
		super(content);
	}


}