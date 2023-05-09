package com.spring.project.hotelbooking.xmlparser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLParser {

    public RoomsTagXML parseXMLRooms(File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(RoomsTagXML.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (RoomsTagXML) jaxbUnmarshaller.unmarshal(file);
    }
}
