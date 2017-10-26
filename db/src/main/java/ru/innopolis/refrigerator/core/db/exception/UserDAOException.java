package ru.innopolis.refrigerator.core.db.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDAOException extends ExceptionDAO {

	private static final Logger logger = LogManager.getLogger(UserDAOException.class.getName());
	/**
	 *
	 */
	public UserDAOException() {
	}

	/**
	 * @param message
	 */
	public UserDAOException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UserDAOException(String message, Throwable cause, boolean enableSuppression,
							   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public UserDAOException(Throwable cause) {
		super(cause);
	}
}
