package ru.innopolis.refrigerator.core.db.hibernate.dao.recipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import ru.innopolis.refrigerator.core.db.DaoFactory;
import ru.innopolis.refrigerator.core.db.dao.recipeingredient.RecipeIngredientDAO;
import ru.innopolis.refrigerator.core.db.exception.RecipeIngredientDAOException;
import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAOImpl;
import ru.innopolis.refrigerator.core.model.recipe.Recipe;
import ru.innopolis.refrigerator.core.model.recipe.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;

public class RecipeIngredientDAOImpl extends ElementDAOImpl<RecipeIngredient> implements RecipeIngredientDAO<RecipeIngredient> {

	private static final Logger logger = LogManager.getLogger(RecipeIngredientDAOImpl.class.getName());
	private SessionFactory sessionFactory;

	public RecipeIngredientDAOImpl() {
		super(RecipeIngredient.class);
	}

	public RecipeIngredientDAOImpl(Class<RecipeIngredient> content) {
		super(content);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public boolean deleteAllByRecipe(Recipe recipe) throws Exception {
		Session session = null;
		List<RecipeIngredient> recipeIngredients= new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(RecipeIngredient.class);
			criteria.add(Restrictions.eq("recipe", recipe));
			recipeIngredients = criteria.getExecutableCriteria(session).list();
			transaction.commit();
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new RecipeIngredientDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		for (RecipeIngredient recipeIngredient : recipeIngredients) {
			DaoFactory.getInstance().getRecipeIngredientDAO().delete(recipeIngredient);
		}
		return true;
	}

	@Override
	public List<RecipeIngredient> getIngredientByRecipe(Recipe recipe) throws RecipeIngredientDAOException {


		Session session = null;
		List<RecipeIngredient> recipeIngredients= new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			DetachedCriteria criteria = DetachedCriteria.forClass(RecipeIngredient.class);
			criteria.add(Restrictions.eq("recipe", recipe));
			recipeIngredients = criteria.getExecutableCriteria(session).list();
			transaction.commit();
		}
		catch (Exception e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new RecipeIngredientDAOException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return recipeIngredients;
	}
}
