package ru.innopolis.refrigerator.core.db.jdbc.dao.refrigerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.IConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.exception.RefrigeratorDAOException;
import ru.innopolis.refrigerator.core.model.enumcls.Role;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;
import ru.innopolis.refrigerator.core.model.refrigerator.Refrigerator;
import ru.innopolis.refrigerator.core.model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RefrigeratorDAO {

	private static final Logger logger = LogManager.getLogger(RefrigeratorDAO.class.getName());
	private static IConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public static List<Refrigerator> getAll() throws RefrigeratorDAOException {
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

//				Map<Ingredient, Double> mapIng = RefrigeratorIngredientDAO.getAllByRefrigeratorId(resultSet.getLong("id"));
			}
		}
		catch (SQLException e) {
			logger.error("I can not get of all items to the database" + e.toString());
			throw new RefrigeratorDAOException();
		}
		return refrigerators;
	}
}