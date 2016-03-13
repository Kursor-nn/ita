package com.kozachuk.ita.ItaApp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by alexanderkozachuk on 12.03.16.
 */
public class ItaServer extends Thread{
    private Socket socket = null;
    private boolean runServer = true;
    private String clientSentence;
    String cap_Sentence;

    public ItaServer(Socket socket) {
        super("ItaServer");
        this.socket = socket;
    }

    public void run(){
        BufferedReader in = null;
        DataOutputStream out = null;
        try{
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e){
            System.out.println(e);
            runServer = false;
        }

        while(runServer) {
            try{
                clientSentence = in.readLine();
                if(clientSentence != null){
                    cap_Sentence = "Received:" +  clientSentence + '\n';
                    System.out.println(cap_Sentence);
                }

            }catch (IOException e){
                System.out.println(e);
                runServer = false;
            }
        }
    }
}
