package ru.innopolis.refrigerator.core.db.hibernate.dao.refrigerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.refrigerator.Refrigerator;

public class RefrigeratorDAO extends ElementDAOImpl<Refrigerator> {

	private static final Logger logger = LogManager.getLogger(RefrigeratorDAO.class.getName());
	public RefrigeratorDAO() {
		super(Refrigerator.class);
	}

	public RefrigeratorDAO(Class<Refrigerator> content) {
		super(content);
	}


}