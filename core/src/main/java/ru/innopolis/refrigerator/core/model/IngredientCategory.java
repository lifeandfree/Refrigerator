package ru.innopolis.refrigerator.core.model;

import org.hibernate.annotations.GenericGenerator;

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
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false)
	@XmlElement(required = true)
	private long id;
	@XmlElement(required = true)
	@Column(name = "ingredientCategorName", nullable = false, length = 255)
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
		return "IngredientCategory{" + "id=" + id + ", name='" + ingredientCategorName + '\'' + '}';
	}
}
