package ru.innopolis.refrigerator.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.dao.DaoFactory;
import ru.innopolis.refrigerator.core.db.exception.UserDAOException;
import ru.innopolis.refrigerator.core.model.enumcls.Role;
import ru.innopolis.refrigerator.core.model.user.User;
import ru.innopolis.refrigerator.service.exception.RegistrationServiceImplException;
import ru.innopolis.refrigerator.service.utils.PasswordValidator;

import static ru.innopolis.refrigerator.service.utils.PasswordEncoder.passwordEncode;

public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger logger = LogManager.getLogger(RegistrationServiceImpl.class.getName());
	@Override
	public Boolean regUser(String login, String password, Role role, String email) throws UserDAOException {
		if (login == null || password == null || email == null) {
			return false;
		}
		return DaoFactory.getInstance().getUserDAO().createUser(new User(login, passwordEncode(password), Role.user, email));
	}

	@Override
	public boolean checkReg(String login, String email) throws RegistrationServiceImplException {
		String msg;
		if (login == null || email == null) {
			msg = "Login or password is null";
			logger.error(msg);
			throw new RegistrationServiceImplException(msg);
		}

		if (login.length() < 8){
			msg = "Login must be at least 8 characters.";
			logger.error(msg);
			throw new RegistrationServiceImplException(msg);
		}

		if ( EmailValidator.getInstance().isValid(email)){
			msg = "Email is not valid.";
			logger.error(msg);
			throw new RegistrationServiceImplException(msg);
		}

		try {
			return DaoFactory.getInstance().getUserDAO().checkDublicateUser(login, email);
		}
		catch (UserDAOException e) {
			msg = "Error in request";
			logger.error(msg);
			throw new RegistrationServiceImplException(msg);
		}
	}

	@Override
	public boolean checkPassword(String password) throws RegistrationServiceImplException {
		String msg;
		if (password.length() < 8) {
			msg = "Password must be at least 8 characters.";
			logger.error(msg);
			throw new RegistrationServiceImplException(msg);
		}
		PasswordValidator passwordValidator = new PasswordValidator();
		return passwordValidator.validate(password);
	}
}