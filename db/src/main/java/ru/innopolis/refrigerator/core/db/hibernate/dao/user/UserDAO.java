package ru.innopolis.refrigerator.core.db.hibernate.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.hibernate.dao.DaoFactory;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.Role;
import ru.innopolis.refrigerator.core.model.User;

public class UserDAO extends ElementDAOImpl<User> {
	public static void main(String[] args) {
		logger.error("fffff");
		User user = new User("tmp1", "pass1", Role.user, "ggg@ggg.ru");
		DaoFactory.getInstance().getUserDAO().addElement(user);

	}

	private static final Logger logger = LogManager.getLogger(UserDAO.class.getName());
	public UserDAO() {
		super(User.class);
	}

	public UserDAO(Class<User> content) {
		super(content);
	}


}
