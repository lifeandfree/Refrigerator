package ru.innopolis.refrigerator.core.db.hibernate.dao.ingredient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import ru.innopolis.refrigerator.core.db.dao.ingredient.IngredientDAO;
import ru.innopolis.refrigerator.core.db.exception.CookingMethodDAOException;
import ru.innopolis.refrigerator.core.db.exception.IngredientDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;

import java.util.List;

public class IngredientDAOImpl extends ElementDAOImpl<Ingredient>  implements IngredientDAO<Ingredient> {

	private static final Logger logger = LogManager.getLogger(IngredientDAOImpl.class.getName());
	private SessionFactory sessionFactory;

	public IngredientDAOImpl() {
		super(Ingredient.class);
	}

	public IngredientDAOImpl(Class<Ingredient> content) {
		super(content);
	}


	@Override
	public Long getId(Ingredient ingredient) throws IngredientDAOException {

		Session session = null;

		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(Ingredient.class);
			criteria.add(Restrictions.eq("name", ingredient.getName())).add(Restrictions.eq("dimension", ingredient.getDimension()));
			List<Ingredient> ingredients = criteria.getExecutableCriteria(session).setMaxResults(1).list();
			transaction.commit();
			if (ingredients != null && ingredients.size() > 0) {
				ingredient = ingredients.get(0);
			}
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new IngredientDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		if (ingredient == null){
			return 0L;
		}
		return ingredient.getId();
	}

	@Override
	public Ingredient getIngredientByNameDimension(String name, String dimension) throws IngredientDAOException {

		Session session = null;
		Ingredient ingredient = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(Ingredient.class);

			criteria.add(Restrictions.eq("name", name)).add(Restrictions.eq("dimension", dimension));
			List<Ingredient> ingredients = criteria.getExecutableCriteria(session).setMaxResults(1).list();
			transaction.commit();
			if (ingredients != null && ingredients.size() > 0) {
				ingredient = ingredients.get(0);
			}
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new IngredientDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return ingredient;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		super.setSessionFactory(sessionFactory);
	}
}