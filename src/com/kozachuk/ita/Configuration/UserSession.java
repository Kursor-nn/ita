package com.kozachuk.ita.Configuration;

import com.kozachuk.ita.Persistance.Model.User;

/**
 * Created by alexanderkozachuk on 14.03.16.
 */
public class UserSession {
    private User user;

    public UserSession(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
