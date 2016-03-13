package com.kozachuk.ita.Persistance.Repository;

import java.io.Serializable;

/**
 * Created by alexanderkozachuk on 12.03.16.
 */
public interface IRepositry<T> {
    Integer save(T entity);
    public T find(Class persistanceClass, Integer id);
    void update(T entity);
    boolean delete(Class entityClass, Integer id);
}
