package ru.innopolis.refrigerator.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.exception.SessionDAOException;
import ru.innopolis.refrigerator.core.db.jdbc.exception.UserDAOException;
import ru.innopolis.refrigerator.core.model.session.Session;
import ru.innopolis.refrigerator.core.model.user.User;
import ru.innopolis.refrigerator.service.AuthorizationService;
import ru.innopolis.refrigerator.service.AuthorizationServiceImpl;
import ru.innopolis.refrigerator.service.exception.AuthorizationServiceImplException;
import ru.innopolis.refrigerator.service.utils.PasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet  {

	private static final Logger logger = LogManager.getLogger(LoginServlet.class.getName());
	private static AuthorizationService as = new AuthorizationServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("psw");
		String logout = req.getParameter("logout");

		User user = new User();
		if (password == null) {
			user.setPassword(null);
		} else
		{
			user.setPassword(PasswordEncoder.passwordEncode(password));
		}
		user.setUsername(login);

		boolean remember;
		String sid = as.getCookie(AuthorizationServiceImpl.COOKIE_SID, req.getCookies());
		long uid = Long.parseLong(as.getCookie(AuthorizationServiceImpl.COOKIE_UID, req.getCookies()));
		if (logout != null && logout.length() != 0)
		{
			try {
				as.logout(sid, uid);
			}
			catch (AuthorizationServiceImplException e) {
				logger.error(e.getMessage(), e.toString());
				req.setAttribute("msgerror", e.getLocalizedMessage());
				getServletContext().getRequestDispatcher("/auth.jsp").forward(req, resp);
			}
			catch (SessionDAOException e) {
				logger.error(e.getMessage(), e.toString());
				req.setAttribute("msgerror", e.getLocalizedMessage());
				getServletContext().getRequestDispatcher("/auth.jsp").forward(req, resp);
			}
		}
		else
		{
			if (user.getUsername() != null && user.getUsername().length() != 0)
			{
				String rememberMe = req.getParameter("rm");
				if (rememberMe == null || rememberMe.length() == 0)
				{
					remember = false;
				}
				else
				{
					remember = true;
				}
				// Авторизация пользователя по логину и паролю, если приняты
				try {
					Session session = as.auth(login, password, remember);
					if (session != null) {
						resp.addCookie(new Cookie("sid", session.getSessionId()));
						resp.addCookie(new Cookie("uid", String.valueOf(session.getUser().getId())));
						resp.sendRedirect("/web-refrigerator/recipes");

					} else {
						String msg = "Need authorization";
						logger.error(msg);
						req.setAttribute("msgerror", msg);
						getServletContext().getRequestDispatcher("/auth.jsp").forward(req, resp);
					}
				}
				catch (AuthorizationServiceImplException e) {
					logger.error(e.getMessage(), e.toString());
					req.setAttribute("msgerror", e.getLocalizedMessage());
					getServletContext().getRequestDispatcher("/auth.jsp").forward(req, resp);
				}
				catch (SessionDAOException e) {
					logger.error(e.getMessage(), e.toString());
					req.setAttribute("msgerror", e.getLocalizedMessage());
					getServletContext().getRequestDispatcher("/auth.jsp").forward(req, resp);
				}
				catch (UserDAOException e) {
					logger.error(e.getMessage(), e.toString());
					req.setAttribute("msgerror", e.getLocalizedMessage());
					getServletContext().getRequestDispatcher("/auth.jsp").forward(req, resp);
				}

			}
			else
			{
				// Восстановление авторизации по идентификаторам пользователя и сеанса, сохраненным в Cookies
				try {
					if (as.authByCookies(sid, uid)){
						resp.sendRedirect("/web-refrigerator/recipes");
					}
					else{
						String msg = "Need authorization";
						logger.error(msg);
						req.setAttribute("msgerror", msg);
						getServletContext().getRequestDispatcher("/auth.jsp").forward(req, resp);
					}
				}
				catch (AuthorizationServiceImplException e) {
					logger.error(e.getMessage(), e.toString());
					req.setAttribute("msgerror", e.getLocalizedMessage());
					getServletContext().getRequestDispatcher("/auth.jsp").forward(req, resp);
				}
				catch (SessionDAOException e) {
					logger.error(e.getMessage(), e.toString());
					req.setAttribute("msgerror", e.getLocalizedMessage());
					getServletContext().getRequestDispatcher("/auth.jsp").forward(req, resp);
				}
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/auth.jsp").forward(req, resp);
	}
}