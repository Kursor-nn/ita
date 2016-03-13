package com.kozachuk.ita.States.LivinigStates;

import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.States.State;
import com.kozachuk.ita.States.StateType;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */
public class CatalogState implements State {
    @Override
    public Respond sendRespond() {
        Respond respond = new Respond("Catalog of content.Please, choose a category: 1,2,3");
        return respond;
    }

    @Override
    public State next(StateType state) throws IllegalStateException{
        State newState = null;
        switch(state){
            case ONE:
            case TWO:
            case THREE:     newState = new ListeningState(); break;
            case ASTERISK:  System.out.println("Not Implement"); break;
            default:        System.out.println("FUCK"); throw new IllegalStateException();
        }

        return newState;
    }
}
