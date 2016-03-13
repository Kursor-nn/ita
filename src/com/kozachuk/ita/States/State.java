package com.kozachuk.ita.States;

import com.kozachuk.ita.CommunicationMessage.Respond;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */
public interface State {
    Respond sendRespond();
    State next(StateType state);
}
