package com.kozachuk.ita.Persistance.Repository;

import com.kozachuk.ita.Persistance.Model.Note;
import com.kozachuk.ita.Persistance.Model.User;
import org.hibernate.Session;

/**
 * Created by alexanderkozachuk on 12.03.16.
 */
public class UserRepository extends RepositoryImpl {
    public UserRepository(Session session){
        super(session);
    }
}
