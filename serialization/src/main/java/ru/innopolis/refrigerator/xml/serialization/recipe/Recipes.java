package ru.innopolis.refrigerator.xml.serialization.recipe;

import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Recipes", propOrder = { "recipes"
})
@XmlRootElement(name = "Recipes")
public class Recipes {

	public Recipes() {
		this.recipes = new ArrayList<>();
	}

	public Recipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	@XmlElement(required = true)
	private List<Recipe> recipes;

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	@Override
	public String toString() {
		return "Recipes{" + "Recipes=" + recipes + '}';
	}
}
