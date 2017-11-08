package ru.innopolis.refrigerator.core.db.hibernate.dao.refrigerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import ru.innopolis.refrigerator.core.db.dao.refrigerator.RefrigeratorDAO;
import ru.innopolis.refrigerator.core.db.exception.IngredientDAOException;
import ru.innopolis.refrigerator.core.db.exception.RefrigeratorDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.refrigerator.Refrigerator;
import ru.innopolis.refrigerator.core.model.user.User;

import java.util.List;

public class RefrigeratorDAOImpl extends ElementDAOImpl<Refrigerator> implements RefrigeratorDAO<Refrigerator> {

	private static final Logger logger = LogManager.getLogger(RefrigeratorDAOImpl.class.getName());
	public RefrigeratorDAOImpl() {
		super(Refrigerator.class);
	}
	private SessionFactory sessionFactory;

	public RefrigeratorDAOImpl(Class<Refrigerator> content) {
		super(content);
	}

	@Override
	public long getId(String name, long userId) throws RefrigeratorDAOException {

		Refrigerator refrigerator = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(Refrigerator.class);
			criteria.add(Restrictions.eq("name", name)).add(Restrictions.eq("user_id", userId));
			List<Refrigerator> refrigerators = criteria.getExecutableCriteria(session).setMaxResults(1).list();
			transaction.commit();
			if (refrigerators != null && refrigerators.size() > 0) {
				refrigerator = refrigerators.get(0);
			}
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new RefrigeratorDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		if (refrigerator == null){
			return 0L;
		}
		return refrigerator.getId();
	}

	@Override
	public Refrigerator getRefByUser(User user) throws RefrigeratorDAOException {
		Refrigerator refrigerator = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(Refrigerator.class);
			criteria.add(Restrictions.eq("user", user));
			List<Refrigerator> refrigerators = criteria.getExecutableCriteria(session).setMaxResults(1).list();
			transaction.commit();
			if (refrigerators != null && refrigerators.size() > 0) {
				refrigerator = refrigerators.get(0);
			}
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new RefrigeratorDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return refrigerator;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		super.setSessionFactory(sessionFactory);
	}
}