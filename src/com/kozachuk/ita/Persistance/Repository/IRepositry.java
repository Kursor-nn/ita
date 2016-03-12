package com.kozachuk.ita.Persistance.Repository;

import java.io.Serializable;

/**
 * Created by alexanderkozachuk on 12.03.16.
 */
public interface IRepositry<T> {
    Integer save(T entity);
    public T find(T entity, Integer id);
    void update(T entiry);
    boolean delete(Class entityClass, Integer id);
}
