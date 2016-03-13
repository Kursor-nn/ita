package com.kozachuk.ita;

import com.kozachuk.ita.Application.Application;
import com.kozachuk.ita.Application.Client;
import com.kozachuk.ita.Application.Server;
import com.kozachuk.ita.Configuration.Configuration;

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

