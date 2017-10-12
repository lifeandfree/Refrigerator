package ru.innopolis.refrigerator.core.model.cookingmethod;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CookingMethod", propOrder = {})
@Entity
@Table(name = "\"CookingMethod\"")
public class CookingMethod implements Serializable, ICookingMethod {

	public CookingMethod(ICookingMethod iCookingMethod) {
		this.iCookingMethod = iCookingMethod;
	}

	public CookingMethod() {
	}

	public CookingMethod(String name) {
		this.name = name;
	}

	@Transient
	ICookingMethod iCookingMethod;

	@XmlElement(required = true)
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@XmlElement(required = true)
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name + " " + iCookingMethod.getName();
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CookingMethod{" + "id=" + id + ", name='" + name + '\'' + '}';
	}


}
