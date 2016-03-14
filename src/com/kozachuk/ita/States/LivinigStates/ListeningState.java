package com.kozachuk.ita.States.LivinigStates;

import com.kozachuk.ita.Application.Application;
import com.kozachuk.ita.Commands.Command;
import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.Persistance.Model.Category;
import com.kozachuk.ita.Persistance.Model.Note;
import com.kozachuk.ita.Persistance.Model.User;
import com.kozachuk.ita.Persistance.Repository.UserRepository;
import com.kozachuk.ita.States.MainState;
import com.kozachuk.ita.States.ApplicationState;
import com.kozachuk.ita.States.StateType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.util.List;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */
public class ListeningState extends ApplicationState {
    private Command command = null;
    private int contentIndex = 0;
    List<Note> notes = null;
    User user = null;
    private String stateMessage = "\nPlease, choose action:\n 1 - next content;\n 2 - add content," +
            "\n '*' - to main menu, '#' - to previous menu. Press '1' for listening";

    public ListeningState(){
        addMessage(stateMessage);
    }

    public ListeningState(ApplicationState applicationState){
        addMessage(applicationState.getMessage());
        addMessage(stateMessage);
        nextState = applicationState;
    }

    @Override
    public Respond handle() {
        Respond respond = new Respond(message);

        UserRepository repoUser = new UserRepository(session);
        user = (User)repoUser.find(User.class, 1);

        respond.setContent("You are listening the following content : ");
        if(notes == null){
            respond.setContent("Please, press '1' for starting");

            notes = session.createCriteria(Note.class)
                    .createCriteria("users", JoinType.LEFT_OUTER_JOIN)
                    .add( Restrictions.isNull("id"))
                    .list();
            contentIndex = 0;

        } else {
            respond.setContent("You have added all content.");
            if(notes.size() > contentIndex) {
                respond.setContent("You are listening the following content : " + getCurrentNote().getName());
                contentIndex++;
            }
        }

        if(nextState != null){
            Respond newRespond = nextState.handle();
            respond.setMessage(newRespond.getMessage() + respond.getMessage()) ;
            nextState = null;
        }

        return respond;
    }

    @Override
    public ApplicationState next(StateType state) {
        ApplicationState newApplicationState = null;
        System.out.println("You have chosen : " + state);

        switch(state){
            case TWO:
                Note note = getCurrentNote();
                if(note != null){
                    AddingContentState newState = new AddingContentState(user, getCurrentNote());
                    newState.setSession(session);
                    this.setNextState(newState);
                }
                newApplicationState = this;
                break;
            case ASTERISK:  newApplicationState = new MainState(); break;
            case LATTICE:   newApplicationState = new CatalogState(); break;
            case ONE:
            default:        newApplicationState = this;
        }

        return newApplicationState;
    }

    private Note getCurrentNote(){
        if(contentIndex < notes.size())
            return notes.get(contentIndex);
        return null;
    };
}
