package ru.innopolis.refrigerator.core.db.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CookingMethodDAOException extends Exception {

	private static final Logger logger = LogManager.getLogger(CookingMethodDAOException.class.getName());
	/**
	 *
	 */
	public CookingMethodDAOException() {
	}

	/**
	 * @param message
	 */
	public CookingMethodDAOException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CookingMethodDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CookingMethodDAOException(String message, Throwable cause, boolean enableSuppression,
										  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public CookingMethodDAOException(Throwable cause) {
		super(cause);
	}
}
