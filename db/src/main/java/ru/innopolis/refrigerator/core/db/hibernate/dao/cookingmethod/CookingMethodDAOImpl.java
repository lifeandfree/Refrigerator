package ru.innopolis.refrigerator.core.db.hibernate.dao.cookingmethod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import ru.innopolis.refrigerator.core.db.dao.cookingmethod.CookingMethodDAO;
import ru.innopolis.refrigerator.core.db.exception.CookingMethodDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;

import java.util.List;

public class CookingMethodDAOImpl extends ElementDAOImpl<CookingMethod> implements CookingMethodDAO<CookingMethod>{

	private static final Logger logger = LogManager.getLogger(CookingMethodDAOImpl.class.getName());
	private SessionFactory sessionFactory;

	public CookingMethodDAOImpl() {
		super(CookingMethod.class);
	}

	public CookingMethodDAOImpl(Class<CookingMethod> content) {
		super(content);
	}

	@Override
	public long getId(CookingMethod cookingMethod) throws CookingMethodDAOException {

		Session session = null;

		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(CookingMethod.class);
			criteria.add(Restrictions.eq("name", cookingMethod.getName()));
			List<CookingMethod> cookingMethods = criteria.getExecutableCriteria(session).setMaxResults(1).list();
			transaction.commit();
			if (cookingMethods != null && cookingMethods.size() > 0) {
				cookingMethod = cookingMethods.get(0);
			}
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new CookingMethodDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		if (cookingMethod == null){
			return 0;
		}
		return cookingMethod.getId();
	}

	@Override
	public CookingMethod getByName(String name) throws CookingMethodDAOException {
		Session session = null;
		CookingMethod cookingMethod = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(CookingMethod.class);
			criteria.add(Restrictions.eq("name", name));
			List<CookingMethod> cookingMethods = criteria.getExecutableCriteria(session).setMaxResults(1).list();
			transaction.commit();
			if (cookingMethods != null && cookingMethods.size() > 0) {
				cookingMethod = cookingMethods.get(0);
			}
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new CookingMethodDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return cookingMethod;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		super.setSessionFactory(sessionFactory);
	}
}