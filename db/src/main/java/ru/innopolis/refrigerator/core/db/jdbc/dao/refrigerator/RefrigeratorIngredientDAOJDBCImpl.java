package ru.innopolis.refrigerator.core.db.jdbc.dao.refrigerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.dao.refrigeratoringredient.RefrigeratorIngredientDAO;
import ru.innopolis.refrigerator.core.db.exception.RefrigeratorDAOException;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactory;
import ru.innopolis.refrigerator.core.db.DaoFactory;
import ru.innopolis.refrigerator.core.db.exception.IngredientDAOException;
import ru.innopolis.refrigerator.core.db.exception.RefrigeratorIngredientDAOException;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.refrigerator.Refrigerator;
import ru.innopolis.refrigerator.core.model.refrigerator.RefrigeratorIngredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefrigeratorIngredientDAOJDBCImpl implements RefrigeratorIngredientDAO<RefrigeratorIngredient>{

	private static final Logger logger = LogManager.getLogger(RefrigeratorIngredientDAOJDBCImpl.class.getName());
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
				Ingredient ingredient = (Ingredient) DaoFactory.getInstance().getIngredientDAO().getById(resultSet.getLong("ingredient_id"));
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
		catch (Exception e) {
			logger.error("I can not get an item to the database" + e.toString());
			throw new RefrigeratorIngredientDAOException();
		}

		return ingredWithQByRefrigerators;
	}

	@Override
	public RefrigeratorIngredient add(RefrigeratorIngredient el) throws RefrigeratorIngredientDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RefrigeratorIngredientDAOException(msg);
	}

	@Override
	public RefrigeratorIngredient delete(RefrigeratorIngredient el) throws RefrigeratorIngredientDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RefrigeratorIngredientDAOException(msg);
	}

	@Override
	public Collection<RefrigeratorIngredient> getAll() throws RefrigeratorIngredientDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RefrigeratorIngredientDAOException(msg);
	}

	@Override
	public RefrigeratorIngredient getById(Long elId) throws RefrigeratorIngredientDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RefrigeratorIngredientDAOException(msg);
	}

	@Override
	public RefrigeratorIngredient update(RefrigeratorIngredient el) throws RefrigeratorIngredientDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RefrigeratorIngredientDAOException(msg);
	}

	@Override
	public Collection<RefrigeratorIngredient> addAll(Collection<RefrigeratorIngredient> els) throws RefrigeratorIngredientDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RefrigeratorIngredientDAOException(msg);
	}

	@Override
	public List<RefrigeratorIngredient> getRefIngrByRef(Refrigerator refrigerator) throws RefrigeratorIngredientDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RefrigeratorIngredientDAOException(msg);
	}
}
