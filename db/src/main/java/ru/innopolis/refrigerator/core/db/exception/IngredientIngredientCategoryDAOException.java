package ru.innopolis.refrigerator.core.db.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IngredientIngredientCategoryDAOException extends Exception {

	private static final Logger logger = LogManager.getLogger(IngredientIngredientCategoryDAOException.class.getName());
	/**
	 *
	 */
	public IngredientIngredientCategoryDAOException() {
	}

	/**
	 * @param message
	 */
	public IngredientIngredientCategoryDAOException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public IngredientIngredientCategoryDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public IngredientIngredientCategoryDAOException(String message, Throwable cause, boolean enableSuppression,
									  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public IngredientIngredientCategoryDAOException(Throwable cause) {
		super(cause);
	}
}
