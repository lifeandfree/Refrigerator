package ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.DbUtil;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactory;
import ru.innopolis.refrigerator.core.logger.LogHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс для создания подключения к БД PostgreSQL.
 */
public class ConnectionFactoryPostgreSQL implements ConnectionFactory
{
	public static void main(String[] args) {
		ConnectionFactoryPostgreSQL.getInstance().getConnection("","","");
	}
	
	public static final String DRIVER_CLASS_POSTGRESQL = "org.postgresql.Driver";

	private static ConnectionFactoryPostgreSQL instance;
	//private static ConnectionFactoryPostgreSQL instance = new ConnectionFactoryPostgreSQL(); //TODO

	private LogHandler log = new LogHandler();

	@Override
	public Connection getConnection(String url, String user, String password)
	{
		return instance.createConnection(url, user, password);
	}

	@Override
	public Connection getConnection() {
		return instance.createConnection("jdbc:postgresql://localhost:5432/refrigerator4", "refrigerator", "123");
	}

	public static synchronized ConnectionFactoryPostgreSQL getInstance()
	{
		if (instance == null)
		{
			instance = new ConnectionFactoryPostgreSQL();
		}

		return instance;
	}

	private ConnectionFactoryPostgreSQL()
	{
		try
		{
			Class.forName(DRIVER_CLASS_POSTGRESQL);
		}
		catch (ClassNotFoundException e)
		{
			log.error("Unable to load " + DRIVER_CLASS_POSTGRESQL, e.toString());
		}
		catch (Exception e)
		{
			log.error("Unable to load " + DRIVER_CLASS_POSTGRESQL + " Message: " + e.getMessage(), e.toString());
		}
	}

	private Connection createConnection(String url, String user, String password)
	{
		Connection connection = null;
		try
		{
			connection = DriverManager.getConnection(url, user, password);
			DbUtil.checkWarnings(connection.getWarnings());
			log.info("Connect to Database.");
		}
		catch (SQLException e)
		{
			log.error(
					"Unable to Connect to Database." + " SQLState: " + e.getSQLState() + " Message: " + e.getMessage()
							+ " Vendor code: " + e.getErrorCode(), e.toString());
		}
		catch (Exception e)
		{
			log.error("Unable to Connect to Database." + " Message: " + e.getMessage(), e.toString());
		}
		return connection;
	}

}