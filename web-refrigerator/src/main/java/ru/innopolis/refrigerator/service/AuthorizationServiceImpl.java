package ru.innopolis.refrigerator.service;

import ru.innopolis.refrigerator.core.db.jdbc.dao.user.UserDAO;

public class AuthorizationServiceImpl implements AuthorizationService {
	private static UserDAO userDAO = new UserDAO();

	@Override
	public Boolean auth(String login, String password) {
		if (login == null || password == null) {
			return false;
		}
//		if (userDAO.getUserByLoginAndPassword(login, PasswordEncoder.encode(password)) != null) {
			return true;
//		}
//		return false;
	}
}
