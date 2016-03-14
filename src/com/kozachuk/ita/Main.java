package com.kozachuk.ita;

import com.kozachuk.ita.Commands.GetCategoriesCommand;
import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.Configuration.Configuration;
import com.kozachuk.ita.Persistance.HibernatePersistance;
import com.kozachuk.ita.Persistance.Model.Category;
import com.kozachuk.ita.Persistance.Repository.UserRepository;
import com.kozachuk.ita.States.ApplicationState;
import com.kozachuk.ita.States.LivinigStates.CatalogState;
import com.kozachuk.ita.States.LivinigStates.ListeningState;
import com.kozachuk.ita.States.MainState;
import com.kozachuk.ita.Application.Application;
import com.kozachuk.ita.States.StateType;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import java.io.IOException;
import java.util.List;
import com.kozachuk.ita.Commands.*;
/**
 * Created by alexanderkozachuk on 11.03.16.
 */

public class Main {

    public static void main(String[] args) throws IOException{
        Configuration conf = new Configuration(args);
        conf.parse();
        //Session session = HibernatePersistance.getSessionFactory().openSession();


        /*ApplicationState state = new ListeningState();
        state = state.next(StateType.ONE);
        state.setSession(session);
        Respond respond = state.handle();*/
        /*System.out.println(respond.getMessage());
        System.out.println(respond.getContent());

        state = state.next(StateType.TWO);
        state.setSession(session);
        respond = state.handle();
        System.out.println(respond.getMessage());
        System.out.println(respond.getContent());*/


        //HibernatePersistance.shutdown();
        /*Session session = HibernatePersistance.getSessionFactory().openSession();
        Respond respond = new Respond();
        StringBuilder bstring = new StringBuilder();

        Command command = new GetCategoriesCommand(session);
        List<Category> data = command.run();

        for(int i=0; i < data.size(); i++)
            bstring.append(i + " - " + data.get(i).getName() + "; ");

        respond.setContent(bstring.toString());

        HibernatePersistance.shutdown();*/

        //String msisdn = "88888889";
        /*Session session = HibernatePersistance.getSessionFactory().openSession();

        List<Category> categoryList = session.createCriteria(Category.class).
                                              addOrder(Order.asc("name")).list();
        for(Category category: categoryList){
            System.out.println(category.getId() + " : " + category.getName());
        }





        //UserRepository repoUSer = new UserRepository(session);

        //ApplicationState state = new MainState();

        HibernatePersistance.shutdown();*/
        //ApplicationState state = new MainState();

        Application app = Application.getApplication(conf);
        app.handle();
    }
}

