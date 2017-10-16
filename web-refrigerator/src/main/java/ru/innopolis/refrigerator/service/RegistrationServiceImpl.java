package ru.innopolis.refrigerator.service;

import ru.innopolis.refrigerator.core.db.jdbc.dao.user.UserDAO;
import ru.innopolis.refrigerator.core.model.enumcls.Role;
import ru.innopolis.refrigerator.core.model.user.User;

import static ru.innopolis.refrigerator.service.PasswordEncoder.encode;

public class RegistrationServiceImpl implements RegistrationService {
	private static UserDAO userDAO = new UserDAO();

	@Override
	public Boolean regUser(String login, String password, Role role, String email) {
		if (login == null || password == null || email == null) {
			return false;
		}
		return userDAO.createUser(new User(login, encode(password), Role.user, email));
	}
}