package ru.innopolis.refrigerator.core.db.jdbc.dao.ingredientcategory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.IConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.exception.IngredientCategoryDAOException;
import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IngredientCategoryDAO {

	private static IConnectionFactory connection;
	private static final Logger logger = LogManager.getLogger(IngredientCategoryDAO.class.getName());

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public static List<IngredientCategory> getAll() throws IngredientCategoryDAOException {
		List<IngredientCategory> ingredientCategoryList = new ArrayList<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM IngredientCategory");

			while (resultSet.next()) {
				IngredientCategory ingredientCategory = new IngredientCategory(resultSet.getString("name"));
				ingredientCategoryList.add(ingredientCategory);
			}
		} catch (SQLException e) {
			logger.error("I can not get of all items to the database" + e.toString());
			throw new IngredientCategoryDAOException();
		}
		return ingredientCategoryList;
	}

	public static Set<IngredientCategory> getAllById(Set<Long> ids) throws IngredientCategoryDAOException {
		Set<IngredientCategory> ingredientCategoryList = new HashSet<>();
		IngredientCategory ingredientCategory;

		for (Long id: ids
			 ) {
			try {
				Statement statement = connection.getConnection().createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM IngredientCategory where id=" +id);

				if (resultSet.next()) {
					ingredientCategory = new IngredientCategory(resultSet.getString("name"));
					ingredientCategoryList.add(ingredientCategory);
				}
			} catch (SQLException e) {
				logger.error("I can not get an item to the database" + e.toString());
				throw new IngredientCategoryDAOException();
			}
		}

		return ingredientCategoryList;
	}
}
