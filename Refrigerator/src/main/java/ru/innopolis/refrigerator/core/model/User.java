package ru.innopolis.refrigerator.core.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", propOrder = {"id", "username", "password", "email"})
@XmlRootElement(name = "User")
public class User {

	public User() {
	}

	public User(String username, String password, byte role, String email) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
	}

	private long id;

	/**
	 * Логин пользователя
	 */
	@XmlElement(required = true)
	private String username;

	/**
	 * Сформированное хэш-значение пароля для хранения в базе
	 */
	@XmlElement(required = true)
	private String password;

	/**
	 *
	 */
	@XmlElement(required = true)
	private byte role;

	/**
	 * Email пользователя
	 */
	@XmlElement(required = true)
	private String email;


	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
