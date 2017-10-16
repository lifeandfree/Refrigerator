package ru.innopolis.refrigerator.servlet;

import ru.innopolis.refrigerator.service.AuthorizationService;
import ru.innopolis.refrigerator.service.AuthorizationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
	private static AuthorizationService as = new AuthorizationServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("psw");
		if (as.auth(login, password)) {
			req.getSession().setAttribute("isAuth", true);
//            req.getRequestDispatcher("/hello").forward(req, resp);
			req.getRequestDispatcher("/refrigerator").forward(req, resp);
		} else {
			getServletContext().getRequestDispatcher("/").forward(req, resp);
		}
	}
}