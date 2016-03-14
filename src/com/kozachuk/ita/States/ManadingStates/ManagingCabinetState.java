package com.kozachuk.ita.States.ManadingStates;

import com.kozachuk.ita.Application.Application;
import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.Persistance.Model.Note;
import com.kozachuk.ita.Persistance.Model.User;
import com.kozachuk.ita.Persistance.Repository.UserRepository;
import com.kozachuk.ita.States.ApplicationState;
import com.kozachuk.ita.States.LivinigStates.AddingContentState;
import com.kozachuk.ita.States.LivinigStates.ListeningState;
import com.kozachuk.ita.States.MainState;
import com.kozachuk.ita.States.StateType;
import com.sun.media.sound.SoftMainMixer;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import javax.jws.Oneway;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by alexanderkozachuk on 14.03.16.
 */
public class ManagingCabinetState extends ApplicationState{
    List<Note> notes = new ArrayList<Note>();
    Iterator<Note> notesIterator = null;
    Note currentNote = null;
    User user;
    Integer contentIndex;
    String stateMessage = "You are located at menu of managing service and managing your medias. " +
            "Choose action : 1 - playing next content, 2 - deleting a media from your library, * - move to main menu. " +
            "Press '1' for start playing your medias";

    public ManagingCabinetState(){
        addMessage(stateMessage);
    }

    public ManagingCabinetState(ApplicationState state){
        this();
        addMessage(state.getMessage());
        this.nextState = state;
    }

    @Override
    public Respond handle() {
        Respond respond = new Respond();
        respond.setMessage(message);

        UserRepository repoUser = new UserRepository(session);
        user = (User)repoUser.find(User.class, 1);
        Set<Note> temp = user.getNotes();
        respond.setContent("Please, press '1' for starting");

        if(notes.isEmpty() && !(user.getNotes().isEmpty())){
            notes.addAll(user.getNotes());
            notesIterator = notes.iterator();
        } else {
            currentNote = null;

            if(!user.getNotes().isEmpty() && notesIterator.hasNext()) {
                currentNote = notesIterator.next();
            }

            respond.setContent(getContentForRespond());
        }

        if(nextState != null){
            respond = nextState.handle();
            respond.setMessage(respond.getMessage() + message);
            nextState = null;
            respond.setContent(getContentForRespond());
        }

        return respond;
    }

    @Override
    public ApplicationState next(StateType state) {
        ApplicationState applicationState = null;

        switch (state){
            case ONE:       applicationState = this; break;
            case TWO:
                            Note note = getCurrentNote();
                            if(note != null){
                                DeletingMediaState newState = new DeletingMediaState(user, getCurrentNote());
                                newState.setSession(session);
                                this.setNextState(newState);
                            }
                            applicationState = this; break;
            case ASTERISK:  applicationState = new MainState(); break;
            default:        applicationState = new ManagingCabinetState();
        }

        return applicationState;
    }

    private Note getCurrentNote(){
        return currentNote;
    }

    private String getContentForRespond(){
        if(getCurrentNote() != null) {
            return "You are listening the following content : " + getCurrentNote().getName();
        }
        return "You have seen all content in your library.";
    }
}
