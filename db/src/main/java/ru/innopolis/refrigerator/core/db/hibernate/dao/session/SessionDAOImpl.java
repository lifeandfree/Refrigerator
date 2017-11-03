package ru.innopolis.refrigerator.core.db.hibernate.dao.session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import ru.innopolis.refrigerator.core.db.DaoFactory;
import ru.innopolis.refrigerator.core.db.dao.session.SessionDAO;
import ru.innopolis.refrigerator.core.db.exception.SessionDAOException;
import ru.innopolis.refrigerator.core.db.exception.UserDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.session.Session;
import sun.util.resources.cldr.LocaleNames;

import java.util.List;

public class SessionDAOImpl extends ElementDAOImpl<Session> implements SessionDAO<Session> {

	private static final Logger logger = LogManager.getLogger(Session.class.getName());
	private SessionFactory sessionFactory;
	public SessionDAOImpl() {
		super(Session.class);
	}

	public SessionDAOImpl(Class<Session> content) {
		super(content);
	}


	@Override
	public long findSession(String sid, long uid) throws SessionDAOException {
		Session session = getSessionBySidAndUid(sid, uid);
		if (session == null){
			return 0;
		}
		return session.getId();
	}

	@Override
	public boolean deleteById(long sessionId) throws SessionDAOException {
		Session session = getById(sessionId);
		delete(session);
		return true;
	}

	@Override
	public Session getSessionBySidAndUid(String sid, long uid) throws SessionDAOException {

		Session sessionUser = null;

		org.hibernate.Session session =null;

		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(Session.class);
			criteria.add(Restrictions.eq("sessionId", sid));
			criteria.add(Restrictions.eq("user", DaoFactory.getInstance().getUserDAO().getById(uid)));
			List<Session> sessionUsers = criteria.getExecutableCriteria(session).setMaxResults(1).list();
			transaction.commit();
			if (sessionUsers != null && sessionUsers.size() > 0) {
				sessionUser = sessionUsers.get(0);
			}
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new SessionDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return sessionUser;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		super.setSessionFactory(sessionFactory);
	}
}