package ru.innopolis.refrigerator.core.db.dao.refrigerator;

import ru.innopolis.refrigerator.core.db.exception.RefrigeratorDAOException;
import ru.innopolis.refrigerator.core.model.refrigerator.Refrigerator;
import ru.innopolis.refrigerator.core.model.user.User;

public interface SpecialRefrigeratorDAO {

	long getId(String name, long userId) throws RefrigeratorDAOException;

	Refrigerator getRefByUser(User user) throws RefrigeratorDAOException;
}
