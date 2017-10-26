package ru.innopolis.refrigerator.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.refrigerator.core.db.jdbc.exception.SessionDAOException;
import ru.innopolis.refrigerator.core.db.jdbc.exception.UserDAOException;
import ru.innopolis.refrigerator.core.model.session.Session;
import ru.innopolis.refrigerator.core.model.user.User;
import ru.innopolis.refrigerator.service.AuthorizationService;
import ru.innopolis.refrigerator.service.AuthorizationServiceImpl;
import ru.innopolis.refrigerator.service.exception.AuthorizationServiceImplException;
import ru.innopolis.refrigerator.service.utils.PasswordEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class.getName());
	private static AuthorizationService as = new AuthorizationServiceImpl();

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public ModelAndView renderLoginForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msgerror", "");
		modelAndView.addObject("msginfo", "");
		modelAndView.setViewName("auth");
		return modelAndView;
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(name = "login", required = false) String login,
							  @RequestParam(name = "psw", required = false) String password,
							  @RequestParam(name = "logout", required = false) String logout,
							  @RequestParam(name = "rm", required = false) String rememberMe,
							  @CookieValue(value = AuthorizationServiceImpl.COOKIE_SID, required = false) Cookie sid,
							  @CookieValue(value = AuthorizationServiceImpl.COOKIE_UID, required = false) Cookie usid,
							  HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:auth");

		User user = new User();
		if (password == null) {
			user.setPassword(null);
		} else {
			user.setPassword(PasswordEncoder.passwordEncode(password));
		}
		user.setUsername(login);

		boolean remember;
		long uid = 0L;
		if (usid != null) {
			uid = Long.parseLong(usid.getValue());
		}

		if (logout != null && logout.length() != 0) {
			try {
				as.logout(sid.getValue(), uid);
			}
			catch (AuthorizationServiceImplException e) {
				logger.error(e.getMessage(), e.toString());
				modelAndView.addObject("msginfo", "");
				modelAndView.addObject("msgerror", e.getMessage());
			}
			catch (SessionDAOException e) {
				logger.error(e.getMessage(), e.toString());
				modelAndView.addObject("msginfo", "");
				modelAndView.addObject("msgerror", e.getMessage());
			}
		} else {
			if (user.getUsername() != null && user.getUsername().length() != 0) {
				if (rememberMe == null || rememberMe.length() == 0) {
					remember = false;
				} else {
					remember = true;
				}
				// Авторизация пользователя по логину и паролю, если приняты
				try {
					Session session = null;
					try {
						session = as.auth(login, password, Boolean.valueOf(remember));
					}
					catch (UserDAOException e) {
						logger.error(e.getMessage(), e.toString());
						modelAndView.addObject("msginfo", "");
						modelAndView.addObject("msgerror", e.getMessage());
					}
					if (session != null) {
						response.addCookie(new Cookie("sid", session.getSessionId()));
						response.addCookie(new Cookie("uid", String.valueOf(session.getUser().getId())));
						ModelAndView modelAndViewRec = new ModelAndView("redirect:recipes");
						return modelAndViewRec;

					} else {
						String msg = "Need authorization";
						logger.error(msg);
						modelAndView.addObject("msginfo", "");
						modelAndView.addObject("msgerror", msg);
					}
				}
				catch (AuthorizationServiceImplException e) {
					logger.error(e.getMessage(), e.toString());
					modelAndView.addObject("msginfo", "");
					modelAndView.addObject("msgerror", e.getMessage());
				}
				catch (SessionDAOException e) {
					logger.error(e.getMessage(), e.toString());
					modelAndView.addObject("msginfo", "");
					modelAndView.addObject("msgerror", e.getMessage());
				}
			} else {
				// Восстановление авторизации по идентификаторам пользователя и сеанса, сохраненным в Cookies
				try {
					if (as.authByCookies(sid.getValue(), uid)) {
						ModelAndView modelAndViewRec = new ModelAndView("redirect:recipes");
						return modelAndViewRec;
					} else {
						String msg = "Need authorization";
						logger.error(msg);
						modelAndView.addObject("msginfo", "");
						modelAndView.addObject("msgerror", msg);
					}
				}
				catch (AuthorizationServiceImplException e) {
					logger.error(e.getMessage(), e.toString());
					modelAndView.addObject("msginfo", "");
					modelAndView.addObject("msgerror", e.getMessage());
				}
				catch (SessionDAOException e) {
					logger.error(e.getMessage(), e.toString());
					modelAndView.addObject("msginfo", "");
					modelAndView.addObject("msgerror", e.getMessage());
				}
			}
		}
		return modelAndView;
	}

	@RequestMapping(value = "/auth/logout", method = RequestMethod.GET)
	public ModelAndView logout(@CookieValue(value = AuthorizationServiceImpl.COOKIE_SID, required = false) Cookie sid, @CookieValue(value = AuthorizationServiceImpl.COOKIE_UID, required = false) Cookie usid, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/auth");

		long uid = 0L;
		if (usid != null) {
			uid = Long.parseLong(usid.getValue());
		}

		try {
			as.logout(sid.getValue(), uid);
			response.addCookie(new Cookie("sid", ""));
			response.addCookie(new Cookie("uid", "0"));
		}
		catch (AuthorizationServiceImplException e) {
			logger.error(e.getMessage(), e.toString());
			modelAndView.addObject("msginfo", "");
			modelAndView.addObject("msgerror", e.getMessage());
		}
		catch (SessionDAOException e) {
			logger.error(e.getMessage(), e.toString());
			modelAndView.addObject("msginfo", "");
			modelAndView.addObject("msgerror", e.getMessage());
		}
		return modelAndView;
	}
}

