package ru.innopolis.refrigerator.core.db.jdbc.dao.ingredient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.dao.DaoFactory;
import ru.innopolis.refrigerator.core.db.exception.*;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IngredientDAO {

	private static final Logger logger = LogManager.getLogger(IngredientDAO.class.getName());
	private static ConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public List<Ingredient> getAll() throws IngredientDAOException {
		List<Ingredient> ingredientList = new ArrayList<>();
		List<IngredientCategory> ingredientCategories = new ArrayList<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT * FROM  \"Ingredient\" i JOIN \"ingredient_ingredientcategory\" iic ON i.id=iic.ingredient_id  JOIN \"IngredientCategory\" ic ON iic.ingredientcategory_id=ic.id");

			while (resultSet.next()) {
				IngredientCategory ingredientCategory = new IngredientCategory(resultSet.getString("ingredientcategorname"));
				int indexOfingredientCategories = ingredientCategories.indexOf(ingredientCategory);
				if (indexOfingredientCategories >= 0){
					ingredientCategory = ingredientCategories.get(indexOfingredientCategories);
				}
				else
				{
					ingredientCategories.add(ingredientCategory);
				}

				Ingredient ingredient = new Ingredient(
						resultSet.getString("name"),
						resultSet.getString("dimension")
						//IngredientCategoryDAO.getAllById(IngredientIngredientCategoryDAO.getAllByIngredientId(resultSet.getLong("id")))
						);
				int indexOfIngredient =  ingredientList.indexOf(ingredient);
				if (indexOfIngredient >= 0){
					ingredient = ingredientList.get(indexOfIngredient);
				}
				else {
					ingredientList.add(ingredient);
				}
				Set<IngredientCategory> ingredientCategoryAdd = ingredient.getIngredientCategory();
				ingredientCategoryAdd.add(ingredientCategory);
				ingredient.setIngredientCategory(ingredientCategoryAdd);
			}
		}
		catch (SQLException e) {
			logger.error("I can not get of all items to the database" + e.toString());
			throw new IngredientDAOException();
		}
		return ingredientList;
	}

	public Ingredient getById(long id) throws IngredientDAOException {
		Ingredient ingredient = null;
		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Ingredient\" where id=?");

			if (resultSet.next()) {
				ingredient = new Ingredient(
						resultSet.getString("name"),
						resultSet.getString("dimension"),
						DaoFactory.getInstance().getIngredientCategoryDAO().getAllById(DaoFactory.getInstance().getIngredientIngredientCategoryDAO().getAllByIngredientId(resultSet.getLong("id"))));

			}
		}
		catch (IngredientCategoryDAOException e) {
			logger.error("I can not get an item to the database" + e.toString());
		}
		catch (IngredientIngredientCategoryDAOException e) {
			logger.error("I can not get an item to the database" + e.toString());
		}
		catch (SQLException e) {
			logger.error("I can not get an item to the database" + e.toString());
			throw new IngredientDAOException();
		}
		return ingredient;
	}

	public void insertAll(List<Ingredient> ingredients) throws IngredientDAOException {
			for (Ingredient ingredient : ingredients) {
				Set<Long> ingCatIds = new HashSet<>();
				try {
					ingCatIds = DaoFactory.getInstance().getIngredientCategoryDAO().getAllId(ingredient.getIngredientCategory());
				}
				catch (IngredientCategoryDAOException e) {
					logger.error("I can not add an item to the database" + e.toString());
					throw new IngredientDAOException();
				}
//				try {
//					connection.getConnection().setAutoCommit(false);
//				}
//				catch (SQLException e) {
//					logger.error("I can not set setAutoCommit to the database" + e.toString());
//					connection.getConnection().rollback();
//				}
				insertOne(ingredient.getDimension(), ingredient.getName());
				long ingredientId = getId(ingredient);
				String sql = "INSERT INTO ingredient_ingredientcategory (ingredient_id, ingredientCategory_id) VALUES (?,?)";
				try {
					PreparedStatement ps = connection.getConnection().prepareStatement(sql);
					for (long id : ingCatIds) {
						ps.clearParameters();
						ps.setLong(1, ingredientId);
						ps.setLong(2, id);
						ps.addBatch();
					}
					ps.executeBatch();
				}
				catch (SQLException e) {
					logger.error("I can not add an item to the database" + e.toString());
					throw new IngredientDAOException();
				}
			}
	}

	public long getId(Ingredient ingredient) throws IngredientDAOException {
		long ingredientId = 0;
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM \"Ingredient\" WHERE dimension=? AND name=?");
			statement.setString(1, ingredient.getDimension());
			statement.setString(2, ingredient.getName());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				ingredientId = resultSet.getLong("id");
			}
		}
		catch (SQLException e) {
			logger.error("I can not get an item to the database" + e.toString());
			throw new IngredientDAOException();
		}
		return ingredientId;
	}

	private void insertOne(String dimension, String name) throws IngredientDAOException {
		String sql = "INSERT INTO \"Ingredient\" (name, dimension) VALUES (?, ?)";
		try {
			PreparedStatement ps = connection.getConnection().prepareStatement(sql);
			ps.clearParameters();
			ps.setString(1, name);
			ps.setString(2, dimension);
			ps.addBatch();
			ps.executeBatch();
		}
		catch (SQLException e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new IngredientDAOException();
		}
	}
}