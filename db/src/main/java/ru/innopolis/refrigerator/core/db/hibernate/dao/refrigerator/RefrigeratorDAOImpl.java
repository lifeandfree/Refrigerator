package ru.innopolis.refrigerator.core.db.hibernate.dao.refrigerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.dao.refrigerator.RefrigeratorDAO;
import ru.innopolis.refrigerator.core.db.exception.RefrigeratorDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.refrigerator.Refrigerator;

public class RefrigeratorDAOImpl extends ElementDAOImpl<Refrigerator> implements RefrigeratorDAO<Refrigerator> {

	private static final Logger logger = LogManager.getLogger(RefrigeratorDAOImpl.class.getName());
	public RefrigeratorDAOImpl() {
		super(Refrigerator.class);
	}

	public RefrigeratorDAOImpl(Class<Refrigerator> content) {
		super(content);
	}


	@Override
	public long getId(String name, long userId) throws RefrigeratorDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new RefrigeratorDAOException(msg);
	}
}