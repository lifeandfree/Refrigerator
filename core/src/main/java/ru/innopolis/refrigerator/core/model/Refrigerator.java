package ru.innopolis.refrigerator.core.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Refrigerator", propOrder = {

})
@XmlRootElement(name = "Refrigerator")
@Entity
@Table(name = "\"Refrigerator\"")
public class Refrigerator implements Serializable {

	public Refrigerator() {
	}

	public Refrigerator(String name, User user, Map<Ingredient, Double> ingredients) {
		this.name = name;
		this.user = user;
		this.ingredients = ingredients;
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
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@XmlElement(required = true)
	@OneToMany @JoinTable(name="Refrigerator_ingredient")
	@MapKeyColumn(name="ingredients")
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
