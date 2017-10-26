package ru.innopolis.refrigerator.core.db.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RecipeDAOException extends Exception {

	private static final Logger logger = LogManager.getLogger(RecipeDAOException.class.getName());
	/**
	 *
	 */
	public RecipeDAOException() {
	}

	/**
	 * @param message
	 */
	public RecipeDAOException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RecipeDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RecipeDAOException(String message, Throwable cause, boolean enableSuppression,
											  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public RecipeDAOException(Throwable cause) {
		super(cause);
	}
}
