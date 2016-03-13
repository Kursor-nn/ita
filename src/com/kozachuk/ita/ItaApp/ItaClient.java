package com.kozachuk.ita.ItaApp;

import com.kozachuk.ita.CommunicationMessage.MessageTransfer;
import com.kozachuk.ita.CommunicationMessage.Request;
import com.kozachuk.ita.Configuration.Configuration;
import com.kozachuk.ita.States.State;
import com.kozachuk.ita.States.StateType;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import javax.xml.bind.JAXBException;


/**
 * Created by alexanderkozachuk on 13.03.16.
 */
public class ItaClient extends Thread{
    private boolean isRunning = true;
    private Configuration config;

    public ItaClient(Configuration config) {
        super("ItaClient");
        this.config = config;
    }

    public void run(){
        System.out.println("Client is running");
        Socket clientSocket = null;
        Scanner scaner;
        Request request;
        MessageTransfer mtransfer;
        DataOutputStream outStream;
        BufferedReader inStream;

        try {
            mtransfer = new MessageTransfer(Request.class);
            mtransfer.createXml();
            scaner = new Scanner(System.in);
            request = new Request();
            clientSocket = new Socket(this.config.getHost(), config.getPort());
            outStream = new DataOutputStream(clientSocket.getOutputStream());
            inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (isRunning) {
                System.out.print("Enter code: ");
                if(scaner.hasNext()){
                    String i = scaner.next();
                    if (i.equals("q")) isRunning = false;
                    request.setStateType(StateType.get(i));

                    try {
                        mtransfer.sendXml(request, outStream);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
            isRunning = false;
        }

        System.out.println("Client was stopped");
    }
}
