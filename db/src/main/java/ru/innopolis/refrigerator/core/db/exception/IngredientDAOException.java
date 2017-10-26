package ru.innopolis.refrigerator.core.db.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IngredientDAOException extends Exception {

	private static final Logger logger = LogManager.getLogger(IngredientDAOException.class.getName());
	/**
	 *
	 */
	public IngredientDAOException() {
	}

	/**
	 * @param message
	 */
	public IngredientDAOException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public IngredientDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public IngredientDAOException(String message, Throwable cause, boolean enableSuppression,
													boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public IngredientDAOException(Throwable cause) {
		super(cause);
	}
}
