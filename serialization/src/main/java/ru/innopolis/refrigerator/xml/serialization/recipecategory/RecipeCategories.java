package ru.innopolis.refrigerator.xml.serialization.recipecategory;


import ru.innopolis.refrigerator.core.model.recipecategory.RecipeCategory;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecipeCategories", propOrder = { "recipeCategories"
})
@XmlRootElement(name = "RecipeCategories")
public class RecipeCategories {

	public RecipeCategories() {
		recipeCategories = new ArrayList<>();
	}

	@XmlElement(required = true)
	private List<RecipeCategory> recipeCategories;

	@Override
	public String toString() {
		return "RecipeCategories{" + "recipeCategories=" + recipeCategories + '}';
	}

	public List<RecipeCategory> getRecipeCategories() {
		return recipeCategories;
	}

	public void setRecipeCategories(List<RecipeCategory> recipeCategories) {
		this.recipeCategories = recipeCategories;
	}
}
