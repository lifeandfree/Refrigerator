package ru.innopolis.refrigerator.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.logger.LogHandler;
import ru.innopolis.refrigerator.core.model.enumcls.Role;
import ru.innopolis.refrigerator.service.RegistrationService;
import ru.innopolis.refrigerator.service.RegistrationServiceImpl;
import ru.innopolis.refrigerator.service.exception.RegistrationServiceImplException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

	private static final Logger logger = LogManager.getLogger(RegisterServlet.class.getName());
	private static RegistrationService rs = new RegistrationServiceImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("username");
		String password = request.getParameter("user_password");
		String email = request.getParameter("email");

		logger.info("Register with login: " + login + " email: " + email);
		String msg = "";
		try {
			if (rs.checkReg(login, email)){
				msg = "Such login or user already exists.";
				logger.error(msg);
				request.setAttribute("msgerror", msg);
				getServletContext().getRequestDispatcher("/reg.jsp").forward(request, response);
			}
		}
		catch (RegistrationServiceImplException e) {
			logger.error(e.getMessage(), e.toString());
			request.setAttribute("msgerror", e.getMessage());
			getServletContext().getRequestDispatcher("/reg.jsp").forward(request, response);
		}

		try {
			if (!rs.checkPassword(password)){
				msg = "Password is not valid";
				logger.error(msg);
				request.setAttribute("msgerror",  msg);
				getServletContext().getRequestDispatcher("/reg.jsp").forward(request, response);
			}
		}
		catch (RegistrationServiceImplException e) {
			logger.error(msg);
			request.setAttribute("msgerror", msg);
			getServletContext().getRequestDispatcher("/reg.jsp").forward(request, response);
		}
		if (rs.regUser(login, password, Role.user, email))
		{
			response.sendRedirect("auth");
			//getServletContext().getRequestDispatcher("/auth").(request, response);
		}
		else {
			msg = "";
			logger.error(msg);
			request.setAttribute("msgerror", msg);
			getServletContext().getRequestDispatcher("/reg.jsp").forward(request, response);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("msgerror", "");
		getServletContext().getRequestDispatcher("/reg.jsp").forward(request, response);
	}
}
