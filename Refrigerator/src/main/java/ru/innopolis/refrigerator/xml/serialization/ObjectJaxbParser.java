package ru.innopolis.refrigerator.xml.serialization.refrigerator;

import ru.innopolis.refrigerator.xml.serialization.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class RefrigeratorJaxbParser implements XmlParser {
	@Override
	public Object getObject(File file, Class c) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(c);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object object = unmarshaller.unmarshal(file);
		return object;
	}

	@Override
	public void saveObject(File file, Object o) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(o.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(o,file);
	}
}
