package ru.innopolis.refrigerator.core.db.jdbc.dao.ingredient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.IConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.dao.ingredientcategory.IngredientCategoryDAO;
import ru.innopolis.refrigerator.core.db.jdbc.exception.IngredientCategoryDAOException;
import ru.innopolis.refrigerator.core.db.jdbc.exception.IngredientDAOException;
import ru.innopolis.refrigerator.core.db.jdbc.exception.IngredientIngredientCategoryDAOException;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class IngredientDAO {

	private static final Logger logger = LogManager.getLogger(IngredientDAO.class.getName());
	private static IConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public static List<Ingredient> getAll() throws IngredientDAOException {
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

	public static Ingredient getById(long id) throws IngredientDAOException {

		Ingredient ingredient = null;

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Ingredient where id=?");

			if (resultSet.next()) {
				ingredient = new Ingredient(
						resultSet.getString("name"),
						resultSet.getString("dimension"),
						IngredientCategoryDAO.getAllById(IngredientIngredientCategoryDAO.getAllByIngredientId(resultSet.getLong("id"))));

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
}