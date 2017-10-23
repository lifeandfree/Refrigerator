package ru.innopolis.refrigerator.core.model.user;

import org.hibernate.annotations.GenericGenerator;
import ru.innopolis.refrigerator.core.model.enumcls.Role;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@Entity
@Table(name = "\"User\"")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", propOrder = {"id", "username", "password", "email", "role"})
@XmlRootElement(name = "User")
public class User implements Serializable {

	private static final long serialVersionUID = 8304111827557668640L;

	public User() {
	}

	public User(String username, String password, Role role, String email) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
	}


	public User(String username, String password, Role role, String email, long id) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
		this.id = id;
	}


	@Id
//	@GeneratedValue(generator = "increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	@XmlElement(required = true)
	private long id;

	/**
	 * Логин пользователя
	 */
	@Column(name = "username", nullable = false, length = 255, unique = true)
	@XmlElement(required = true)
	private String username;

	/**
	 * Сформированное хэш-значение пароля для хранения в базе
	 */
	@Column(name = "password", nullable = false, length = 100)
	@XmlElement(required = true)
	private String password;

	/**
	 *
	 */
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	@XmlElement(required = true)
	private Role role;

	/**
	 * Email пользователя
	 */
	@Column(name = "email", nullable = false, length = 255, unique = true)
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", role=" + role + ", email='" + email + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		User user = (User) o;

		if (username != null ? !username.equals(user.username) : user.username != null)
			return false;
		if (password != null ? !password.equals(user.password) : user.password != null)
			return false;
		if (role != user.role)
			return false;
		return email != null ? email.equals(user.email) : user.email == null;
	}

	@Override
	public int hashCode() {
		int result = username != null ? username.hashCode() : 0;
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		return result;
	}
}
