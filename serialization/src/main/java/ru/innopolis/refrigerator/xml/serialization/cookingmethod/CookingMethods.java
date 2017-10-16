package ru.innopolis.refrigerator.xml.serialization.cookingmethod;

import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CookingMethods", propOrder = { "cookingMethods"
})
@XmlRootElement(name = "CookingMethods")
public class CookingMethods {

	public CookingMethods() {
		this.cookingMethods = new ArrayList<>();
	}

	@XmlElement(required = true)
	private List<CookingMethod> cookingMethods;

	@Override
	public String toString() {
		return "CookingMethods{" + "cookingMethods=" + this.cookingMethods + '}';
	}

	public List<CookingMethod> getCookingMethods() {
		return this.cookingMethods;
	}

	public void setCookingMethods(List<CookingMethod> cookingMethods) {
		this.cookingMethods = cookingMethods;
	}
}
