package com.kozachuk.ita.Persistance.Repository;

import org.hibernate.Session;

import java.io.Serializable;

/**
 * Created by alexanderkozachuk on 12.03.16.
 */
public class RepositoryImpl<T> implements IRepositry<T>{

    protected Session session;

    public RepositoryImpl(Session session){
        this.session = session;
    }

    public void setSession(Session session){
        this.session = session;
    }
    public Session getSession(){
        return this.session;
    }

    @Override
    public Integer save(T entity) {
        session.beginTransaction();
        Integer userId = (Integer)session.save(entity);
        session.getTransaction().commit();

        return userId;
    }

    @Override
    public T find(Class persistanceClass, Integer id) {
        return (T)session.get(persistanceClass, id);
    }

    @Override
    public void update(T entity) {
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    @Override
    public boolean delete(Class entityClass, Integer id) {
        Object persistentInstance = session.get(entityClass, id);
        if (persistentInstance != null) {
            session.beginTransaction();
            session.delete(persistentInstance);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }
}
