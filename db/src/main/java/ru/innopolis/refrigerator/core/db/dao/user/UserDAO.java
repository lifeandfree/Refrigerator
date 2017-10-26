package ru.innopolis.refrigerator.core.db.dao.user;

import ru.innopolis.refrigerator.core.db.exception.UserDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAO;
import ru.innopolis.refrigerator.core.model.user.User;

public interface UserDAO<E> extends ElementDAO<E>, SpecialUserDAO  {

	long getId(User user) throws UserDAOException ;

	Boolean createUser(User user) throws UserDAOException;

	boolean checkDublicateUser(String login, String email) throws UserDAOException;

	User getUserByLoginAndPassword(String login, String psw) throws UserDAOException;

	long getUserIdByLoginAndPassword(String login, String psw) throws UserDAOException;
}
