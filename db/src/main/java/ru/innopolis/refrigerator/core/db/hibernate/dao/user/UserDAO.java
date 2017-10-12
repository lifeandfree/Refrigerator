package ru.innopolis.refrigerator.core.db.hibernate.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.user.User;

public class UserDAO extends ElementDAOImpl<User> {

	private static final Logger logger = LogManager.getLogger(UserDAO.class.getName());
	public UserDAO() {
		super(User.class);
	}

	public UserDAO(Class<User> content) {
		super(content);
	}

}
