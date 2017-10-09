package ru.innopolis.refrigerator.core.db.jdbc.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * Дополнительные методы работы с БД
 */
public class DbUtil
{
	private static final Logger log = LogManager.getLogger(ConnectionFactoryPostgreSQL.class.getName());
	/**
	 * Проверяет имеется исключение типа WARNING.
	 *
	 * @param warn
	 *            - исключение.
	 */
	public static void checkWarnings(SQLWarning warn)
	{
		if (warn != null)
		{
			while (warn != null)
			{
				log.warn(
						"Unable to Connect to Database." + " SQLState: " + warn.getSQLState() + " Message: "
								+ warn.getMessage() + " Vendor code: " + warn.getErrorCode(), warn);
				warn.getNextException();
			}
		}
	}

	/**
	 * Закрывает соединение с БД.
	 *
	 * @param connection
	 *            - соединение с БД.
	 */
	public static void close(Connection connection)
	{
		if (connection != null)
		{
			try
			{
				connection.close();
				log.info("Connection closed");
			}
			catch (SQLException e)
			{
				log.error(
						"Unable to close connection." + " SQLState: " + e.getSQLState() + " Message: " + e.getMessage()
								+ " Vendor code: " + e.getErrorCode(), e);
				e.getNextException();
			}
			catch (Exception e)
			{
				log.error("Unable to close connection" + " Message: " + e.getMessage(), e);
			}
		}
	}

	/**
	 * Закрывает обработку результатов с запроса с БД.
	 *
	 * @param resultSet
	 */
	public static void close(ResultSet resultSet)
	{
		if (resultSet != null)
		{
			try
			{
				resultSet.close();
				log.info("resultSet completed");
			}
			catch (SQLException e)
			{
				log.error(
						"Unable to close resultSet." + " SQLState: " + e.getSQLState() + " Message: " + e.getMessage()
								+ " Vendor code: " + e.getErrorCode(), e);
				e.getNextException();
			}
			catch (Exception e)
			{
				log.error("Unable to close resultSet" + " Message: " + e.getMessage(), e);
			}
		}
	}

	/**
	 * Закрывает statement.
	 *
	 * @param statement
	 */
	public static void close(Statement statement)
	{
		if (statement != null)
		{
			try
			{
				statement.close();
				log.info("Request completed");
			}
			catch (SQLException e)
			{
				log.error(
						"Unable to close statement." + " SQLState: " + e.getSQLState() + " Message: " + e.getMessage()
								+ " Vendor code: " + e.getErrorCode(), e);
				e.getNextException();
			}
			catch (Exception e)
			{
				log.error("Unable to close statement" + " Message: " + e.getMessage(), e);
			}
		}
	}

}
