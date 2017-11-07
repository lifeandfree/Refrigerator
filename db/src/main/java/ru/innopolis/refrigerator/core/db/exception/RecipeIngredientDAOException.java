package ru.innopolis.refrigerator.core.db.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RecipeIngredientDAOException  extends ExceptionDAO{

	private static final Logger logger = LogManager.getLogger(RecipeIngredientDAOException.class.getName());
	/**
	 *
	 */
	public RecipeIngredientDAOException() {
	}

	/**
	 * @param message
	 */
	public RecipeIngredientDAOException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RecipeIngredientDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RecipeIngredientDAOException(String message, Throwable cause, boolean enableSuppression,
							  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public RecipeIngredientDAOException(Throwable cause) {
		super(cause);
	}
}
