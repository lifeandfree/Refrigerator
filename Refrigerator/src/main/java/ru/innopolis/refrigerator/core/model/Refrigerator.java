package ru.innopolis.refrigerator.core.model;

import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Refrigerator", propOrder = {

})
@XmlRootElement(name = "Refrigerator")
public class Refrigerator {

	public Refrigerator() {
	}

	public Refrigerator(String name, User user, Map<Ingredient, Double> ingredients) {
		this.name = name;
		this.user = user;
		this.ingredients = ingredients;
	}

	private long id;
	@XmlElement(required = true)
	private String name;
	@XmlElement(required = true)
	private User user;
	@XmlElement(required = true)
	private Map<Ingredient, Double> ingredients;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<Ingredient, Double> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Map<Ingredient, Double> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Refrigerator{" + "id=" + id + ", name='" + name + '\'' + ", user=" + user + ", ingredients=" + ingredients + '}';
	}

}
