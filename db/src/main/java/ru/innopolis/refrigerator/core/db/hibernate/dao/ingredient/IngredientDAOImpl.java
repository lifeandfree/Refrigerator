package ru.innopolis.refrigerator.core.db.hibernate.dao.ingredient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.dao.ingredient.IngredientDAO;
import ru.innopolis.refrigerator.core.db.exception.IngredientDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;

public class IngredientDAOImpl extends ElementDAOImpl<Ingredient>  implements IngredientDAO<Ingredient> {

	private static final Logger logger = LogManager.getLogger(IngredientDAOImpl.class.getName());
	public IngredientDAOImpl() {
		super(Ingredient.class);
	}

	public IngredientDAOImpl(Class<Ingredient> content) {
		super(content);
	}


	@Override
	public Long getId(Ingredient ingredient) throws IngredientDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new IngredientDAOException(msg);
	}
}