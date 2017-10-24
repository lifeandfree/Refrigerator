package ru.innopolis.refrigerator.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.refrigerator.core.model.enumcls.Role;
import ru.innopolis.refrigerator.service.RegistrationService;
import ru.innopolis.refrigerator.service.RegistrationServiceImpl;
import ru.innopolis.refrigerator.service.exception.RegistrationServiceImplException;

@Controller
public class RegistrationController {

	private static final Logger logger = LogManager.getLogger(RegistrationController.class.getName());
	private static RegistrationService rs = new RegistrationServiceImpl();

	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public ModelAndView renderRegistrationForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msgerror", "");
		modelAndView.setViewName("reg");
		return modelAndView;
	}

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public ModelAndView registration(@RequestParam(name = "username", required = true) String login,
							  @RequestParam(name = "user_password", required = true) String password,
							  @RequestParam(name = "email", required = false) String email
	) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:reg");

		logger.info("Register with login: " + login + " email: " + email);
		String msg = "";
		try {
			if (rs.checkReg(login, email)){
				msg = "Such login or user already exists.";
				logger.error(msg);
				modelAndView.addObject("msgerror", msg);

			}
		}
		catch (RegistrationServiceImplException e) {
			logger.error(e.getMessage(), e.toString());
			modelAndView.addObject("msgerror", e.getMessage());
		}

		try {
			if (!rs.checkPassword(password)){
				msg = "Password is not valid";
				logger.error(msg);
				modelAndView.addObject("msgerror", msg);
			}
		}
		catch (RegistrationServiceImplException e) {
			logger.error(e.getMessage(), e.toString());
			modelAndView.addObject("msgerror", e.getMessage());
		}
		if (rs.regUser(login, password, Role.user, email))
		{
			ModelAndView modelAndViewAuth = new ModelAndView("redirect:auth");
			msg = "Registration completed successfully.";
			logger.info(msg);
			modelAndViewAuth.addObject("msginfo", msg);
			return  modelAndViewAuth;
		}
		else {
			msg = "I can not";
			logger.error(msg);
			modelAndView.addObject("msgerror", msg);
		}
		return modelAndView;
	}

}
