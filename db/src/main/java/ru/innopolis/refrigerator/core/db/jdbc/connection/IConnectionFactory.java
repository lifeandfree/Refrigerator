package ru.innopolis.refrigerator.core.db.jdbc.connection;

import java.sql.Connection;

public interface IConnectionFactory {
	public Connection getConnection(String url, String user, String password);
	public Connection getConnection();
}
