package ru.innopolis.refrigerator.service.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrationServiceImplException extends ServiceException {

	private static final Logger logger = LogManager.getLogger(RegistrationServiceImplException.class.getName());
	/**
	 *
	 */
	public RegistrationServiceImplException() {
	}

	/**
	 * @param message
	 */
	public RegistrationServiceImplException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RegistrationServiceImplException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RegistrationServiceImplException(String message, Throwable cause, boolean enableSuppression,
								 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public RegistrationServiceImplException(Throwable cause) {
		super(cause);
	}


}
