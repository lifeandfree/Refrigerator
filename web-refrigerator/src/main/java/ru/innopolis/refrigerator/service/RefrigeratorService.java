package ru.innopolis.refrigerator.service;

import ru.innopolis.refrigerator.core.model.refrigerator.RefrigeratorIngredient;
import ru.innopolis.refrigerator.service.exception.RefrigeratorServiceException;

import java.util.List;

public interface RefrigeratorService {
	boolean createRefrigeratorElement(String ingredient, String dimension, String quantity, long uid) throws RefrigeratorServiceException;

	List<RefrigeratorIngredient> getRefrigeratorIngredient(Long uid) throws RefrigeratorServiceException;

	boolean removeRefrigeratorIngredient(Long id) throws RefrigeratorServiceException;
}
