package ru.innopolis.refrigerator.service.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RecipeServiceException extends  ServiceException{
	private static final Logger logger = LogManager.getLogger(RecipeServiceException.class.getName());
	/**
	 *
	 */
	public RecipeServiceException() {
	}

	/**
	 * @param message
	 */
	public RecipeServiceException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RecipeServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RecipeServiceException(String message, Throwable cause, boolean enableSuppression,
											boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public RecipeServiceException(Throwable cause) {
		super(cause);
	}
}
