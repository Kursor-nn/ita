package com.kozachuk.ita.Application;

import com.kozachuk.ita.Configuration.Configuration;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by alexanderkozachuk on 12.03.16.
 */
public class Client implements IApplication {
    Socket clientSocket;
    boolean applicationStop = false;
    Configuration config;

    public Client(Configuration configuration){
        this.config = configuration;
    }

    @Override
    public void handle() throws IOException {
        this.clientSocket = new Socket(config.getHost(), config.getPort());
        String sentence;
        String modifiedSentence;
        while(applicationStop){
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("Ready");
            sentence = in.readLine();
            out.writeBytes(sentence + '\n');
            modifiedSentence = in.readLine();
            System.out.println(modifiedSentence);
        }
        clientSocket.close();
    }
}
