package ru.innopolis.refrigerator.xml.serialization.ingredient;

import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ingredients", propOrder = { "ingredients"
})
@XmlRootElement(name = "Ingredients")
public class Ingredients {

	public Ingredients() {
		this.ingredients = new ArrayList<>();
	}

	@XmlElement(required = true)
	private List<Ingredient> ingredients;

	public List<Ingredient> getIngredients() {
		return this.ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Ingredients{" + "ingredients='" + this.ingredients + '\'' + '}';
	}
}
