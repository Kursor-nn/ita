package com.kozachuk.ita.States.HelpStates;

import com.kozachuk.ita.Application.Application;
import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.States.ApplicationState;
import com.kozachuk.ita.States.MainState;
import com.kozachuk.ita.States.StateType;

import java.io.IOException;

/**
 * Created by alexanderkozachuk on 14.03.16.
 */
public class PriceState extends ApplicationState {
    String stateMessage = "All contents are cost 100 rubles. Choose section: * - to main menu, # - to previous menu;";

    public PriceState(){
        addMessage(stateMessage);
    }

    public PriceState(ApplicationState state){
        this();
        addMessage(state.getMessage());
    }

    @Override
    public Respond handle() {
        return new Respond(stateMessage);
    }

    @Override
    public ApplicationState next(StateType state) {
        ApplicationState applicationState = null;

        switch (state){
            case ASTERISK:  applicationState = new MainState(); break;
            case LATTICE:   applicationState = new HelpMenuState(); break;
            default:        applicationState = this;
        }

        return applicationState;
    }
}
