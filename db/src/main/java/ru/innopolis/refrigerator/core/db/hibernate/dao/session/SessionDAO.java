package ru.innopolis.refrigerator.core.db.hibernate.dao.session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.session.Session;

public class SessionDAO extends ElementDAOImpl<Session> {

	private static final Logger logger = LogManager.getLogger(Session.class.getName());
	public SessionDAO() {
		super(Session.class);
	}

	public SessionDAO(Class<Session> content) {
		super(content);
	}


}