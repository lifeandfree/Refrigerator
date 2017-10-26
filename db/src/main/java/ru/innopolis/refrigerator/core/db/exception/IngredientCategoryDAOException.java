package ru.innopolis.refrigerator.core.db.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IngredientCategoryDAOException extends Exception {

	private static final Logger logger = LogManager.getLogger(IngredientCategoryDAOException.class.getName());
	/**
	 *
	 */
	public IngredientCategoryDAOException() {
	}

	/**
	 * @param message
	 */
	public IngredientCategoryDAOException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public IngredientCategoryDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public IngredientCategoryDAOException(String message, Throwable cause, boolean enableSuppression,
								  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public IngredientCategoryDAOException(Throwable cause) {
		super(cause);
	}
}
