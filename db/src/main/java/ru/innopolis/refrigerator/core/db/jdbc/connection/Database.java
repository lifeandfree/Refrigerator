package ru.innopolis.refrigerator.core.db.jdbc.connection;

public class Database
{

	private String dbLogin;
	private String dbName;
	private String dbPasswd;
	private String dbServer;
	private String dbUrl;
	private int port;

	public Database(String dbPasswd, String dbServer, String dbUrl) {
		this.dbPasswd = dbPasswd;
		this.dbServer = dbServer;
		this.dbUrl = dbUrl;
	}

	public Database(String dbLogin, String dbName, String dbPasswd, String dbServer, int port) {
		this.dbLogin = dbLogin;
		this.dbName = dbName;
		this.dbPasswd = dbPasswd;
		this.dbServer = dbServer;
		this.port = port;
	}

	public String getDbLogin() {
		return dbLogin;
	}

	public void setDbLogin(String dbLogin) {
		this.dbLogin = dbLogin;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbPasswd() {
		return dbPasswd;
	}

	public void setDbPasswd(String dbPasswd) {
		this.dbPasswd = dbPasswd;
	}

	public String getDbServer() {
		return dbServer;
	}

	public void setDbServer(String dbServer) {
		this.dbServer = dbServer;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
