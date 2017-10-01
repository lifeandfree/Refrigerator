package ru.innopolis.refrigerator.core.model;

import java.util.List;
import java.util.Set;

public class Recipe {

	private long id;
	private String name;
	private Set<Ingredient> ingredients;// название / количество.
	private String Instructions;
	private String photo;
	private List<RecipeCategory> recipeCategorys; //вид блида.
	private CookingMethod cookingMethod; // способ приготовления



}
