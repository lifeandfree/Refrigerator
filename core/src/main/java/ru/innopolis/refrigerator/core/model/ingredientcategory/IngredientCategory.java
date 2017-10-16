package ru.innopolis.refrigerator.core.model.ingredientcategory;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IngredientCategory", propOrder = {})
@Entity
@Table(name = "\"IngredientCategory\"")
public class IngredientCategory implements Serializable {

	public IngredientCategory() {
	}

	public IngredientCategory(String name) {
		this.ingredientCategorName = name;
	}

	@Id
//	@GeneratedValue(generator = "increment")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false)
	@XmlElement(required = true)
	private long id;

	@XmlElement(required = true)
	@Column(name = "ingredientCategorName", unique = true, nullable = false, length = 255)
	private String ingredientCategorName;

	public long getId() {
		return id;
	}

	public String getName() {
		return ingredientCategorName;
	}

	public void setName(String name) {
		this.ingredientCategorName = name;
	}

	@Override
	public String toString() {
		return "IngredientCategory{" + "ingredientCategorName='" + ingredientCategorName + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		IngredientCategory that = (IngredientCategory) o;

		return ingredientCategorName != null ? ingredientCategorName.equals(that.ingredientCategorName) : that.ingredientCategorName == null;
	}

	@Override
	public int hashCode() {
		return ingredientCategorName != null ? ingredientCategorName.hashCode() : 0;
	}
}
