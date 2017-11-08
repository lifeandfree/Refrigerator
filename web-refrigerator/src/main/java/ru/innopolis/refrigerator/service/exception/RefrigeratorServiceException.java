package ru.innopolis.refrigerator.service.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RefrigeratorServiceException extends ServiceException {

	private static final Logger logger = LogManager.getLogger(RefrigeratorServiceException.class.getName());
	/**
	 *
	 */
	public RefrigeratorServiceException() {
	}

	/**
	 * @param message
	 */
	public RefrigeratorServiceException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RefrigeratorServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RefrigeratorServiceException(String message, Throwable cause, boolean enableSuppression,
											boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public RefrigeratorServiceException(Throwable cause) {
		super(cause);
	}
}
