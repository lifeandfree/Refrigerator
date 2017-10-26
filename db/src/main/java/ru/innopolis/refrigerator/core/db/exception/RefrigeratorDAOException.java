package ru.innopolis.refrigerator.core.db.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RefrigeratorDAOException extends Exception {

	private static final Logger logger = LogManager.getLogger(RefrigeratorDAOException.class.getName());
	/**
	 *
	 */
	public RefrigeratorDAOException() {
	}

	/**
	 * @param message
	 */
	public RefrigeratorDAOException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RefrigeratorDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RefrigeratorDAOException(String message, Throwable cause, boolean enableSuppression,
											  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public RefrigeratorDAOException(Throwable cause) {
		super(cause);
	}
}
