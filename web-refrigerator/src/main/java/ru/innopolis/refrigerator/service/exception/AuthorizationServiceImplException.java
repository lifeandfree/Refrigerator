package ru.innopolis.refrigerator.service.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthorizationServiceImplException extends ServiceException {

	private static final Logger logger = LogManager.getLogger(AuthorizationServiceImplException.class.getName());
	/**
	 *
	 */
	public AuthorizationServiceImplException() {
	}

	/**
	 * @param message
	 */
	public AuthorizationServiceImplException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AuthorizationServiceImplException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AuthorizationServiceImplException(String message, Throwable cause, boolean enableSuppression,
											boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public AuthorizationServiceImplException(Throwable cause) {
		super(cause);
	}
}
