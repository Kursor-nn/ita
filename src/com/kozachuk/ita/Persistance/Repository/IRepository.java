package com.kozachuk.ita.Persistance.Repository;

/**
 * Created by alexanderkozachuk on 12.03.16.
 */
public interface IRepository<T> {
    Integer save(T entity);
    public T find(Class persistanceClass, Integer id);
    void update(T entity);
    boolean delete(Class entityClass, Integer id);
}
