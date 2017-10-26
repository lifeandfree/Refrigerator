package ru.innopolis.refrigerator.core.db.dao.refrigerator;

import ru.innopolis.refrigerator.core.db.exception.RefrigeratorDAOException;

public interface SpecialRefrigeratorDAO {

	long getId(String name, long userId) throws RefrigeratorDAOException;
}
