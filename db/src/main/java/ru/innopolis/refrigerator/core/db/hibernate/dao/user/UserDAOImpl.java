package ru.innopolis.refrigerator.core.db.hibernate.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import ru.innopolis.refrigerator.core.db.dao.user.UserDAO;
import ru.innopolis.refrigerator.core.db.exception.UserDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.user.User;

import java.util.List;

public class UserDAOImpl extends ElementDAOImpl<User> implements UserDAO<User> {

	private static final Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());

	private SessionFactory sessionFactory;

	public UserDAOImpl() {
		super(User.class);
		super.setSessionFactory(sessionFactory);
	}

	public UserDAOImpl(Class<User> content) {
		super(content);
		super.setSessionFactory(sessionFactory);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public long getId(User user) throws UserDAOException {

		Session session = null;

		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
			criteria.add(Restrictions.eq("email", user.getEmail())).add(Restrictions.eq("username", user.getUsername()));
			List<User> users = criteria.getExecutableCriteria(session).setMaxResults(1).list();
			transaction.commit();
			if (users != null && users.size() > 0) {
				user = users.get(0);
			}
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new UserDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		if (user == null) {
			return 0;
		}
		return user.getId();
	}

	@Override
	public Boolean createUser(User user) throws UserDAOException {
		user = add(user);
		if (user != null){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean checkDublicateUser(String login, String email) throws UserDAOException {
		User user = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
			criteria.add(Restrictions.eq("email", email)).add(Restrictions.eq("username", login));
			List<User> users = criteria.getExecutableCriteria(session).setMaxResults(1).list();
			transaction.commit();
			if (users != null && users.size() > 0) {
				user = users.get(0);
			}
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new UserDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		if (user == null) {
			return false;
		}
		return true;
	}

	@Override
	public User getUserByLoginAndPassword(String login, String psw) throws UserDAOException {
		User user = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
			criteria.add(Restrictions.eq("password", psw)).add(Restrictions.eq("username", login));
			List<User> users = criteria.getExecutableCriteria(session).setMaxResults(1).list();
			transaction.commit();
			if (users != null && users.size() > 0) {
				user = users.get(0);
			}
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new UserDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return user;
	}

	@Override
	public long getUserIdByLoginAndPassword(String login, String psw) throws UserDAOException {
		User user  = getUserByLoginAndPassword(login, psw);
		if (user == null)
			return 0;
		return user.getId();
	}

}
