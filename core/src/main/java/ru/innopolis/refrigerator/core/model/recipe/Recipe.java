package ru.innopolis.refrigerator.core.model.recipe;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;
import ru.innopolis.refrigerator.core.model.enumcls.Complexity;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.recipecategory.RecipeCategory;
import ru.innopolis.refrigerator.core.model.user.User;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Recipe", propOrder = {})
@XmlRootElement(name = "Recipe")
@Entity
@Table(name = "\"Recipe\"")
public class Recipe implements Serializable {

	public Recipe() {
		this.recipeCategorys = new HashSet<>();
		this.ingredients = new HashMap<>();

	}

	public Recipe(String name, Set<RecipeCategory> recipeCategorys, Complexity complexity, int time, Map<Ingredient, Double> ingredients, String instructions, String photo, CookingMethod cookingMethod, User user) {
		this.name = name;
		this.recipeCategorys = recipeCategorys;
		this.complexity = complexity;
		this.time = time;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.photo = photo;
		this.cookingMethod = cookingMethod;
		this.user = user;
	}

	public Recipe(String name, Complexity complexity, int time, String instructions, String photo, CookingMethod cookingMethod, User user) {
		this.recipeCategorys = new HashSet<>();
		this.ingredients = new HashMap<>();
		this.name = name;
		this.complexity = complexity;
		this.time = time;
		this.instructions = instructions;
		this.photo = photo;
		this.cookingMethod = cookingMethod;
		this.user = user;
	}

	@XmlElement(required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(generator = "increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@XmlElement(required = true)
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@XmlElement(required = true)
	@ManyToMany(targetEntity = RecipeCategory.class, cascade = { CascadeType.ALL, CascadeType.REMOVE })
//	@JoinTable(name = "recipe_recipecategory",
//			joinColumns = { @JoinColumn(name = "recipe_id") },
//			inverseJoinColumns = { @JoinColumn(name = "recipeCategory_id") })
	private Set<RecipeCategory> recipeCategorys; //вид блида.

	@Enumerated(EnumType.STRING)
	@Column(name = "complexity", nullable = false)
	@XmlElement(required = true)
	private Complexity complexity;

	@XmlElement(required = true)
	@Column(name = "time", nullable = true)
	private int time;

	@XmlElement(required = true)
//	@OneToMany @JoinTable(name="Recipe_ingredient")
//	@MapKeyColumn(name="ingredients")
	@Transient
	private Map<Ingredient, Double> ingredients;// название / количество. map

	@Column(name = "instructions", nullable = false, length = 255)
	@XmlElement(required = true)
	private String instructions;

	@Column(name = "photo", nullable = true, length = 255)
	@XmlElement(required = true)
	private String photo;

	@ManyToOne
	@JoinColumn(name = "cookingMethod_id", nullable = true)
	@XmlElement(required = true)
	private CookingMethod cookingMethod; // способ приготовления

	@XmlElement(required = true)
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<RecipeCategory> getRecipeCategorys() {
		return recipeCategorys;
	}

	public void setRecipeCategorys(Set<RecipeCategory> recipeCategorys) {
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

	public void setIngredients(Map<Ingredient, Double> ingredients) {
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
		return "Recipe{" + "name='" + name + '\'' + ", recipeCategorys=" + recipeCategorys + ", complexity=" + complexity + ", time=" + time + ", ingredients=" + ingredients + ", instructions='" + instructions + '\'' + ", photo='" + photo + '\'' + ", cookingMethod=" + cookingMethod + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Recipe recipe = (Recipe) o;

		if (name != null ? !name.equals(recipe.name) : recipe.name != null)
			return false;
		if (complexity != recipe.complexity)
			return false;
		if (instructions != null ? !instructions.equals(recipe.instructions) : recipe.instructions != null)
			return false;
		return photo != null ? photo.equals(recipe.photo) : recipe.photo == null;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (complexity != null ? complexity.hashCode() : 0);
		result = 31 * result + (instructions != null ? instructions.hashCode() : 0);
		result = 31 * result + (photo != null ? photo.hashCode() : 0);
		return result;
	}
}
