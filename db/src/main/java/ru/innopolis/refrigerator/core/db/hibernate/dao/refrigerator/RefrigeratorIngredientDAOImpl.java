package ru.innopolis.refrigerator.core.db.hibernate.dao.refrigerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import ru.innopolis.refrigerator.core.db.dao.refrigeratoringredient.RefrigeratorIngredientDAO;
import ru.innopolis.refrigerator.core.db.exception.RefrigeratorDAOException;
import ru.innopolis.refrigerator.core.db.exception.RefrigeratorIngredientDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.refrigerator.Refrigerator;
import ru.innopolis.refrigerator.core.model.refrigerator.RefrigeratorIngredient;

import java.util.List;

public class RefrigeratorIngredientDAOImpl extends ElementDAOImpl<RefrigeratorIngredient> implements RefrigeratorIngredientDAO<RefrigeratorIngredient> {

	private static final Logger logger = LogManager.getLogger(RefrigeratorIngredientDAOImpl.class.getName());
	public RefrigeratorIngredientDAOImpl() {
		super(RefrigeratorIngredient.class);
	}
	private SessionFactory sessionFactory;

	public RefrigeratorIngredientDAOImpl(Class<RefrigeratorIngredient> content) {
		super(content);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public List<RefrigeratorIngredient> getRefIngrByRef(Refrigerator refrigerator) throws RefrigeratorIngredientDAOException {

		Session session = null;
		List<RefrigeratorIngredient> refrigeratorIngredients;

		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(RefrigeratorIngredient.class);
			criteria.add(Restrictions.eq("refrigerator", refrigerator));
			refrigeratorIngredients = criteria.getExecutableCriteria(session).list();
			transaction.commit();
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new RefrigeratorIngredientDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return refrigeratorIngredients;
	}
}
