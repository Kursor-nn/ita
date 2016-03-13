package com.kozachuk.ita.States.LivinigStates;

import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.States.State;
import com.kozachuk.ita.States.StateType;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */
public class AddingContentState implements State {

    @Override
    public Respond sendRespond() {
        String message = "You have added content";
        Respond respond = new Respond(message);
        respond.setContent("You will be redirect to listening section");
        return respond;
    }

    @Override
    public State next(StateType stateType) {
        return new ListeningState();
    }
}
