package com.kozachuk.ita.Commands;

import org.hibernate.Session;

import java.util.List;

/**
 * Created by alexanderkozachuk on 14.03.16.
 */
public abstract class Command {
    protected Session session;
    public abstract List run();

    public Command(){}
    public Command(Session session){
        this.session = session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
