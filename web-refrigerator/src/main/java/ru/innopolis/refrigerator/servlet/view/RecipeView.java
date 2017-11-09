package ru.innopolis.refrigerator.servlet.view;

import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;

import java.util.Map;

public class RecipeView {
	public String getRenderIngredient(Map<Ingredient, Double> ingredients) {
		StringBuilder stringBuilder = new StringBuilder();

		int i = 0;
		if (ingredients == null || ingredients.size() == 0){
			stringBuilder.append("<tr id='addr0'><td>1</td><td><input type=\"text\" name='ingredient0'  placeholder='Ingredient' class=\"form-control\"/></td><td><input type=\"text\" name='quantity0' placeholder='Quantity' class=\"form-control\"/></td><td><input type=\"text\" name='dimension0' placeholder='Dimension' class=\"form-control\"/></td></tr><tr id='addr1'></tr>");
		}
		else {
			for (Map.Entry<Ingredient, Double> entry : ingredients.entrySet()) {
				Double value = (Double) entry.getValue();
				Ingredient ingredient = entry.getKey();
				stringBuilder.append("<tr id='addr"+i+"'><td>" + (i+1) + "</td><td><input type=\"text\" name='ingredient"+i+"' value=\""+ingredient.getName()+"\"  placeholder='Ingredient' class=\"form-control\"/></td><td><input type=\"text\" value=\""+value+"\" name='quantity"+i+"' placeholder='Quantity' class=\"form-control\" /></td><td><input type=\"text\" name='dimension"+i+"' value=\""+ingredient.getDimension()+"\" placeholder='Dimension' class=\"form-control\"/>\n</td></tr><tr id='addr"+(i+1)+"'></tr>");
			}
		}

		return stringBuilder.toString();
	}

	public String getNotVisibleRenderIngredient(Map<Ingredient, Double> ingredients) {
		StringBuilder stringBuilder = new StringBuilder();

		int i = 0;
		if (ingredients == null || ingredients.size() == 0){
			stringBuilder.append("<tr id='addr0'><td>1</td><td><input type=\"text\" name='ingredient0'  placeholder='Ingredient' class=\"form-control\"/ disabled></td><td><input type=\"text\" name='quantity0' placeholder='Quantity' class=\"form-control\"/></td><td><input type=\"text\" name='dimension0' placeholder='Dimension' class=\"form-control\"/></td></tr><tr id='addr1'></tr>");
		}
		else {
			for (Map.Entry<Ingredient, Double> entry : ingredients.entrySet()) {
				Double value = (Double) entry.getValue();
				Ingredient ingredient = entry.getKey();
				stringBuilder.append("<tr id='addr"+i+"'><td>" + (i+1) + "</td><td><input type=\"text\" name='ingredient"+i+"' value=\""+ingredient.getName()+"\"  placeholder='Ingredient' class=\"form-control\" disabled/></td><td><input type=\"text\" value=\""+value+"\" name='quantity"+i+"' placeholder='Quantity' class=\"form-control\" disabled/></td><td><input type=\"text\" name='dimension"+i+"' value=\""+ingredient.getDimension()+"\" placeholder='Dimension' class=\"form-control\" disabled/>\n</td></tr><tr id='addr"+(i+1)+"'></tr>");
			}
		}

		return stringBuilder.toString();
	}
}
