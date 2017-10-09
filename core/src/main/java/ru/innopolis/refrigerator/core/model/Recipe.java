package ru.innopolis.refrigerator.core.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Recipe", propOrder = {})
@XmlRootElement(name = "Recipe")
@Entity
@Table(name = "\"Recipe\"")
public class Recipe implements Serializable {

	public Recipe() {
	}

	public Recipe(String name, List<RecipeCategory> recipeCategorys, Complexity complexity, int time, HashMap<Ingredient, Double> ingredients, String instructions, String photo, CookingMethod cookingMethod) {
		this.name = name;
		this.recipeCategorys = recipeCategorys;
		this.complexity = complexity;
		this.time = time;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.photo = photo;
		this.cookingMethod = cookingMethod;
	}

	@XmlElement(required = true)
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@XmlElement(required = true)
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@XmlElement(required = true)
	@OneToMany
	@JoinTable(name = "Recipe_RecipeCategory", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "recipeCategory_id"))
	private List<RecipeCategory> recipeCategorys; //вид блида.

	@Enumerated(EnumType.STRING)
	@XmlElement(required = true)
	private Complexity complexity; //enum

	@XmlElement(required = true)
	@Column(name = "time", nullable = false)
	private int time;

	@XmlElement(required = true)
	@OneToMany @JoinTable(name="Recipe_ingredient")
	@MapKeyColumn(name="ingredients")
	private HashMap<Ingredient, Double> ingredients;// название / количество. map

	@Column(name = "instructions", nullable = false, length = 255)
	@XmlElement(required = true)
	private String instructions;

	@Column(name = "photo", nullable = false, length = 255)
	@XmlElement(required = true)
	private String photo;

	@ManyToOne
	@JoinColumn(name = "cookingMethod_id")
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

	public Complexity getComplexity() {
		return complexity;
	}

	public void setComplexity(Complexity complexity) {
		this.complexity = complexity;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Map<Ingredient, Double> getIngredients() {
		return ingredients;
	}

	public void setIngredients(HashMap<Ingredient, Double> ingredients) {
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

	@Override
	public String toString() {
		return "Recipe{" + "id=" + id + ", name='" + name + '\'' + ", recipeCategorys=" + recipeCategorys + ", complexity=" + complexity + ", time=" + time + ", ingredients=" + ingredients + ", instructions='" + instructions + '\'' + ", photo='" + photo + '\'' + ", cookingMethod=" + cookingMethod + '}';
	}
}
