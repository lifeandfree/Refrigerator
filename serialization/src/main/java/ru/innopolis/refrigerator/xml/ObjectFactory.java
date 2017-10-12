package ru.innopolis.refrigerator.xml;

import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.core.model.recipecategory.RecipeCategory;
import ru.innopolis.refrigerator.core.model.refrigerator.Refrigerator;
import ru.innopolis.refrigerator.core.model.session.Session;
import ru.innopolis.refrigerator.core.model.user.User;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: model
	 */
	public ObjectFactory() {
	}

	public CookingMethod createCookingMethod() {
		return new CookingMethod();
	}

	public Ingredient createIngredient() {
		return new Ingredient();
	}

	public IngredientCategory createIngredientCategory() {
		return new IngredientCategory();
	}

	public Recipe createRecipe() {
		return new Recipe();
	}

	public RecipeCategory createRecipeCategory() {
		return new RecipeCategory();
	}

	public Refrigerator createRefrigerator() {
		return new Refrigerator();
	}

	public Session createSession() {
		return new Session();
	}

	public User createUser() {
		return new User();
	}


}
