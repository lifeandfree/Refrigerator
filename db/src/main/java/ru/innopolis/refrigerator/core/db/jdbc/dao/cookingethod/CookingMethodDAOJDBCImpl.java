package ru.innopolis.refrigerator.core.db.jdbc.dao.cookingethod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.dao.cookingmethod.CookingMethodDAO;
import ru.innopolis.refrigerator.core.db.exception.IngredientCategoryDAOException;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactory;
import ru.innopolis.refrigerator.core.db.exception.CookingMethodDAOException;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CookingMethodDAOJDBCImpl implements CookingMethodDAO<CookingMethod> {

	private static final Logger logger = LogManager.getLogger(CookingMethodDAOJDBCImpl.class.getName());
	private static ConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	@Override
	public List<CookingMethod> getAll() throws CookingMethodDAOException {
		List<CookingMethod> cookingMethodList = new ArrayList<>();
		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM \"CookingMethod\"");

			while (resultSet.next()) {
				CookingMethod cookingMethod = new CookingMethod(resultSet.getString("name"));
				cookingMethodList.add(cookingMethod);
			}
		} catch (SQLException e) {
			logger.error("I can not get of all items to the database" + e.toString());
			throw new CookingMethodDAOException();
		}
		return cookingMethodList;
	}

	@Override
	public CookingMethod getById(Long elId)  throws CookingMethodDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new CookingMethodDAOException(msg);
	}

	@Override
	public CookingMethod update(CookingMethod el) throws CookingMethodDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new CookingMethodDAOException(msg);
	}


	@Override
	public CookingMethod add(CookingMethod cookingMethod) throws CookingMethodDAOException {
		String sql = "INSERT INTO \"CookingMethod\" (name) VALUES(?)";
		try {
			PreparedStatement ps = connection.getConnection().prepareStatement(sql);
			ps.clearParameters();
			ps.setString(1, cookingMethod.getName());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new CookingMethodDAOException();
		}
		return cookingMethod;
	}

	@Override
	public CookingMethod delete(CookingMethod el) throws CookingMethodDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new CookingMethodDAOException(msg);
	}

	@Override
	public Collection<CookingMethod> addAll(Collection<CookingMethod> cookingMethods) throws CookingMethodDAOException  {
		String sql = "INSERT INTO \"CookingMethod\" (name) VALUES(?)";
		try {
			PreparedStatement ps = connection.getConnection().prepareStatement(sql);
			for (CookingMethod cookingMethod : cookingMethods) {
				ps.clearParameters();
				ps.setString(1, cookingMethod.getName());
				ps.addBatch();
			}
			ps.executeBatch();
		}
		catch (SQLException e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new CookingMethodDAOException();
		}
		return cookingMethods;
	}

	@Override
	public long getId(CookingMethod cookingMethod) throws CookingMethodDAOException {
		long cookId = 0;
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM \"CookingMethod\" WHERE name=?");
			statement.setString(1, cookingMethod.getName());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				cookId = resultSet.getLong("id");
			}
		}
		catch (SQLException e) {
			logger.error("I can not get an item to the database" + e.toString());
			throw new CookingMethodDAOException();
		}
		return cookId;
	}

	@Override
	public CookingMethod getByName(String name) throws CookingMethodDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new CookingMethodDAOException(msg);
	}


}