package com.kozachuk.ita.States.LivinigStates;

import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.Persistance.Model.Note;
import com.kozachuk.ita.Persistance.Model.User;
import com.kozachuk.ita.Persistance.Repository.UserRepository;
import com.kozachuk.ita.States.ApplicationState;
import com.kozachuk.ita.States.StateType;
import org.hibernate.Session;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */
public class AddingContentState extends ApplicationState {
    private String stateMessage = "You have added content.\nYou will be redirect to listening section\n\n";
    ApplicationState applicationState;
    Note note = null;
    User user = null;

    public AddingContentState(){
        addMessage(stateMessage + '\n');
    }
    public AddingContentState(ApplicationState applicationState){
        this();
        addMessage(applicationState.getMessage());
    }

    public AddingContentState(User user, Note note){
        this();
        this.note = note;
        this.user = user;
    }

    @Override
    public Respond handle() {
        Respond respond = new Respond(message);
        UserRepository repoUser = new UserRepository(session);

        repoUser.attach(user, note);
        respond.setContent(note.getName() + " was added to your library.");

        return respond;
    }

    @Override
    public ApplicationState next(StateType stateType) {
        return new ListeningState();
    }
}
