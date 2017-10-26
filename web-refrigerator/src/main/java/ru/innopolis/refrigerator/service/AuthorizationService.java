package ru.innopolis.refrigerator.service;

import ru.innopolis.refrigerator.core.db.exception.SessionDAOException;
import ru.innopolis.refrigerator.core.db.exception.UserDAOException;
import ru.innopolis.refrigerator.core.model.session.Session;
import ru.innopolis.refrigerator.service.exception.AuthorizationServiceImplException;

import javax.servlet.http.Cookie;
import java.util.Map;

public interface AuthorizationService {

	Session auth(String login, String password, boolean remember) throws UserDAOException, AuthorizationServiceImplException, SessionDAOException;

	boolean logout(String sid, long uid) throws AuthorizationServiceImplException, SessionDAOException;

	public Map<String, String> loadCookies(Cookie[] cookies);

	public String getCookie(String cookieName, Cookie[] cookies);

	boolean authByCookies(String sid, long uid) throws AuthorizationServiceImplException, SessionDAOException;
}

