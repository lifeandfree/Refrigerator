package ru.innopolis.refrigerator.core.db.jdbc.dao.recipecategory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.dao.recipe.RecipeDAO;
import ru.innopolis.refrigerator.core.db.dao.recipecategory.RecipeCategoryDAO;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactory;
import ru.innopolis.refrigerator.core.db.exception.RecipeCategoryDAOException;
import ru.innopolis.refrigerator.core.model.recipecategory.RecipeCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class RecipeCategoryDAOJDBCImpl implements RecipeCategoryDAO<RecipeCategory> {

	private static ConnectionFactory connection;
	private static final Logger logger = LogManager.getLogger(RecipeCategoryDAOJDBCImpl.class.getName());

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	@Override
	public  List<RecipeCategory> getAll() throws RecipeCategoryDAOException {
		List<RecipeCategory> recipeCategories = new ArrayList<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM \"RecipeCategory\"");

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

	@Override
	public RecipeCategory getById(Long elId) throws RecipeCategoryDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RecipeCategoryDAOException(msg);
	}

	@Override
	public RecipeCategory update(RecipeCategory el) throws RecipeCategoryDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RecipeCategoryDAOException(msg);
	}

	@Override
	public RecipeCategory add(RecipeCategory recipeCategory) throws RecipeCategoryDAOException {
		String sql = "INSERT INTO \"RecipeCategory\" (name) VALUES(?)";
		try {
			PreparedStatement ps = connection.getConnection().prepareStatement(sql);
			ps.clearParameters();
			ps.setString(1, recipeCategory.getName());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new RecipeCategoryDAOException();
		}
		return recipeCategory;
	}

	@Override
	public RecipeCategory delete(RecipeCategory recipeCategory) throws RecipeCategoryDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RecipeCategoryDAOException(msg);
	}

	@Override
	public Collection<RecipeCategory> addAll(Collection<RecipeCategory> recipeCategories) throws RecipeCategoryDAOException {
		String sql = "INSERT INTO \"RecipeCategory\" ( name ) VALUES(?)";
		try {
			PreparedStatement ps = connection.getConnection().prepareStatement(sql);
			for (RecipeCategory recipeCategory : recipeCategories) {
				ps.clearParameters();
				ps.setString(1, recipeCategory.getName());
				ps.addBatch();
			}
			ps.executeBatch();
		}
		catch (SQLException e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new RecipeCategoryDAOException();
		}
		return recipeCategories;
	}

	@Override
	public Set<Long> getIds(Set<RecipeCategory> recipeCategorys) throws RecipeCategoryDAOException {
		Set<Long> recipeCategoryIds=  new HashSet<>();
		for (RecipeCategory recipeCategory:
			 recipeCategorys) {
			long recipeCategoryId = 0;
			try {
				PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM \"RecipeCategory\" WHERE name=?");
				statement.setString(1, recipeCategory.getName());
				ResultSet resultSet = statement.executeQuery();
				if (resultSet.next()) {
					recipeCategoryId = resultSet.getLong("id");
				}
			}
			catch (SQLException e) {
				logger.error("I can not get an item to the database" + e.toString());
				throw new RecipeCategoryDAOException();
			}
			if (recipeCategoryId > 0){
				recipeCategoryIds.add(recipeCategoryId);
			}
		}
		return recipeCategoryIds;

	}
}