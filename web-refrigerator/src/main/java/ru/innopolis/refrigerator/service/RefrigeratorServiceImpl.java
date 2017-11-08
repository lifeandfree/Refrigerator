package ru.innopolis.refrigerator.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.DaoFactory;
import ru.innopolis.refrigerator.core.db.exception.IngredientDAOException;
import ru.innopolis.refrigerator.core.db.exception.RefrigeratorDAOException;
import ru.innopolis.refrigerator.core.db.exception.RefrigeratorIngredientDAOException;
import ru.innopolis.refrigerator.core.model.ingredient.Ingredient;
import ru.innopolis.refrigerator.core.model.refrigerator.Refrigerator;
import ru.innopolis.refrigerator.core.model.refrigerator.RefrigeratorIngredient;
import ru.innopolis.refrigerator.core.model.user.User;
import ru.innopolis.refrigerator.service.exception.RefrigeratorServiceException;

import java.util.List;

public class RefrigeratorServiceImpl implements RefrigeratorService {

	private static final Logger logger = LogManager.getLogger(RefrigeratorServiceImpl.class.getName());

	@Override
	public boolean createRefrigeratorElement(String ingredient, String dimension, String quantity, long uid) throws RefrigeratorServiceException {

		if (ingredient == null){
			throw new RefrigeratorServiceException("ingredient is null");
		}
		if (dimension == null){
			throw new RefrigeratorServiceException("dimension is null");
		}
		if (quantity == null){
			throw new RefrigeratorServiceException("quantity is null");
		}

		Ingredient ingredientel = new Ingredient(ingredient, dimension);
		long inId = 0L;
		try {
			inId = DaoFactory.getInstance().getIngredientDAO().getId(ingredientel);
		}
		catch (IngredientDAOException e) {
			logger.error(e);
			throw new RefrigeratorServiceException(e);
		}

		if (inId > 0){
			try {
				ingredientel = (Ingredient) DaoFactory.getInstance().getIngredientDAO().getById(inId);
			}
			catch (Exception e) {
				logger.error(e);
				throw new RefrigeratorServiceException(e);
			}
		}
		else {
			try {
				ingredientel = (Ingredient) DaoFactory.getInstance().getIngredientDAO().add(ingredientel);
			}
			catch (Exception e) {
				logger.error(e);
				throw new RefrigeratorServiceException(e);
			}
		}

		User user = null;
		try {
			user = (User) DaoFactory.getInstance().getUserDAO().getById(uid);
		}
		catch (Exception e) {
			logger.error(e);
			throw new RefrigeratorServiceException(e);
		}

		Refrigerator refrigerator = null;
		try {
			refrigerator = DaoFactory.getInstance().getRefrigeratorDAO().getRefByUser(user);
		}
		catch (RefrigeratorDAOException e) {
			logger.error(e);
			throw new RefrigeratorServiceException(e);
		}

		RefrigeratorIngredient refrigeratorIngredient =
				new RefrigeratorIngredient(refrigerator, ingredientel, Double.parseDouble(quantity));

		try {
			DaoFactory.getInstance().getRefrigeratorIngredientDAO().add(refrigeratorIngredient);
		}
		catch (Exception e) {
			logger.error(e);
			throw new RefrigeratorServiceException(e);
		}
		return true;
	}

	@Override
	public List<RefrigeratorIngredient> getRefrigeratorIngredient(Long uid) throws RefrigeratorServiceException {
		User user = null;
		try {
			user = (User) DaoFactory.getInstance().getUserDAO().getById(uid);
		}
		catch (Exception e) {
			logger.error(e);
			throw new RefrigeratorServiceException(e);
		}

		Refrigerator refrigerator = null;
		try {
			refrigerator = DaoFactory.getInstance().getRefrigeratorDAO().getRefByUser(user);
		}
		catch (RefrigeratorDAOException e) {
			logger.error(e);
			throw new RefrigeratorServiceException(e);
		}

		List<RefrigeratorIngredient> refrigeratorIngredient = null;
		try {
			refrigeratorIngredient = DaoFactory.getInstance().getRefrigeratorIngredientDAO().getRefIngrByRef(refrigerator);
		}
		catch (RefrigeratorIngredientDAOException e) {
			logger.error(e);
			throw new RefrigeratorServiceException(e);
		}

		return refrigeratorIngredient;

	}

	@Override
	public boolean removeRefrigeratorIngredient(Long id) throws RefrigeratorServiceException {
		try {
			RefrigeratorIngredient refrigeratorIngredient = (RefrigeratorIngredient) DaoFactory.getInstance().getRefrigeratorIngredientDAO().getById(id);
			DaoFactory.getInstance().getRefrigeratorIngredientDAO().delete(refrigeratorIngredient);
		}
		catch (Exception e) {
			logger.error(e);
			throw new RefrigeratorServiceException(e);
		}
		return true;
	}
}
