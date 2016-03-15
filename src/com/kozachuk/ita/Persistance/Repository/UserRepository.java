package com.kozachuk.ita.Persistance.Repository;

import com.kozachuk.ita.Persistance.Model.Note;
import com.kozachuk.ita.Persistance.Model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alexanderkozachuk on 12.03.16.
 */
public class UserRepository extends RepositoryImpl{
    public UserRepository(Session session){
        super(session);
    }

    public void attach(User user, Note note){
        session.beginTransaction();
        user.getNotes().add(note);
        session.getTransaction().commit();
    }

    public void detach(User user, Note note){
        this.session.beginTransaction();
        user.getNotes().remove(note);
        this.session.getTransaction().commit();
    }

    public User findByMsisdn(Long value) {
        Criteria criteria = session.createCriteria(User.class);
        User entity = (User)criteria.add(Restrictions.eq("msisdn", value)).uniqueResult();
        return entity;
    }

}
