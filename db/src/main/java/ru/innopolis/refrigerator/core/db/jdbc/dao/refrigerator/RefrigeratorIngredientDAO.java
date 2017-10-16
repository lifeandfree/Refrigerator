package ru.innopolis.refrigerator.core.db.jdbc.dao.refrigerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.dao.DaoFactory;
import ru.innopolis.refrigerator.core.db.jdbc.dao.ingredient.IngredientDAO;
import ru.innopolis.refrigerator.core.db.jdbc.dao.ingredient.IngredientIngredientCategoryDAO;
import ru.innopolis.refrigerator.core.db.jdbc.exception.IngredientDAOException;
import ru.innopolis.refrigerator.core.db.jdbc.exception.RefrigeratorIngredientDAOException;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RefrigeratorIngredientDAO extends RefrigeratorIngredientDAOException{

	private static final Logger logger = LogManager.getLogger(IngredientIngredientCategoryDAO.class.getName());
	private static ConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public Map<Ingredient, Double> getAllByRefrigeratorId(Long id) throws RefrigeratorIngredientDAOException {
		Map<Ingredient, Double> ingredWithQByRefrigerators = new HashMap<>();

		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM Refrigerator_ingredient where id=?");
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Ingredient ingredient = DaoFactory.getInstance().getIngredientDAO().getById(resultSet.getLong("ingredient_id"));
				Double ingredientQuantity = resultSet.getDouble("ingredient_id");
				ingredWithQByRefrigerators.put(ingredient, ingredientQuantity);
			}
		}catch (IngredientDAOException e) {
			logger.error("I can not get an item to the database" + e.toString());
		}
		catch (SQLException e) {
			logger.error("I can not get an item to the database" + e.toString());
			throw new RefrigeratorIngredientDAOException();
		}

		return ingredWithQByRefrigerators;
	}




}
