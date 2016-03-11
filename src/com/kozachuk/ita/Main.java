package com.kozachuk.ita;

/**
 * Created by alexanderkozachuk on 11.03.16.
 */

import org.hibernate.Session;
import com.kozachuk.ita.Model.User;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, World =)");

        Session session = HibernatePersistence.getSessionFactory().openSession();

        session.beginTransaction();
        User user = new User();

        //Make some product for storing in database
        user.setName("test name");
        user.setMsisdn("789648336221");
        user.setContactId(1L);

        //Save product to database
        Long userId = (Long) session.save(user);
        session.getTransaction().commit();

        //get data from  database
        user = (User) session.get(User.class, userId);
        System.out.println(user);

        //close session
        HibernatePersistence.shutdown();
        System.out.println("Good bay, World =)");
    }
}
