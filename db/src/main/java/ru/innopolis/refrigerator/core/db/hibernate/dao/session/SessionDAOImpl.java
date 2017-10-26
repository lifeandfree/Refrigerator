package ru.innopolis.refrigerator.core.db.hibernate.dao.session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.dao.session.SessionDAO;
import ru.innopolis.refrigerator.core.db.exception.SessionDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.session.Session;

public class SessionDAOImpl extends ElementDAOImpl<Session> implements SessionDAO<Session> {

	private static final Logger logger = LogManager.getLogger(Session.class.getName());
	public SessionDAOImpl() {
		super(Session.class);
	}

	public SessionDAOImpl(Class<Session> content) {
		super(content);
	}


	@Override
	public long findSession(String sid, long uid) throws SessionDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new SessionDAOException(msg);
	}

	@Override
	public boolean deleteById(long sessionId) throws SessionDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new SessionDAOException(msg);
	}

	@Override
	public Session getSessionBySidAndUid(String sid, long uid) throws SessionDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new SessionDAOException(msg);
	}
}