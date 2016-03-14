package com.kozachuk.ita.Configuration;

import com.kozachuk.ita.Persistance.Model.User;
import com.kozachuk.ita.States.StateType;

/**
 * Created by alexanderkozachuk on 14.03.16.
 */
public class UserSession {
    private User user;
    private StateType stateType;
    private String userInput;

    public UserSession(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setStateType(StateType stateType) {
        this.stateType = stateType;
    }

    public StateType getStateType() {
        return stateType;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}
