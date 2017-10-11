package ru.innopolis.refrigerator.core.db.jdbc.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.IConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.exception.IngredientIngredientCategoryDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IngredientIngredientCategoryDAO {

	private static final Logger logger = LogManager.getLogger(IngredientIngredientCategoryDAO.class.getName());
	private static IConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public static Set<Long> getAllByIngredientId(long id) throws IngredientIngredientCategoryDAOException {
	Set<Long> ingToIngCategs = new HashSet<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Ingredient_ingredientCategory where id="+id);

			while (resultSet.next()) {
				Long ingToIngCateg = resultSet.getLong("ingredientCategory_id");
				ingToIngCategs.add(ingToIngCateg);

			}
		} catch (SQLException e) {
			logger.error("I can not get an item to the database" + e.toString());
			throw new IngredientIngredientCategoryDAOException();
		}
		return ingToIngCategs;
	}
}