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

        /*boolean isRunning = true;
        String sentence;
        String modifiedSentence;
        Socket clientSocket = new Socket(config.getHost(), config.getPort());

        while(isRunning){
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.writeBytes("FUCK 1" + '\n');

            //sentence = in.readLine();
            //out.writeBytes(sentence + '\n');
            //modifiedSentence = in.readLine();
            //System.out.println(modifiedSentence);
        }
        clientSocket.close();*/


        /*try {
            serverSocket = new ServerSocket(config.getPort());

            while(listeningSocket){
                Socket clientSocket = serverSocket.accept();
                ItaClient client = new ItaClient(clientSocket);
                client.start();
            }

            serverSocket.close();

        } catch (IOException e) {
            System.err.println("Could not listen on port: " + config.getPort());
        }*/
    }
}
