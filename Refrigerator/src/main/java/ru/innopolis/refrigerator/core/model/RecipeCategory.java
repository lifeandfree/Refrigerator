package ru.innopolis.refrigerator.core.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecipeCategory", propOrder = {

})
public class RecipeCategory {

	public RecipeCategory() {
	}

	public RecipeCategory(String name) {
		this.name = name;
	}

	private long id;
	@XmlElement(required = true)
	private String name;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
