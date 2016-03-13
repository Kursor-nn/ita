package com.kozachuk.ita.States.LivinigStates;

import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.States.State;
import com.kozachuk.ita.States.StateType;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */
public class ListeningState implements State{
    private int contentIndex = 0;

    @Override
    public Respond sendRespond() {
        Respond respond = new Respond("Please, choose action:\n 1 - next content;\n 2 - add content,\n '*' - to main menu, '#' - to previous menu ");
        respond.setContent("You are listening the following content : content "+contentIndex+" =)");
        this.contentIndex++;
        return respond;
    }

    @Override
    public State next(StateType state) {
        State newState = null;
        switch(state){
            case ONE:       newState = this; break;
            case TWO:       newState = new AddingContentState(); break;
            case ASTERISK:  newState = new CatalogState(); break;
            default:        System.out.println("FUCK"); throw new IllegalStateException();
        }

        return newState;
    }
}
