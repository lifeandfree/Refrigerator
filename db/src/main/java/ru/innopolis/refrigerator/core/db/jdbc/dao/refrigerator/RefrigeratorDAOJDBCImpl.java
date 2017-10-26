package ru.innopolis.refrigerator.core.db.jdbc.dao.refrigerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.dao.refrigerator.RefrigeratorDAO;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.dao.DaoFactory;
import ru.innopolis.refrigerator.core.db.exception.*;
import ru.innopolis.refrigerator.core.model.enumcls.Role;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;
import ru.innopolis.refrigerator.core.model.refrigerator.Refrigerator;
import ru.innopolis.refrigerator.core.model.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class RefrigeratorDAOJDBCImpl implements RefrigeratorDAO<Refrigerator> {

	private static final Logger logger = LogManager.getLogger(RefrigeratorDAOJDBCImpl.class.getName());
	private static ConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	@Override
	public Refrigerator add(Refrigerator refrigerator) throws RefrigeratorDAOException {
		insertOne(refrigerator.getName(), refrigerator.getUser().getId());
		return refrigerator;
	}

	@Override
	public Refrigerator delete(Refrigerator el) throws RefrigeratorDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RefrigeratorDAOException(msg);
	}

	@Override
	public List<Refrigerator> getAll() throws RefrigeratorDAOException {
		List<Refrigerator> refrigerators = new ArrayList<>();
		List<Ingredient> ingredientList = new ArrayList<>();
		List<IngredientCategory> ingredientCategories = new ArrayList<>();
		List<User> users = new ArrayList<>();
//		Map<Ingredient, Double> ingredientDoubleMap = new HashMap<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(
			"SELECT ref.id id, ref.name refrigeratorname , ref.user_id user_id, u.email email, u.password userpassword, u.role userrole, u.username username, refin.quantity quantity, refin.ingredient_id ingredient_id, refin.refrigerator_id refrigerator_id, ing.dimension dimension, ing.name ingname, icc.ingredientcategory_id ingredientcategory_id, ic.ingredientcategorname ingredientcategorname FROM \"Refrigerator\" ref JOIN \"User\" u ON u.id=ref.user_id  JOIN \"RefrigeratorIngredient\" refin ON ref.id=refin.refrigerator_id JOIN \"Ingredient\" ing ON ing.id=refin.ingredient_id JOIN ingredient_ingredientcategory icc ON ing.id=icc.ingredient_id JOIN \"IngredientCategory\" ic ON ic.id=icc.ingredientCategory_id;");
			while(resultSet.next()) {
				User user = new User(resultSet.getString("username"), resultSet.getString("userpassword"), Role.valueOf(resultSet.getString("userrole")), resultSet.getString("email"));

				int indexOfuser = users.indexOf(user);
				if (indexOfuser >= 0){
					user = users.get(indexOfuser);
				}
				else
				{
					users.add(user);
				}

				IngredientCategory ingredientCategory = new IngredientCategory(resultSet.getString("ingredientcategorname"));
				int indexOfingredientCategory = ingredientCategories.indexOf(ingredientCategory);
				if (indexOfingredientCategory >= 0){
					ingredientCategory = ingredientCategories.get(indexOfingredientCategory);
				}
				else
				{
					ingredientCategories.add(ingredientCategory);
				}

				Ingredient ingredient = new Ingredient(
						resultSet.getString("ingname"),
						resultSet.getString("dimension")
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

				Refrigerator refrigerator = new Refrigerator(
						resultSet.getString("refrigeratorname"), user
				);
				int indexOfRefrigerator =  refrigerators.indexOf(refrigerator);
				if (indexOfRefrigerator >= 0){
					refrigerator = refrigerators.get(indexOfRefrigerator);
				}
				else {
					refrigerators.add(refrigerator);
				}

				Double quantity = resultSet.getDouble("quantity");
				Map<Ingredient, Double> mapIng = refrigerator.getIngredients();
				mapIng.put(ingredient, quantity);
				refrigerator.setIngredients(mapIng);
			}
		}
		catch (SQLException e) {
			logger.error("I can not get of all items to the database" + e.toString());
			throw new RefrigeratorDAOException();
		}
		return refrigerators;
	}

	@Override
	public Refrigerator getById(Long elId) throws RefrigeratorDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RefrigeratorDAOException(msg);
	}

	@Override
	public Refrigerator update(Refrigerator el) throws RefrigeratorDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RefrigeratorDAOException(msg);
	}

	@Override
	public Collection<Refrigerator> addAll(Collection<Refrigerator> refrigerators) throws RefrigeratorDAOException {

		PreparedStatement ps = null;
		try {
			for (Refrigerator refrigerator:
					refrigerators) {
				long userId = 0;
				try {
					userId = DaoFactory.getInstance().getUserDAO().getId(refrigerator.getUser());
				}
				catch (UserDAOException e) {
					logger.error("I can not get an user to the database" + e.toString());
					throw new RefrigeratorDAOException();
				}
				if (userId > 0){
					insertOne(refrigerator.getName(), userId);
				}
				else {
					logger.error("I can not add an refrigerator to the database. Not User"+ refrigerator.getUser().toString());
					throw new RefrigeratorDAOException();
				}
				long refrigeratorId = getId(refrigerator.getName(), userId);
				if (refrigeratorId <= 0 ){
					logger.error("I can not get an refrigeratorID to the database" );
					throw new RefrigeratorDAOException();
				}

				Map<Ingredient, Double> ingredients = refrigerator.getIngredients();

				for(Map.Entry<Ingredient, Double> entry : ingredients.entrySet()) {
					Ingredient ingredient = entry.getKey();
					Double value = entry.getValue();

					long ingredientId;
					try {
						ingredientId = DaoFactory.getInstance().getIngredientDAO().getId(ingredient);
						String sql = "INSERT INTO \"RefrigeratorIngredient\" (quantity, ingredient_id, refrigerator_id) VALUES (?,?,?)";
						ps = connection.getConnection().prepareStatement(sql);
						ps.clearParameters();
						ps.setDouble(1, value);
						ps.setLong(2, ingredientId);
						ps.setLong(3, refrigeratorId);
						ps.addBatch();
						ps.executeBatch();
					}
					catch (IngredientDAOException e) {
						logger.error("I can not get an ingredientId to the database" );
					}
				}

			}
		}
		catch (SQLException e) {
			logger.error("I can not set an refrigerator to the database" );
		}
		return refrigerators;

	}

	@Override
	public long getId(String name, long userId) throws RefrigeratorDAOException {
		long refrigeratorId = 0;
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM \"Refrigerator\" WHERE name=? AND user_id=?");
			statement.setString(1, name);
			statement.setLong(2, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				refrigeratorId = resultSet.getLong("id");
			}
		}
		catch (SQLException e) {
			logger.error("I can not get an item to the database" + e.toString());
			throw new RefrigeratorDAOException();
		}
		return refrigeratorId;
	}

	private void insertOne(String name, long userId) throws RefrigeratorDAOException {
		String sql = "INSERT INTO \"Refrigerator\" (name, user_id) VALUES(?,?)";
		try {
			PreparedStatement ps = connection.getConnection().prepareStatement(sql);
			ps.clearParameters();
			ps.setString(1, name);
			ps.setLong(2, userId);
			ps.addBatch();
			ps.executeBatch();
		}
		catch (SQLException e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new RefrigeratorDAOException();
		}
	}
}