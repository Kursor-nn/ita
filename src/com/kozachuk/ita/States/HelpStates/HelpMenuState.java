package com.kozachuk.ita.States.HelpStates;

import com.kozachuk.ita.Application.Application;
import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.States.ApplicationState;
import com.kozachuk.ita.States.StateType;

/**
 * Created by alexanderkozachuk on 14.03.16.
 */
public class HelpMenuState extends ApplicationState {
    String stateMessage = "Choose section: 1 - information about service, 2 - price list";

    public HelpMenuState(){
        addMessage(stateMessage);
    }

    public HelpMenuState(ApplicationState state){
        this();
        addMessage(state.getMessage());
    }

    @Override
    public Respond handle() {
        return new Respond(message);
    }

    @Override
    public ApplicationState next(StateType state) {
        ApplicationState applicationState = null;

        switch(state){
            case ONE: applicationState = new DescribeState(); break;
            case TWO: applicationState = new PriceState(); break;
            default: applicationState = this;
        }

        return applicationState;
    }
}
