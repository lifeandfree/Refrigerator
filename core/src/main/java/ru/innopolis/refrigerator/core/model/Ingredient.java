package ru.innopolis.refrigerator.core.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ingredient", propOrder = {})
@XmlRootElement(name = "Ingredient")
@Entity
@Table(name = "\"Ingredient\"")
public class Ingredient implements Serializable {

	public Ingredient() {
	}

	public Ingredient(String name, String dimension, List<IngredientCategory> ingredientCategory) {
		this.name = name;
		this.dimension = dimension;
		this.ingredientCategory = ingredientCategory;
	}

	public Ingredient(String name, String dimension) {
		this.name = name;
		this.dimension = dimension;
		this.ingredientCategory = null;
	}


	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false)
	@XmlElement(required = true)
	private long id;

	@XmlElement(required = true)
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@XmlElement(required = true)
	@Column(name = "dimension", nullable = false, length = 100)
	private String dimension; // млитры граммы шт
	@XmlElement(required = true)
	@OneToMany
	@JoinTable(
			name="Ingredient_ingredientCategory",
			joinColumns = @JoinColumn( name="ingredient_id"),
			inverseJoinColumns = @JoinColumn( name="ingredientCategory_id")
	)
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
