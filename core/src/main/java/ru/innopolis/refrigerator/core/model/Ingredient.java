package ru.innopolis.refrigerator.core.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ingredient", propOrder = {})
@XmlRootElement(name = "Ingredient")
@Entity
@Table(name = "\"Ingredient\"")
public class Ingredient implements Serializable {

	public Ingredient() {
	}

	public Ingredient(String name, String dimension, Set<IngredientCategory> ingredientCategory) {
		this.name = name;
		this.dimension = dimension;
		this.ingredientCategory = ingredientCategory;
	}

	public Ingredient(String name, String dimension) {
		this.name = name;
		this.dimension = dimension;
		this.ingredientCategory = new HashSet<>();
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
			joinColumns = @JoinColumn( name="ingredient_id", unique = false),
			inverseJoinColumns = @JoinColumn( name="ingredientCategory_id", unique = false)
	)
	private Set<IngredientCategory> ingredientCategory;

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

	public Set<IngredientCategory> getIngredientCategory() {
		return ingredientCategory;
	}

	public void setIngredientCategory(Set<IngredientCategory> ingredientCategory) {
		this.ingredientCategory = ingredientCategory;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Ingredient that = (Ingredient) o;

		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;
		return dimension != null ? dimension.equals(that.dimension) : that.dimension == null;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (dimension != null ? dimension.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Ingredient{" + "name='" + name + '\'' + ", dimension='" + dimension + '\'' + ", ingredientCategory=" + ingredientCategory + '}';
	}
}
