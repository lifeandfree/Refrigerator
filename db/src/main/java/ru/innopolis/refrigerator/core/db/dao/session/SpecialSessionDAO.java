package ru.innopolis.refrigerator.core.db.dao.session;

import ru.innopolis.refrigerator.core.db.exception.SessionDAOException;
import ru.innopolis.refrigerator.core.model.session.Session;

public interface SpecialSessionDAO {

	long findSession(String sid, long uid) throws SessionDAOException;

	boolean deleteById(long sessionId) throws SessionDAOException;

	Session getSessionBySidAndUid(String sid, long uid) throws SessionDAOException;

}


