package com.kozachuk.ita.Application.ItaApp;

import com.kozachuk.ita.CommunicationMessage.MessageTransfer;
import com.kozachuk.ita.CommunicationMessage.Request;
import com.kozachuk.ita.CommunicationMessage.Respond;
import com.kozachuk.ita.Configuration.Configuration;
import com.kozachuk.ita.States.StateType;

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
    private MessageTransfer messageTransferRespond;
    MessageTransfer mtransfer;

    public ItaClient(Configuration config) {
        super("ItaClient");
        this.config = config;
    }

    private Respond getRespond(MessageTransfer messageTransfer, BufferedReader inStream) throws IOException, JAXBException {
        StringBuilder builder = new StringBuilder();
        String aux = "";
        Respond respond = null;
        while (inStream.ready() && ((aux = inStream.readLine()) != null)) {
            builder.append(aux);
        }

        String text = builder.toString();
        if(text.length() != 0){
            respond = (Respond)messageTransfer.makeObject(text);
        }

        return respond;

    }

    public void run(){
        System.out.println("Client is running");
        Socket clientSocket = null;
        Scanner scaner;
        Request request;
        DataOutputStream outStream;
        BufferedReader inStream;

        try {
            mtransfer = new MessageTransfer(Request.class);
            mtransfer.init();
            scaner = new Scanner(System.in);
            request = new Request();
            clientSocket = new Socket(this.config.getHost(), config.getPort());
            outStream = new DataOutputStream(clientSocket.getOutputStream());
            inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            messageTransferRespond = new MessageTransfer(Respond.class);
            messageTransferRespond.init();

            while (isRunning) {
                if(inStream.ready()){
                    Respond respond = (Respond)getRespond(messageTransferRespond, inStream);
                    respond.print();

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
                        System.out.println("===============================================================>");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            isRunning = false;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        System.out.println("Client was stopped");
    }
}
