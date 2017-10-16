package ru.innopolis.refrigerator.xml.serialization.ingredientcategory;


import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IngredientCategories", propOrder = { "ingredientCategories"
})
@XmlRootElement(name = "IngredientCategories")
public class IngredientCategories {

	public IngredientCategories() {
		this.ingredientCategories = new ArrayList<>();
	}

	@XmlElement(required = true)
	private List<IngredientCategory> ingredientCategories;

	public List<IngredientCategory> getIngredientCategories() {
		return ingredientCategories;
	}

	public void setIngredientCategories(List<IngredientCategory> ingredientCategories) {
		this.ingredientCategories = ingredientCategories;
	}

	@Override
	public String toString() {
		return "IngredientCategories{" + "ingredientCategories=" + ingredientCategories + '}';
	}
}
