package ru.innopolis.refrigerator.servlet.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.model.refrigerator.RefrigeratorIngredient;
import ru.innopolis.refrigerator.service.RefrigeratorService;
import ru.innopolis.refrigerator.service.RefrigeratorServiceImpl;
import ru.innopolis.refrigerator.service.exception.RefrigeratorServiceException;

import java.util.List;

public class RefrigeratorView {

	private static final Logger logger = LogManager.getLogger(RefrigeratorView.class.getName());
	private static RefrigeratorService rc = new RefrigeratorServiceImpl();
	private Long uid;

	public RefrigeratorView(Long uid) {
		this.uid = uid;
	}

	public RefrigeratorView() {
	}

	public String rendering() throws RefrigeratorServiceException {

		List<RefrigeratorIngredient>  refrigeratorIngredients = rc.getRefrigeratorIngredient(uid);

		StringBuilder stringBuilder = new StringBuilder();
		int i = 0;
		for (RefrigeratorIngredient refrigeratorIngredient : refrigeratorIngredients) {
			i++;
			stringBuilder.append("<tr><th scope=\"row\">").
					append(i).append(" </th><td>").
					append(refrigeratorIngredient.getIngredient().getName()).append("</td><td>").
					append(refrigeratorIngredient.getIngredient().getDimension()).append("</td><td>").
					append(refrigeratorIngredient.getQuantity()).append("</td>").
					append("<td><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Delete\"><button class=\"btn btn-danger btn-xs\" data-title=\"Delete\" data-toggle=\"modal\" onclick=\"window.location.href='refrigerator/delete?id="+ refrigeratorIngredient.getId() +"'\" data-target=\"#delete\" ><span class=\"glyphicon glyphicon-trash\"></span></button></p></td></tr>");

		}
		return stringBuilder.toString();
	}
}
