package ru.innopolis.refrigerator.core.db.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RefrigeratorIngredientDAOException extends Exception {

	private static final Logger logger = LogManager.getLogger(RefrigeratorIngredientDAOException.class.getName());
	/**
	 *
	 */
	public RefrigeratorIngredientDAOException() {
	}

	/**
	 * @param message
	 */
	public RefrigeratorIngredientDAOException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RefrigeratorIngredientDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RefrigeratorIngredientDAOException(String message, Throwable cause, boolean enableSuppression,
							boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public RefrigeratorIngredientDAOException(Throwable cause) {
		super(cause);
	}
}
