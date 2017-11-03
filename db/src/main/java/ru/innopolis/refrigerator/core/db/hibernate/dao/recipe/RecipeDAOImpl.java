package ru.innopolis.refrigerator.core.db.hibernate.dao.recipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import ru.innopolis.refrigerator.core.db.DaoFactory;
import ru.innopolis.refrigerator.core.db.dao.recipe.RecipeDAO;
import ru.innopolis.refrigerator.core.db.exception.RecipeDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.core.model.user.User;

import java.util.List;

public class RecipeDAOImpl extends ElementDAOImpl<Recipe> implements RecipeDAO<Recipe> {

	private static final Logger logger = LogManager.getLogger(RecipeDAOImpl.class.getName());
	private SessionFactory sessionFactory;

	public RecipeDAOImpl() {
		super(Recipe.class);
	}

	public RecipeDAOImpl(Class<Recipe> content) {
		super(content);
	}


	@Override
	public Long getId(Recipe recipe, long userId, long cookingMethodId) throws RecipeDAOException {

		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(Recipe.class);
			criteria.add(Restrictions.eq("user", DaoFactory.getInstance().getUserDAO().getById(userId))).
					add(Restrictions.eq("cookingMethod", DaoFactory.getInstance().getCookingMethodDAO().getById(cookingMethodId))).
					add(Restrictions.eq("name", recipe.getName()));
			List<Recipe> recipes = criteria.getExecutableCriteria(session).setMaxResults(1).list();
			transaction.commit();
			if (recipes != null && recipes.size() > 0) {
				recipe = recipes.get(0);
			}
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new RecipeDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return recipe.getId();
	}

	@Override
	public List<Recipe> getAllByUserId(Long uid) throws RecipeDAOException {
		Session session = null;
		List<Recipe> recipes;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(Recipe.class);
			criteria.add(Restrictions.eq("user", DaoFactory.getInstance().getUserDAO().getById(uid)));
			recipes = criteria.getExecutableCriteria(session).setMaxResults(1).list();
			transaction.commit();
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new RecipeDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return recipes;
	}

	@Override
	public void add(Recipe recipe, long userId, long cookingMethodId) throws RecipeDAOException {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(Recipe.class);
			recipe.setUser((User) DaoFactory.getInstance().getUserDAO().getById(userId));
			recipe.setCookingMethod((CookingMethod) DaoFactory.getInstance().getCookingMethodDAO().getById(cookingMethodId));
			List<Recipe> recipes = criteria.getExecutableCriteria(session).setMaxResults(1).list();
			transaction.commit();
			add(recipe);
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new RecipeDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		super.setSessionFactory(sessionFactory);
	}
}