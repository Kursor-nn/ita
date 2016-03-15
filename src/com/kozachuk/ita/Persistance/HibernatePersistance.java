package com.kozachuk.ita.Persistance;

/**
 * Created by alexanderkozachuk on 11.03.16.
 */

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernatePersistance {

    private static SessionFactory sessionFactory = null;
    private Configuration cfg;

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration cfg = new Configuration();
            cfg.configure("/resources/hibernate.cfg.xml"); //hibernate config xml file name
            return cfg.buildSessionFactory();

        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory buildSessionFactory(com.kozachuk.ita.Configuration.Configuration config) {
        try {
            String host = config.getDatabaseHost() + ":" + config.getDatabasePort() + "/" + config.getDatabaseName();

            Configuration cfg = new Configuration();
            cfg.configure("/resources/hibernate.cfg.xml"); //hibernate config xml file name
            cfg.getProperties().setProperty("hibernate.connection.password", config.getDatabasePassword());
            cfg.getProperties().setProperty("hibernate.connection.username",config.getDatabaseUsername());
            cfg.getProperties().setProperty("hibernate.connection.url", host);

            return cfg.buildSessionFactory();

        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    public static SessionFactory getSessionFactory(com.kozachuk.ita.Configuration.Configuration config) {
        if(sessionFactory == null){
            sessionFactory = buildSessionFactory(config);
        }
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        sessionFactory.close();
    }
}
