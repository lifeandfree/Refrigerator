package ru.innopolis.refrigerator.core.model.refrigerator;

import org.hibernate.annotations.GenericGenerator;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.ingredientcategory.IngredientCategory;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RefrigeratorIngredient", propOrder = {

})
@XmlRootElement(name = "RefrigeratorIngredient")
@Entity
@Table(name = "\"RefrigeratorIngredient\"")
public class RefrigeratorIngredient implements Serializable {

	public RefrigeratorIngredient() {
		ingredient = new Ingredient();
		refrigerator = new Refrigerator();
	}

	public RefrigeratorIngredient(Refrigerator refrigerator, Ingredient ingredient, Double quantity) {
		this.quantity = quantity;
		this.ingredient = ingredient;
		this.refrigerator = refrigerator;
	}

	@XmlElement(required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@XmlElement(required = true)
	@Column(name = "quantity", nullable = false)
	private Double quantity;

	@XmlElement(required = true)
	@ManyToOne(targetEntity = Ingredient.class)
//	@JoinColumn(name = "ingredient_id", nullable = false, unique = false)
	private Ingredient ingredient;

	@XmlElement(required = true)
	@ManyToOne(targetEntity = Refrigerator.class)
//	@JoinColumn(name = "refrigerator_id", nullable = false, unique = false)
	private Refrigerator refrigerator;

	@Override
	public String toString() {
		return "RefrigeratorIngredient{" + "quantity=" + quantity + ", ingredient=" + ingredient + ", refrigerator=" + refrigerator + '}';
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

	public Refrigerator getRefrigerator() {
		return refrigerator;
	}

	public void setRefrigerator(Refrigerator refrigerator) {
		this.refrigerator = refrigerator;
	}
}
