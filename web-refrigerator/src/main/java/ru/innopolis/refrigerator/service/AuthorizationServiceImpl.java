package ru.innopolis.refrigerator.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.dao.DaoFactory;
import ru.innopolis.refrigerator.core.db.jdbc.exception.SessionDAOException;
import ru.innopolis.refrigerator.core.db.jdbc.exception.UserDAOException;
import ru.innopolis.refrigerator.core.model.session.Session;
import ru.innopolis.refrigerator.service.exception.AuthorizationServiceImplException;
import ru.innopolis.refrigerator.service.utils.Md5;
import ru.innopolis.refrigerator.service.utils.PasswordEncoder;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationServiceImpl implements AuthorizationService {

	public static final String COOKIE_SID = "sid";
	public static final String COOKIE_UID = "uid";
	/**
	 * Значение userId по умолчанию.
	 */
	public static final String ANONYMOUS = "0";

	private static final Logger logger = LogManager.getLogger(AuthorizationServiceImpl.class.getName());
	@Override
	public Session auth(String login, String password, boolean remember) throws UserDAOException, AuthorizationServiceImplException, SessionDAOException {
		if (login == null || password == null) {
			String msg = "Login or password is null.";
			logger.error(msg);
			throw new AuthorizationServiceImplException(msg);
		}
		long userId = DaoFactory.getInstance().getUserDAO().getUserIdByLoginAndPassword(login, PasswordEncoder.passwordEncode(password));
		if (userId > 0) {
			long currentTimeMillis = System.currentTimeMillis();
			Session session = new Session(
					Md5.getMD5(userId + String.valueOf(currentTimeMillis)),
					DaoFactory.getInstance().getUserDAO().getById(userId), "", 0,
					currentTimeMillis,
					remember);
			DaoFactory.getInstance().getSessionDAO().insertOne(session);
			return session;
		}
		else{
			String msg = "Login or password is not found.";
			logger.error(msg);
			throw new AuthorizationServiceImplException(msg);
		}
	}

	/**
	 * Завершить сеанс работы пользователя с сайтом.
	 *
	 * @return
	 */
	@Override
	public boolean logout(String sid, long uid) throws AuthorizationServiceImplException, SessionDAOException {
		long sessionId = DaoFactory.getInstance().getSessionDAO().findSession(sid, uid);
		if (sessionId > 0)
		{
			if (DaoFactory.getInstance().getSessionDAO().deleteById(sessionId)){
				return true;
			}
			else
			{
				String msg = "No Sessions Found. uid: " + uid + " sid: "+ sid;
				logger.error(msg);
				throw new AuthorizationServiceImplException(msg);
			}
		}
		else
		{
			String msg = "No Sessions Found. uid: " + uid + " sid: "+ sid;
			logger.error(msg);
			throw new AuthorizationServiceImplException(msg);
		}
	}

	/**
	 * Загрузить cookies
	 * @param cookies
	 */
	@Override
	public Map<String, String> loadCookies(Cookie[] cookies) {
		Map<String, String> cookieValues = new HashMap<>();
		if (cookies != null)
			{
				int cookieLength = cookies.length;
				String cookieName, cookieValue;
				for (int i = 0; i < cookieLength; i++)
				{
					Cookie cookie = cookies[i];
					cookieName = cookie.getName();
					cookieValue = cookie.getValue();

					cookieValues.put(cookieName, cookieValue);
				}
			}
				
			if (!cookieValueIsSet(COOKIE_UID, cookieValues))
			{
				cookieValueSet(COOKIE_UID, ANONYMOUS, cookieValues);
			}
			if (!cookieValueIsSet(COOKIE_SID, cookieValues))
			{
				cookieValueSet(COOKIE_SID, "", cookieValues);
			}
		return cookieValues;
	}

	/**
	 * Проверить, установлено ли значение в массиве значений Cookie
	 *
	 * @param cookieName
	 *            Проверяемое значение Cookie
	 * @param cookieValues
	 * @return bool - установлено, false - иначе
	 */
	private boolean cookieValueIsSet(String cookieName, Map<String, String> cookieValues)
	{
		boolean result = false;
		if (cookieValues != null)
		{
			int cookieLength = cookieValues.size();
			for (int i = 0; i < cookieLength; i++)
			{
				if (cookieValues.get(cookieName) != null)
				{
					result = true;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Установить значение cookieValue
	 *
	 * @param cookieName
	 *            имя Cookie
	 * @param cookieValue
	 *            значение Cookie
	 * @param cookieValues
	 * @return true, если значение установлено, иначе false
	 */
	private boolean cookieValueSet(String cookieName, String cookieValue, Map<String, String> cookieValues)
	{
		if (cookieValueIsSet(cookieName, cookieValues))
		{
			cookieValues.remove(cookieName);
		}
		cookieValues.put(cookieName, cookieValue);
		return cookieValues.get(cookieName).equals(cookieValue);
	}

	/**
	 * Получить значение cookie с заданным именем
	 *
	 * @param cookieName
	 *            имя cookie
	 * @return значение куки по имени.
	 */
	public String getCookie(String cookieName, Cookie[] cookies)
	{
		Cookie cookie;
		String result = "";
		if (cookies != null)
		{
			int cookieLength = cookies.length;
			for (int i = 0; i < cookieLength; i++)
			{
				Cookie cookie1 = cookies[i];
				cookie = cookie1;
				if ((cookie.getName()).compareTo(cookieName) == 0)
				{
					if (cookieName.equals(COOKIE_UID) && cookie.getValue().equals(""))
					{
						result = ANONYMOUS;
					}
					else
					{
						result = cookie.getValue();
					}
				}
			}
		}
		if (cookieName.equals(COOKIE_UID) && result.equals(""))
		{
			result = ANONYMOUS;
		}

		return result;
	}

	@Override
	public boolean authByCookies(String sid, long uid) throws AuthorizationServiceImplException, SessionDAOException {
		if (sid == null || uid == 0) {
			String msg = "Not data for autorization.";
			logger.error(msg);
			throw new AuthorizationServiceImplException(msg);
		}
		Session session = DaoFactory.getInstance().getSessionDAO().getSessionBySidAndUid(sid, uid);

		if (session != null) {
			return true;
		}
		else{
			String msg = "Session is not found.";
			logger.error(msg);
			throw new AuthorizationServiceImplException(msg);
		}
	}
}
