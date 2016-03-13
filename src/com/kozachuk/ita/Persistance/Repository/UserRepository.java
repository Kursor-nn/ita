package com.kozachuk.ita.Persistance.Repository;

import com.kozachuk.ita.Persistance.Model.Note;
import com.kozachuk.ita.Persistance.Model.User;
import org.hibernate.Session;

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
        Set<Note> attachedNotes = user.getNotes();
        user.getNotes().add(note);
        session.getTransaction().commit();
    }

    public void detach(User user, Note note){
        this.session.beginTransaction();
        user.getNotes().remove(note);
        this.session.getTransaction().commit();
    }
}
