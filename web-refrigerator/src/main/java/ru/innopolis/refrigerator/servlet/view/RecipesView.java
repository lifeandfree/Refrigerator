package ru.innopolis.refrigerator.servlet.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.DaoFactory;
import ru.innopolis.refrigerator.core.db.exception.RecipeDAOException;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.service.RecipeService;
import ru.innopolis.refrigerator.service.RecipesService;
import ru.innopolis.refrigerator.service.RecipesServiceImpl;

import java.util.List;

public class RecipesView {

	private static final Logger logger = LogManager.getLogger(RecipesView.class.getName());
	private static RecipesService rc = new RecipesServiceImpl();
	private Long uid;

	public RecipesView(Long uid) {
		this.uid = uid;
	}

	public RecipesView() {
	}

	public String rendering() throws RecipeDAOException {

		List<Recipe> recipes = rc.getRecipe(uid);

		StringBuilder stringBuilder = new StringBuilder();
		int i = 0;
		for (Recipe recipe : recipes) {
			i++;
			stringBuilder.append("<tr><th scope=\"row\">").
					append(i).append(" </th><td>").
					append(recipe.getName()).append("</td><td>").
					append(recipe.getComplexity()).append("</td><td>").
					append(recipe.getTime()).append("</td>").
					append("<td><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Edit\"><button class=\"btn btn-primary btn-xs\" data-title=\"Edit\" data-toggle=\"modal\" onclick=\"window.location.href='recipe/edit?id="+ recipe.getId() +"'\" data-target=\"#edit\" ><span class=\"glyphicon glyphicon-pencil\"></span></button></p></td>\n").
					append("<td><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Delete\"><button class=\"btn btn-danger btn-xs\" data-title=\"Delete\" data-toggle=\"modal\" onclick=\"window.location.href='recipe/delete?id="+ recipe.getId() +"'\" data-target=\"#delete\" ><span class=\"glyphicon glyphicon-trash\"></span></button></p></td></tr>");

		}
		return stringBuilder.toString();
	}
}
