package ru.innopolis.refrigerator.core.model;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Recipe", propOrder = {})
@XmlRootElement(name = "Recipe")
public class Recipe {

	public Recipe() {
	}

	public Recipe(String name, List<RecipeCategory> recipeCategorys, byte complexity, int time, Set<Ingredient> ingredients, String instructions, String photo, CookingMethod cookingMethod) {
		this.name = name;
		this.recipeCategorys = recipeCategorys;
		this.complexity = complexity;
		this.time = time;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.photo = photo;
		this.cookingMethod = cookingMethod;
	}

	private long id;
	@XmlElement(required = true)
	private String name;
	@XmlElement(required = true)
	private List<RecipeCategory> recipeCategorys; //вид блида.
	@XmlElement(required = true)
	private byte complexity;
	@XmlElement(required = true)
	private int time;
	@XmlElement(required = true)
	private Set<Ingredient> ingredients;// название / количество.
	@XmlElement(required = true)
	private String instructions;
	@XmlElement(required = true)
	private String photo;
	@XmlElement(required = true)
	private CookingMethod cookingMethod; // способ приготовления

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RecipeCategory> getRecipeCategorys() {
		return recipeCategorys;
	}

	public void setRecipeCategorys(List<RecipeCategory> recipeCategorys) {
		this.recipeCategorys = recipeCategorys;
	}

	public byte getComplexity() {
		return complexity;
	}

	public void setComplexity(byte complexity) {
		this.complexity = complexity;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public CookingMethod getCookingMethod() {
		return cookingMethod;
	}

	public void setCookingMethod(CookingMethod cookingMethod) {
		this.cookingMethod = cookingMethod;
	}
}
