package ru.innopolis.refrigerator.core.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ingredient", propOrder = {})
@XmlRootElement(name = "Ingredient")
public class Ingredient {

	public Ingredient() {
	}

	public Ingredient(String name, String dimension, List<IngredientCategory> ingredientCategory) {
		this.name = name;
		this.dimension = dimension;
		this.ingredientCategory = ingredientCategory;
	}

	private long id;
	@XmlElement(required = true)
	private String name;
	@XmlElement(required = true)
	private String dimension; // млитры граммы шт
	@XmlElement(required = true)
	private List<IngredientCategory> ingredientCategory;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public List<IngredientCategory> getIngredientCategory() {
		return ingredientCategory;
	}

	public void setIngredientCategory(List<IngredientCategory> ingredientCategory) {
		this.ingredientCategory = ingredientCategory;
	}

	@Override
	public String toString() {
		return "Ingredient{" + "id=" + id + ", name='" + name + '\'' + ", dimension='" + dimension + '\'' + ", ingredientCategory=" + ingredientCategory + '}';
	}
}
