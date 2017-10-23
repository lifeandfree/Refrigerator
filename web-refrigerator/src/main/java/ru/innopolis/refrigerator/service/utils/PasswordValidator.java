package ru.innopolis.refrigerator.service.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

	private static final Logger logger = LogManager.getLogger(PasswordValidator.class.getName());
	private Pattern pattern;
	private Matcher matcher;

	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	public PasswordValidator() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

	public boolean validate(final String password) {
		logger.info("PasswordValidator " + password);
		matcher = pattern.matcher(password);
		return matcher.matches();
	}
}