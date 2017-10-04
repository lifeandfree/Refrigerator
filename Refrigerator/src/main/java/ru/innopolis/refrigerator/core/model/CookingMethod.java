package ru.innopolis.refrigerator.core.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CookingMethod", propOrder = {})
public class CookingMethod {

	public CookingMethod() {
	}

	public CookingMethod(String name) {
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

	@Override
	public String toString() {
		return "CookingMethod{" + "id=" + id + ", name='" + name + '\'' + '}';
	}
}
