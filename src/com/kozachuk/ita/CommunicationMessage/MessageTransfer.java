package com.kozachuk.ita.CommunicationMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */
public class MessageTransfer {
    JAXBContext jaxbContext = null;
    Marshaller jaxbMarshaller = null;
    Class contextClass = null;

    public MessageTransfer(Class classContext){
        this.contextClass = classContext;
    }

    public void init(){
        try {
            jaxbContext = JAXBContext.newInstance(contextClass);
            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void sendXml(Message message, OutputStream stream) throws JAXBException {
        jaxbMarshaller.marshal(message, stream);
    }

    public Message makeObject(String xml) throws JAXBException {
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Message message = (Message) jaxbUnmarshaller.unmarshal(new StringReader(xml));
        return message;
    }
}
