package com.kozachuk.ita.CommunicationMessage;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Respond implements Message{
    private String message;
    private String content;

    public Respond(){}

    public Respond(String message){
        this.message = message;
    }
    @XmlElement
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    @XmlElement
    public String getContent() {
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("message", getMessage())
                .append("content", getContent())
                .toString();
    }

}
