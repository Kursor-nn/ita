package com.kozachuk.ita.Application;

import com.kozachuk.ita.Configuration.Configuration;

import java.io.IOException;

/**
 * Created by alexanderkozachuk on 12.03.16.
 */
abstract public class Application {
    abstract public void handle() throws IOException;
    public static Application getApplication(Configuration config){
        Application app;

        if(config.getTypeApp().equals("server")){
            app = new Server(config);
        } else {
            app = new Client(config);
        }

        return app;
    }
}
