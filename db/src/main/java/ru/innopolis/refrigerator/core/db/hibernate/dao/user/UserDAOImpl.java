package ru.innopolis.refrigerator.core.db.hibernate.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.dao.user.UserDAO;
import ru.innopolis.refrigerator.core.db.exception.UserDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.user.User;

public class UserDAOImpl extends ElementDAOImpl<User> implements UserDAO<User> {

	private static final Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());
	public UserDAOImpl() {
		super(User.class);
	}
	public UserDAOImpl(Class<User> content) {
		super(content);
	}

	@Override
	public long getId(User user) throws UserDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new UserDAOException(msg);
	}

	@Override
	public Boolean createUser(User user) throws UserDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new UserDAOException(msg);
	}

	@Override
	public boolean checkDublicateUser(String login, String email) throws UserDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new UserDAOException(msg);
	}

	@Override
	public User getUserByLoginAndPassword(String login, String psw) throws UserDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new UserDAOException(msg);
	}

	@Override
	public long getUserIdByLoginAndPassword(String login, String psw) throws UserDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new UserDAOException(msg);
	}
}
