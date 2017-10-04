package ru.innopolis.refrigerator.xml.serialization.refrigerator;

import ru.innopolis.refrigerator.core.Constants;
import ru.innopolis.refrigerator.core.model.Ingredient;
import ru.innopolis.refrigerator.core.model.IngredientCategory;
import ru.innopolis.refrigerator.core.model.Refrigerator;
import ru.innopolis.refrigerator.core.model.User;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefrigeratorJaxbParserMain {

	public static void main(String[] args) {

		IngredientCategory ingredientCategory = new IngredientCategory("овощь");
		List<IngredientCategory> ingredientCategorys = new ArrayList<IngredientCategory>();
		ingredientCategorys.add(ingredientCategory);

		Ingredient ingredient = new Ingredient("Морковь", "гр", ingredientCategorys);
		Map<Ingredient, Double> ingredientStringMap = new HashMap<>();
		ingredientStringMap.put(ingredient, 100d);

		User user = new User("user1", "pass1", (byte) 1, "emael1");

		Refrigerator refrigerator = new Refrigerator("ref1", user, ingredientStringMap);
		List<Refrigerator> refrigeratorsList = new ArrayList<>();
		refrigeratorsList.add(refrigerator);
		Refrigerators refrigerators = new Refrigerators();
		refrigerators.setRefrigerators(refrigeratorsList);

		RefrigeratorJaxbParser parser = new RefrigeratorJaxbParser();
		try {
			parser.saveObject(new File(Constants.REFRIGERATORS_XML_FILENAME), refrigerators);
		}
		catch (JAXBException e) {
			e.printStackTrace();
		}

		try {
			Refrigerators parseRefregirator = (Refrigerators) parser.getObject(new File(Constants.REFRIGERATORS_XML_FILENAME), Refrigerators.class);
			System.out.println(parseRefregirator.toString());
		}
		catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
