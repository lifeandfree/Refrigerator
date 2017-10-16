package ru.innopolis.refrigerator.core.logger;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

/**
 * @author \author Andrey A. Kovyazin, Sitis Ltd.<br>
 *         \date 19 окт. 2016 г.
 */
public class LogHandler {

	private org.apache.logging.log4j.Logger logger;

	/**
	 *
	 */
	public LogHandler() {
		setLogger(LogManager.getLogger());
	}

	/**
	 * DEBUG-massage
	 *
	 * @param message
	 *            одна, несколько или ни одной строки
	 */
	public final void debug(String... message) {
		StringBuilder msg = new StringBuilder();
		for (String tmpMsg : message) {
			msg.append(tmpMsg);
		}
		getLogger().debug(getMethod() + msg);
	}

	/**
	 * ERROR-massage
	 *
	 * @param message
	 *            одна, несколько или ни одной строки
	 */
	public final void error(String... message) {
		StringBuilder msg = new StringBuilder();
		for (String tmpMsg : message) {
			msg.append(tmpMsg);
		}
		getLogger().error(getMethod() + msg);
	}

	/**
	 * FATAL-massage
	 *
	 * @param message
	 *            одна, несколько или ни одной строки
	 */
	public final void fatal(String... message) {
		StringBuilder msg = new StringBuilder();
		for (String tmpMsg : message) {
			msg.append(tmpMsg);
		}
		getLogger().fatal(getMethod() + msg);
	}

	/**
	 * @return the logger
	 */
	private Logger getLogger() {
		return logger;
	}

	/**
	 * Получить имя вызвавшего метода
	 *
	 * @return
	 */
	private String getMethod() {
		String result = null;
		StackTraceElement stack = new Exception().getStackTrace()[2];
		result = stack.getClassName() + "." + stack.getMethodName() + "(): ";
		return result;
	}

	/**
	 * INFO-massage
	 *
	 * @param message
	 *            одна, несколько или ни одной строки
	 */
	public final void info(String... message) {
		StringBuilder msg = new StringBuilder();
		for (String tmpMsg : message) {
			msg.append(tmpMsg);
		}
		getLogger().info(getMethod() + msg);
	}

	/**
	 * @param logger
	 *            the logger to set
	 */
	private void setLogger(org.apache.logging.log4j.Logger logger) {
		this.logger = logger;
	}

	/**
	 * WARN-massage
	 *
	 * @param message
	 *            одна, несколько или ни одной строки
	 */
	public final void warn(String... message) {
		StringBuilder msg = new StringBuilder();
		for (String tmpMsg : message) {
			msg.append(tmpMsg);
		}
		getLogger().warn(getMethod() + msg);
	}
}