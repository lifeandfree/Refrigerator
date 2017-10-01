package ru.innopolis.refrigerator.core.model;

public class User {

	/**
	 * Логин пользователя
	 */
	private String username;

	/**
	 * Сформированное хэш-значение пароля для хранения в базе
	 */
	private String password;

	/**
	 * Email пользователя
	 */
	private String email;
}
