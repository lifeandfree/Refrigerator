package ru.innopolis.refrigerator.core.db.jdbc.dao.recipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.dao.DaoFactory;
import ru.innopolis.refrigerator.core.db.exception.*;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;
import ru.innopolis.refrigerator.core.model.enumcls.Complexity;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.core.model.recipecategory.RecipeCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class RecipeDAO {

	private static final Logger logger = LogManager.getLogger(RecipeDAO.class.getName());
	private static ConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public List<Recipe> getAll() throws RecipeDAOException {
		List<Recipe> recipes = new ArrayList<>();
		List<RecipeCategory> categories = new ArrayList<>();
		List<Ingredient> ingredients = new ArrayList<>();
		List<IngredientCategory> ingredientCategories = new ArrayList<>();
		List<CookingMethod> cookingMethods = new ArrayList<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT rec.id recid, rec.complexity complexity, rec.instructions instructions,rec.name recipename, rec.user_id recuserid,rec.photo photo, rec.time rectime, cook.name cookname, rc.name rcname, ri.quantity quantity, ing.dimension dimension, ing.name ingname, ic.ingredientcategorname ingredientcategorname FROM \"Recipe\" rec JOIN \"CookingMethod\" cook ON rec.cookingmethod_id=cook.id JOIN recipe_recipecategory rr ON rec.id=rr.recipe_id JOIN \"RecipeCategory\" rc ON rc.id=recipecategory_id JOIN \"RecipeIngredient\" ri ON ri.recipe_id=rec.id JOIN \"Ingredient\" ing ON ri.ingredient_id=ing.id JOIN ingredient_ingredientcategory icc ON ing.id=icc.ingredient_id JOIN \"IngredientCategory\" ic ON ic.id=icc.ingredientCategory_id;");

			while(resultSet.next()) {

				IngredientCategory ingredientCategory = new IngredientCategory(resultSet.getString("ingredientcategorname"));
				int indexOfingredientCategory = ingredientCategories.indexOf(ingredientCategory);
				if (indexOfingredientCategory >= 0) {
					ingredientCategory = ingredientCategories.get(indexOfingredientCategory);
				} else {
					ingredientCategories.add(ingredientCategory);
				}

				CookingMethod cookingMethod = new CookingMethod(resultSet.getString("cookname"));
				int indexOfcookingMethod = cookingMethods.indexOf(cookingMethod);
				if (indexOfcookingMethod >= 0) {
					cookingMethod = cookingMethods.get(indexOfcookingMethod);
				} else {
					cookingMethods.add(cookingMethod);
				}

				RecipeCategory recipeCategory = new RecipeCategory(resultSet.getString("rcname"));
				int indexOfrecipeCategory = categories.indexOf(recipeCategory);
				if (indexOfrecipeCategory >= 0) {
					recipeCategory = categories.get(indexOfrecipeCategory);
				} else {
					categories.add(recipeCategory);
				}

				Ingredient ingredient = new Ingredient(resultSet.getString("ingname"), resultSet.getString("dimension")
				);
				int indexOfIngredient = ingredients.indexOf(ingredient);
				if (indexOfIngredient >= 0) {
					ingredient = ingredients.get(indexOfIngredient);
				} else {
					ingredients.add(ingredient);
				}

				Set<IngredientCategory> ingredientCategoryAdd = ingredient.getIngredientCategory();
				ingredientCategoryAdd.add(ingredientCategory);
				ingredient.setIngredientCategory(ingredientCategoryAdd);


				Recipe recipe = null;
				try {
					recipe = new Recipe(
							resultSet.getString("recipename"),
							Complexity.valueOf(resultSet.getString("complexity")),
							resultSet.getInt("rectime"),
							resultSet.getString("instructions"),
							resultSet.getString("photo"),
							cookingMethod,
							DaoFactory.getInstance().getUserDAO().getById(resultSet.getLong("recuserid")));
				}
				catch (UserDAOException e) {
					logger.error("I can not get of all items to the database" + e.toString());
				}

				int indexOfRecipe = recipes.indexOf(recipe);
				if (indexOfRecipe >= 0) {
					recipe = recipes.get(indexOfRecipe);
				} else {
					recipes.add(recipe);
				}

				Set<RecipeCategory> recipeCategories = recipe.getRecipeCategorys();
				recipeCategories.add(recipeCategory);
				recipe.setRecipeCategorys(recipeCategories);

				Double quantity = resultSet.getDouble("quantity");
				Map<Ingredient, Double> mapIng = recipe.getIngredients();
				mapIng.put(ingredient, quantity);
				recipe.setIngredients(mapIng);
			}
		}
		catch (SQLException e) {
			logger.error("I can not get of all items to the database" + e.toString());
			throw new RecipeDAOException();
		}
		return recipes;
	}

	private void insertOne(Recipe recipe, long userId, long cookingMethodId) throws RecipeDAOException {
		String sql = "INSERT INTO \"Recipe\" (complexity, instructions, name, photo, time, cookingmethod_id, user_id) VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.getConnection().prepareStatement(sql);
			ps.clearParameters();
			ps.setString(1, recipe.getComplexity().toString());
			ps.setString(2, recipe.getInstructions());
			ps.setString(3, recipe.getName());
			ps.setString(4, recipe.getPhoto());
			ps.setInt(5, recipe.getTime());
			ps.setLong(6, cookingMethodId);
			ps.setLong(7, userId);
			ps.addBatch();
			ps.executeBatch();
		}
		catch (SQLException e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new RecipeDAOException();
		}
	}

	public void insertAll(List<Recipe> recipes) throws RecipeDAOException {

		PreparedStatement ps = null;
		try {
			for (Recipe recipe:
					recipes) {
				long userId = 0;
				if (recipe.getUser() != null){
				try {
					userId = DaoFactory.getInstance().getUserDAO().getId(recipe.getUser());
				}
				catch (UserDAOException e) {
					logger.error("I can not get an user to the database" + e.toString());
					throw new RecipeDAOException();
				}
				}

				long  cookingMethodId = 0;
				try {
					cookingMethodId = DaoFactory.getInstance().getCookingMethodDAO().getId(recipe.getCookingMethod());
				}
				catch (CookingMethodDAOException e) {
					logger.error("I can not get an user to the database" + e.toString());
					throw new RecipeDAOException();
				}

				Set<Long> recipeCategoryIds = new HashSet<>();
				try {
					recipeCategoryIds = DaoFactory.getInstance().getRecipeCategoryDAO().getIds(recipe.getRecipeCategorys());
				}
				catch (RecipeCategoryDAOException e) {
					logger.error("I can not get an RecipeCategory to the database" + e.toString());
				}

				insertOne(recipe, userId, cookingMethodId);
				long recipeId = getId(recipe, userId, cookingMethodId);
				if (recipeId <= 0 ){
					logger.error("I can not get an recipeId to the database" );
					throw new RecipeDAOException();
				}

				Map<Ingredient, Double> ingredients = recipe.getIngredients();

				for(Map.Entry<Ingredient, Double> entry : ingredients.entrySet()) {
					Ingredient ingredient = entry.getKey();
					Double value = entry.getValue();
					long ingredientId;
					try {
						ingredientId = DaoFactory.getInstance().getIngredientDAO().getId(ingredient);
						String sql = "INSERT INTO \"RecipeIngredient\" (quantity, ingredient_id, recipe_id) VALUES (?,?,?)";
						ps = connection.getConnection().prepareStatement(sql);
						ps.clearParameters();
						ps.setDouble(1, value);
						ps.setLong(2, ingredientId);
						ps.setLong(3, recipeId);
						ps.addBatch();
						ps.executeBatch();
					}
					catch (IngredientDAOException e) {
						logger.error("I can not get an ingredientId to the database" );
					}
				}

				for (Long recipeCategoryId:
				recipeCategoryIds) {
					String sql = "INSERT INTO \"recipe_recipecategory\" (recipe_id, recipecategory_id) VALUES (?,?)";
					ps = connection.getConnection().prepareStatement(sql);
					ps.clearParameters();
					ps.setLong(1, recipeId);
					ps.setLong(2, recipeCategoryId);
					ps.addBatch();
					ps.executeBatch();
				}

			}
		}
		catch (SQLException e) {
			logger.error("I can not set an refrigerator to the database" );
		}

	}

	private long getId(Recipe recipe, long userId, long cookingMethodId) throws RecipeDAOException {
		long recipeId = 0;
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement(
					"SELECT * FROM \"Recipe\" WHERE complexity=? AND user_id=? AND instructions=? AND name=? AND time=? AND cookingmethod_id=?");
			statement.setString(1, recipe.getComplexity().toString());
			statement.setLong(2, userId);
			statement.setString(3, recipe.getInstructions());
			statement.setString(4, recipe.getName());
			statement.setInt(5, recipe.getTime());
			statement.setLong(6, cookingMethodId);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				recipeId = resultSet.getLong("id");
			}
		}
		catch (SQLException e) {
			logger.error("I can not get an item to the database" + e.toString());
			throw new RecipeDAOException();
		}
		return recipeId;
	}


	public List<Recipe> getAllByUserId(Long uid) throws RecipeDAOException {
		List<Recipe> recipes = new ArrayList<>();
		List<RecipeCategory> categories = new ArrayList<>();
		List<Ingredient> ingredients = new ArrayList<>();
		List<IngredientCategory> ingredientCategories = new ArrayList<>();
		List<CookingMethod> cookingMethods = new ArrayList<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			String sql = "SELECT rec.id recid, rec.complexity complexity, rec.instructions instructions,rec.name recipename, rec.user_id recuserid,rec.photo photo, rec.time rectime, cook.name cookname, rc.name rcname, ri.quantity quantity, ing.dimension dimension, ing.name ingname, ic.ingredientcategorname ingredientcategorname FROM \"Recipe\" rec JOIN \"CookingMethod\" cook ON rec.cookingmethod_id=cook.id JOIN recipe_recipecategory rr ON rec.id=rr.recipe_id JOIN \"RecipeCategory\" rc ON rc.id=recipecategory_id JOIN \"RecipeIngredient\" ri ON ri.recipe_id=rec.id JOIN \"Ingredient\" ing ON ri.ingredient_id=ing.id JOIN ingredient_ingredientcategory icc ON ing.id=icc.ingredient_id JOIN \"IngredientCategory\" ic ON ic.id=icc.ingredientCategory_id WHERE rec.user_id=" + uid;
			ResultSet resultSet = statement.executeQuery(sql);

			while(resultSet.next()) {

				IngredientCategory ingredientCategory = new IngredientCategory(resultSet.getString("ingredientcategorname"));
				int indexOfingredientCategory = ingredientCategories.indexOf(ingredientCategory);
				if (indexOfingredientCategory >= 0) {
					ingredientCategory = ingredientCategories.get(indexOfingredientCategory);
				} else {
					ingredientCategories.add(ingredientCategory);
				}

				CookingMethod cookingMethod = new CookingMethod(resultSet.getString("cookname"));
				int indexOfcookingMethod = cookingMethods.indexOf(cookingMethod);
				if (indexOfcookingMethod >= 0) {
					cookingMethod = cookingMethods.get(indexOfcookingMethod);
				} else {
					cookingMethods.add(cookingMethod);
				}

				RecipeCategory recipeCategory = new RecipeCategory(resultSet.getString("rcname"));
				int indexOfrecipeCategory = categories.indexOf(recipeCategory);
				if (indexOfrecipeCategory >= 0) {
					recipeCategory = categories.get(indexOfrecipeCategory);
				} else {
					categories.add(recipeCategory);
				}

				Ingredient ingredient = new Ingredient(resultSet.getString("ingname"), resultSet.getString("dimension")
				);
				int indexOfIngredient = ingredients.indexOf(ingredient);
				if (indexOfIngredient >= 0) {
					ingredient = ingredients.get(indexOfIngredient);
				} else {
					ingredients.add(ingredient);
				}

				Set<IngredientCategory> ingredientCategoryAdd = ingredient.getIngredientCategory();
				ingredientCategoryAdd.add(ingredientCategory);
				ingredient.setIngredientCategory(ingredientCategoryAdd);


				Recipe recipe = null;
				try {
					recipe = new Recipe(
							resultSet.getString("recipename"),
							Complexity.valueOf(resultSet.getString("complexity")),
							resultSet.getInt("rectime"),
							resultSet.getString("instructions"),
							resultSet.getString("photo"),
							cookingMethod,
							DaoFactory.getInstance().getUserDAO().getById(uid));
				}
				catch (UserDAOException e) {
					logger.error("I can not get of all items to the database" + e.toString());
				}

				int indexOfRecipe = recipes.indexOf(recipe);
				if (indexOfRecipe >= 0) {
					recipe = recipes.get(indexOfRecipe);
				} else {
					recipes.add(recipe);
				}

				Set<RecipeCategory> recipeCategories = recipe.getRecipeCategorys();
				recipeCategories.add(recipeCategory);
				recipe.setRecipeCategorys(recipeCategories);

				Double quantity = resultSet.getDouble("quantity");
				Map<Ingredient, Double> mapIng = recipe.getIngredients();
				mapIng.put(ingredient, quantity);
				recipe.setIngredients(mapIng);
			}
		}
		catch (SQLException e) {
			logger.error("I can not get of all items to the database" + e.toString());
			throw new RecipeDAOException();
		}
		return recipes;
	}
}
