package ru.innopolis.refrigerator.service.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceException extends Exception{

	private static final Logger logger = LogManager.getLogger(ServiceException.class.getName());
	private String errorCode;
	private String errorMessage;

	public ServiceException() {
		super();
	}

	public ServiceException(String errorMessage) {
		super(errorMessage);
	}

	public ServiceException(String errorCode, String errorMessage) {
		super(errorCode + ": " + errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public ServiceException(String errorMessage, Throwable rootCause) {

		super(errorMessage, rootCause);
	}

	/**
	 * @param errorMessage
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ServiceException(String errorMessage, Throwable cause, boolean enableSuppression,
							boolean writableStackTrace) {
		super(errorMessage, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
