package com.kozachuk.ita.States;

import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.States.LivinigStates.ListeningState;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */
public class MainState implements State{
    @Override
    public Respond sendRespond() {
        String message = "Please, choose section : \n" +
                "1 - listening content \n" +
                "2 - managing private cabinet \n" +
                "3 - help \n";

        Respond respond = new Respond(message);
        return respond;
    }

    @Override
    public State next(StateType stateType) {
        State state = null;

        switch(stateType){
            case TWO: state = new ListeningState(); break;
            default: throw new IllegalStateException();
        }

        return state;
    }
}
