package com.kozachuk.ita.States.LivinigStates;

import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.Persistance.Model.Category;
import com.kozachuk.ita.States.ApplicationState;
import com.kozachuk.ita.States.MainState;
import com.kozachuk.ita.States.StateType;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */
public class CatalogState extends ApplicationState {
    String stateMessage = "Catalog of content.Please, choose a category: ";

    public CatalogState(){
        setMessage(stateMessage);
    }

    public CatalogState(ApplicationState applicationState){
        addMessage(applicationState.getMessage());
        addMessage(stateMessage);
    }

    @Override
    public Respond handle() {
        Respond respond = new Respond(message);

        List<Category> categoryList = session.createCriteria(Category.class)
                                        .addOrder(Order.asc("publicId")).list();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i=0; i < categoryList.size(); i++)
            stringBuilder.append(categoryList.get(i).getPublicId() + " - " + categoryList.get(i).getName() + "; ");

        respond.setContent(stringBuilder.toString());

        return respond;
    }

    @Override
    public ApplicationState next(StateType state) throws IllegalStateException{
        ApplicationState newApplicationState = null;

        switch(state){
            case ONE:
            case TWO:
            case THREE:     newApplicationState = new ListeningState(); break;
            case ASTERISK:
            case LATTICE:   newApplicationState = new MainState(); break;
            default:        newApplicationState = this;
        }

        return newApplicationState;
    }
}
