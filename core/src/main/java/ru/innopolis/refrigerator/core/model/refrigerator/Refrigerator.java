package ru.innopolis.refrigerator.core.model.refrigerator;

import org.hibernate.annotations.GenericGenerator;
import ru.innopolis.refrigerator.core.model.user.User;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Refrigerator", propOrder = {

})
@XmlRootElement(name = "Refrigerator")
@Entity
@Table(name = "\"Refrigerator\"")
public class Refrigerator implements Serializable {

	public Refrigerator() {
		this.ingredients = new HashMap<>();
	}

	public Refrigerator(String name, User user) {
		this.name = name;
		this.user = user;
		this.ingredients = new HashMap<>();
	}

	public Refrigerator(String name, User user, Map<Ingredient, Double> ingredients) {
		this.name = name;
		this.user = user;
		this.ingredients = ingredients;
	}

	@XmlElement(required = true)
	@Id
//	@GeneratedValue(generator = "increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
//	@OneToMany
//	@JoinTable(name = "Refrigerator_ingredient")
//	@MapKeyColumn(name = "ingredients")
	@Transient
	private Map<Ingredient, Double> ingredients;

	public long getId() {
		return id;
	}

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
		return "Refrigerator{" + "name='" + name + '\'' + ", user=" + user + ", ingredients=" + ingredients + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Refrigerator that = (Refrigerator) o;

		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;
		return user != null ? user.equals(that.user) : that.user == null;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (user != null ? user.hashCode() : 0);
		return result;
	}
}
