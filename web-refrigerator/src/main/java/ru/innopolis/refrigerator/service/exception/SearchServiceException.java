package ru.innopolis.refrigerator.service.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchServiceException extends ServiceException {

	private static final Logger logger = LogManager.getLogger(SearchServiceException.class.getName());
	/**
	 *
	 */
	public SearchServiceException() {
	}

	/**
	 * @param message
	 */
	public SearchServiceException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SearchServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SearchServiceException(String message, Throwable cause, boolean enableSuppression,
										boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public SearchServiceException(Throwable cause) {
		super(cause);
	}
}
