package ru.innopolis.refrigerator.core.db.jdbc.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.IConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.exception.RecipeCategoryDAOException;
import ru.innopolis.refrigerator.core.model.RecipeCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RecipeCategoryDAO {

	private static IConnectionFactory connection;
	private static final Logger logger = LogManager.getLogger(RecipeCategoryDAO.class.getName());

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public static List<RecipeCategory> getAll() throws RecipeCategoryDAOException {
		List<RecipeCategory> recipeCategories = new ArrayList<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM RecipeCategory");

			while(resultSet.next()) {
				RecipeCategory recipeCategory = new RecipeCategory(
						resultSet.getString("name")
				);
				recipeCategories.add(recipeCategory);
			}
		}
		catch (SQLException e) {
			logger.error("I can not get of all items to the database" + e.toString());
			throw new RecipeCategoryDAOException();
		}
		return recipeCategories;
	}

}