package ru.innopolis.refrigerator.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.exception.RecipeDAOException;
import ru.innopolis.refrigerator.service.AuthorizationService;
import ru.innopolis.refrigerator.service.AuthorizationServiceImpl;
import ru.innopolis.refrigerator.servlet.view.RecipesView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Recipes extends HttpServlet {
	private static AuthorizationService as = new AuthorizationServiceImpl();
	private static final Logger logger = LogManager.getLogger(Recipes.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long uid = Long.valueOf(as.getCookie(AuthorizationServiceImpl.COOKIE_UID, request.getCookies()));
		RecipesView recipesView = new RecipesView(uid);
		try {
			request.setAttribute("recipes", recipesView.rendering());
		}
		catch (RecipeDAOException e) {
			logger.error(e.getMessage(), e.toString());
			request.setAttribute("recipes", e.getLocalizedMessage());
		}
		getServletContext().getRequestDispatcher("/recipes.jsp").forward(request, response);
	}
}
