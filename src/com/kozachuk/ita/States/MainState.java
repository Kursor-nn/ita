package com.kozachuk.ita.States;

import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.States.HelpStates.HelpMenuState;
import com.kozachuk.ita.States.LivinigStates.CatalogState;
import com.kozachuk.ita.States.ManadingStates.ManagingCabinetState;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */
public class MainState extends ApplicationState {
    String stateMessage = "Please, choose section : \n" +  "1 - listening content \n" + "2 - managing private cabinet \n" + "3 - help \n";

    public MainState(){
        addMessage(stateMessage);
    }

    public MainState(ApplicationState applicationState){
        this();
        addMessage(applicationState.getMessage());
    }

    @Override
    public Respond handle() {
        Respond respond = new Respond(message);
        return respond;
    }

    @Override
    public ApplicationState next(StateType stateType) {
        ApplicationState applicationState = null;
        System.out.println("You have chosen : " + applicationState);
        switch(stateType){
            case ONE:   applicationState = new CatalogState(); break;
            case TWO:   applicationState = new ManagingCabinetState(); break;
            case THREE: applicationState = new HelpMenuState(); break;
            default:    applicationState = this;
        }

        return applicationState;
    }
}
