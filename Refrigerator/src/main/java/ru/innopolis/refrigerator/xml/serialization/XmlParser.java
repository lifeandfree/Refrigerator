package ru.innopolis.refrigerator.xml.serialization;

import javax.xml.bind.JAXBException;
import java.io.File;

public interface XmlParser {
	Object getObject(File file, Class c) throws JAXBException;
	void saveObject(File file, Object o) throws JAXBException;
}
