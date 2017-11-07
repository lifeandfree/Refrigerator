package ru.innopolis.refrigerator.core.model.recipe;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.engine.internal.Cascade;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecipeIngredient", propOrder = {

})
@XmlRootElement(name = "RecipeIngredient")
@Entity
@Table(name = "\"RecipeIngredient\"")
public class RecipeIngredient implements Serializable {

	public RecipeIngredient() {
		recipe = new Recipe();
		ingredient = new Ingredient();
	}

	public RecipeIngredient( Recipe recipe, Ingredient ingredient, Double quantity) {
		this.quantity = quantity;
		this.ingredient = ingredient;
		this.recipe = recipe;
	}

	@XmlElement(required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(generator = "increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@XmlElement(required = true)
	@Column(name = "quantity", nullable = false)
	private Double quantity;

	@XmlElement(required = true)
	@ManyToOne (cascade = {CascadeType.REMOVE, CascadeType.PERSIST })
	@JoinColumn(name = "ingredient_id", nullable = false)
	private Ingredient ingredient;

	@XmlElement(required = true)
	@ManyToOne
	@JoinColumn(name = "recipe_id", nullable = false)
	private Recipe recipe;

	@Override
	public String toString() {
		return "RecipeIngredient{" + "quantity=" + quantity + ", ingredient=" + ingredient + ", recipe=" + recipe + '}';
	}

	public long getId() {
		return id;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
}
