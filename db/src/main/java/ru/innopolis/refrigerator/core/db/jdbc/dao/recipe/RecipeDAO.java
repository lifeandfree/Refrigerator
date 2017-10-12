package ru.innopolis.refrigerator.core.db.jdbc.dao.recipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.IConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.exception.RecipeDAOException;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;
import ru.innopolis.refrigerator.core.model.enumcls.Complexity;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.core.model.recipecategory.RecipeCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecipeDAO {

	public static void main(String[] args) {
		try {
			logger.info(getAll());
		}
		catch (RecipeDAOException e) {
			e.printStackTrace();
		}
	}
	private static final Logger logger = LogManager.getLogger(RecipeDAO.class.getName());
	private static IConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public static List<Recipe> getAll() throws RecipeDAOException {
		List<Recipe> recipes = new ArrayList<>();
		List<RecipeCategory> categories = new ArrayList<>();
		List<Ingredient> ingredients = new ArrayList<>();
		List<IngredientCategory> ingredientCategories = new ArrayList<>();
		List<CookingMethod> cookingMethods = new ArrayList<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT rec.id recid, rec.complexity complexity, rec.instructions instructions,rec.name recipename,rec.photo photo, rec.time rectime, cook.name cookname, rc.name rcname, ri.quantity quantity, ing.dimension dimension, ing.name ingname, ic.ingredientcategorname ingredientcategorname FROM \"Recipe\" rec JOIN \"CookingMethod\" cook ON rec.cookingmethod_id=cook.id JOIN recipe_recipecategory rr ON rec.id=rr.recipe_id JOIN \"RecipeCategory\" rc ON rc.id=recipecategory_id JOIN \"RecipeIngredient\" ri ON ri.recipe_id=rec.id JOIN \"Ingredient\" ing ON ri.ingredient_id=ing.id JOIN ingredient_ingredientcategory icc ON ing.id=icc.ingredient_id JOIN \"IngredientCategory\" ic ON ic.id=icc.ingredientCategory_id;");

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


				Recipe recipe = new Recipe(
						resultSet.getString("recipename"),
						Complexity.valueOf(resultSet.getString("complexity")),
						resultSet.getInt("rectime"),
						resultSet.getString("instructions"),
						resultSet.getString("photo"),
						cookingMethod);

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
