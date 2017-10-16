package ru.innopolis.refrigerator.xml.serialization.user;

import ru.innopolis.refrigerator.core.model.user.User;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Users", propOrder = {"users"})
@XmlRootElement(name = "Users")
public class Users {

	public Users() {
		this.users = new ArrayList<>();
	}

	public Users(List<User> users) {
		this.users = users;
	}

	@XmlElement(required = true)
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Users{" + "users=" + users + '}';
	}
}
