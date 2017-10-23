package ru.innopolis.refrigerator.core.db.jdbc.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionDAOException extends ExceptionDAO {

	private static final Logger logger = LogManager.getLogger(SessionDAOException.class.getName());
	/**
	 *
	 */
	public SessionDAOException() {
	}

	/**
	 * @param message
	 */
	public SessionDAOException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SessionDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SessionDAOException(String message, Throwable cause, boolean enableSuppression,
											boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public SessionDAOException(Throwable cause) {
		super(cause);
	}
}
