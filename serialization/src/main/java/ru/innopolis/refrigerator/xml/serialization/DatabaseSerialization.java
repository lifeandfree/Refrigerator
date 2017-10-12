package ru.innopolis.refrigerator.xml.serialization;

import ru.innopolis.refrigerator.core.Constants;
import ru.innopolis.refrigerator.core.db.jdbc.dao.refrigerator.RefrigeratorDAO;
import ru.innopolis.refrigerator.core.db.jdbc.exception.RefrigeratorDAOException;
import ru.innopolis.refrigerator.xml.serialization.refrigerator.Refrigerators;

import javax.xml.bind.JAXBException;
import java.io.File;

public class DatabaseSerialization {

	public static void main(String[] args) {

		Refrigerators refrigerators = new Refrigerators();
		try {
			refrigerators.setRefrigerators(RefrigeratorDAO.getAll());
		}
		catch (RefrigeratorDAOException e) {
			e.printStackTrace();
		}
		ObjectJaxbParser parser = new ObjectJaxbParser();
		try {
			parser.saveObject(new File(Constants.REFRIGERATORS_XML_FILENAME), refrigerators);
		}
		catch (JAXBException e) {
			e.printStackTrace();
		}
	}


}
