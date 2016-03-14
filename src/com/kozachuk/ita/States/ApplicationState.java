package com.kozachuk.ita.States;

import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.Configuration.UserSession;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */
public abstract class ApplicationState {
    protected String message = "";
    protected String content = "";
    protected ApplicationState nextState = null;
    protected Session session = null;
    private UserSession userSession = null;

    public abstract Respond handle();
    public abstract ApplicationState next(StateType state);

    public void addMessage(String newMessage){
        this.message = this.message + newMessage;
    }

    public void addContent(String newContent){
        this.content = this.content + newContent;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public String getContent() {
        return content;
    }

    public ApplicationState getNextState() {
        return nextState;
    }

    public void setNextState(ApplicationState nextState) {
        this.nextState = nextState;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
