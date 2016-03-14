package com.kozachuk.ita;

import com.kozachuk.ita.Configuration.Configuration;
import com.kozachuk.ita.Persistance.HibernatePersistance;
import com.kozachuk.ita.Application.Application;
import org.hibernate.Session;
import java.io.IOException;
/**
 * Created by alexanderkozachuk on 11.03.16.
 */

public class Main {

    public static void main(String[] args) throws IOException{
        Configuration conf = new Configuration(args);
        conf.parse();

        Application app = Application.getApplication(conf);
        app.handle();
    }
}

