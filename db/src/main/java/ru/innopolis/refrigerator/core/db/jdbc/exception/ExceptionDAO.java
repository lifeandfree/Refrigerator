package ru.innopolis.refrigerator.core.db.jdbc.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExceptionDAO extends Exception{

	private static final Logger logger = LogManager.getLogger(ExceptionDAO.class.getName());
	private String errorCode;
	private String errorMessage;

	public ExceptionDAO() {
		super();
	}

	public ExceptionDAO(String errorMessage) {
		super(errorMessage);
	}

	public ExceptionDAO(String errorCode, String errorMessage) {
		super(errorCode + ": " + errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public ExceptionDAO(String errorMessage, Throwable rootCause) {

		super(errorMessage, rootCause);
	}

	/**
	 * @param errorMessage
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ExceptionDAO(String errorMessage, Throwable cause, boolean enableSuppression,
							boolean writableStackTrace) {
		super(errorMessage, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public ExceptionDAO(Throwable cause) {
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
