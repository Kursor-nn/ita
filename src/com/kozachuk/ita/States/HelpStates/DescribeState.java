package com.kozachuk.ita.States.HelpStates;

import com.kozachuk.ita.Application.Application;
import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.States.ApplicationState;
import com.kozachuk.ita.States.MainState;
import com.kozachuk.ita.States.StateType;

/**
 * Created by alexanderkozachuk on 14.03.16.
 */
public class DescribeState extends ApplicationState {
    String stateMessage = "You may listen to content and buy it. \n Choose section: * - to main menu, # - to previous menu";

    public DescribeState(){
        addMessage(stateMessage);
    }

    public DescribeState(ApplicationState state){
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
            case LATTICE:   applicationState = new HelpMenuState(); break;
            case ASTERISK:  applicationState = new MainState(); break;
            default:        applicationState = new DescribeState();
        }

        return applicationState;
    }
}
