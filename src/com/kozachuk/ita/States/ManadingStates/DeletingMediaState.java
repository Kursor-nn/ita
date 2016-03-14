package com.kozachuk.ita.States.ManadingStates;

import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.Persistance.Model.Note;
import com.kozachuk.ita.Persistance.Model.User;
import com.kozachuk.ita.Persistance.Repository.UserRepository;
import com.kozachuk.ita.States.ApplicationState;
import com.kozachuk.ita.States.StateType;

/**
 * Created by alexanderkozachuk on 14.03.16.
 */
public class DeletingMediaState extends ApplicationState {
    String stateMessage = "Content was deleted from your library.";
    ApplicationState applicationState = null;
    User user = null;
    Note note = null;
    public DeletingMediaState(){
        addMessage(stateMessage);
    }

    public DeletingMediaState(ApplicationState state){
        this();
        addMessage(state.getMessage());
        applicationState = state;
    }

    public DeletingMediaState(User user, Note note){
        this();
        this.user = user;
        this.note = note;
    }

    @Override
    public Respond handle() {
        Respond respond = new Respond();

        UserRepository repoUser = new UserRepository(session);
        repoUser.detach(
                getUserSession().getUser(),
                note);

        respond.setMessage("Content  '" + note.getName() + "' was deleted! ");

        return respond;
    }

    @Override
    public ApplicationState next(StateType state) {
        return new ManagingCabinetState();
    }
}
