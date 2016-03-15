package com.kozachuk.ita.Application;

import com.kozachuk.ita.Configuration.Configuration;
import com.kozachuk.ita.Application.ItaApp.ItaServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by alexanderkozachuk on 12.03.16.
 */
public class Server extends Application {
    ServerSocket serverSocket = null;
    boolean listeningSocket = true;
    Configuration config;

    public Server(Configuration configuration){
        this.config = configuration;
    }

    @Override
    public void handle() throws IOException {
        System.out.println("ItaServer start");
        try {
            serverSocket = new ServerSocket(config.getPort());

            while(listeningSocket){
                Socket clientSocket = serverSocket.accept();
                ItaServer server = new ItaServer(clientSocket, config);
                server.start();
            }

            serverSocket.close();

        } catch (IOException e) {
            System.err.println("Could not listen on port: " + config.getPort());
        }
        System.out.println("ItaApp stop");
    }
}
