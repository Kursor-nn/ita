package com.kozachuk.ita;

/**
 * Created by alexanderkozachuk on 11.03.16.
 */

import com.kozachuk.ita.Application.IApplication;
import com.kozachuk.ita.Application.Server;
import com.kozachuk.ita.Configuration.Configuration;
import com.kozachuk.ita.Persistance.HibernatePersistence;
import com.kozachuk.ita.Persistance.Model.Category;
import com.kozachuk.ita.Persistance.Model.Note;
import com.kozachuk.ita.Persistance.Model.User;
import com.kozachuk.ita.Persistance.Repository.RepositoryImpl;
import com.kozachuk.ita.Server.ItaServer;
import org.hibernate.Session;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException{
        System.out.println("Hello, world");
        Session session = HibernatePersistence.getSessionFactory().openSession();
        RepositoryImpl repoGeneral = new RepositoryImpl(session);


        Category category1 = new Category("category 1");
        Category category2 = new Category("category 2");
        Category category3 = new Category("category 3");

        Note note1 = new Note("note 1");
        Note note2 = new Note("note 2");
        Note note3 = new Note("note 3");
        Note note4 = new Note("note 4");
        Note note5 = new Note("note 5");
        Note note6 = new Note("note 6");
        Note note7 = new Note("note 7");
        Note note8 = new Note("note 8");
        Note note9 = new Note("note 9");
        Note note10 = new Note("note 10");
        Note note11 = new Note("note 11");
        Note note12 = new Note("note 12");

        note1.setCategory(category1);
        note2.setCategory(category2);
        note3.setCategory(category3);
        note4.setCategory(category3);
        note5.setCategory(category2);
        note6.setCategory(category1);
        note7.setCategory(category2);
        note8.setCategory(category3);
        note9.setCategory(category1);
        note10.setCategory(category3);
        note11.setCategory(category2);
        note12.setCategory(category1);

        User user1 = new User(88888889L, "user 1");
        User user2 = new User(88888810L, "user 2");
        User user3 = new User(88888811L, "user 3");


        user1.setNote(note1);
        user2.setNote(note2);
        user3.setNote(note3);
        user1.setNote(note4);
        user2.setNote(note5);
        user3.setNote(note6);
        user1.setNote(note7);
        user2.setNote(note8);
        user3.setNote(note9);
        user1.setNote(note10);
        user2.setNote(note11);
        user3.setNote(note12);


        repoGeneral.save(user1);
        repoGeneral.save(user2);
        repoGeneral.save(user3);

        HibernatePersistence.shutdown();
        System.out.println("Good bay, world");
    }
}
