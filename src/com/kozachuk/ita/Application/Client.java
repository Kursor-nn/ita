package com.kozachuk.ita.Application;

import com.kozachuk.ita.Configuration.Configuration;
import com.kozachuk.ita.Application.ItaApp.ItaClient;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */
public class Client extends Application {
    ServerSocket serverSocket = null;
    boolean listeningSocket = true;
    Configuration config;

    public Client(Configuration configuration){
        this.config = configuration;
    }

    @Override
    public void handle() throws IOException {
        System.out.println("Client start");
        ItaClient client = new ItaClient(config);
        client.start();
    }
}
