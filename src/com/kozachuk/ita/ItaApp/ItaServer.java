package com.kozachuk.ita.ItaApp;

import com.kozachuk.ita.CommunicationMessage.MessageTransfer;
import com.kozachuk.ita.CommunicationMessage.Request;
import com.kozachuk.ita.CommunicationMessage.Respond;

import javax.xml.bind.JAXBException;
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
    MessageTransfer messageTransfer;
    MessageTransfer messageTransferRespond;

    public ItaServer(Socket socket) {
        super("ItaServer");
        this.socket = socket;
    }

    private Request getRequest(BufferedReader in) throws IOException, JAXBException {
        StringBuilder builder = new StringBuilder();
        String aux = "";
        Request request = null;
        while (in.ready() && ((aux = in.readLine()) != null)) {
            builder.append(aux);
        }

        String text = builder.toString();
        if(text.length() != 0){
            request = (Request)messageTransfer.makeObject(text);
        }

        return request;
    }

    public void run(){
        BufferedReader in = null;
        DataOutputStream out = null;
        Request requestFromClient = null;

        try{
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new DataOutputStream(this.socket.getOutputStream());
            messageTransfer = new MessageTransfer(Request.class);
            messageTransfer.init();

            messageTransferRespond = new MessageTransfer(Respond.class);
            messageTransferRespond.init();
        } catch (IOException e){
            System.out.println(e);
            runServer = false;
        }

        while(runServer) {
            try{
                if(in.ready()){
                    requestFromClient = getRequest(in);

                    if(requestFromClient != null){
                        Respond respond = new Respond();
                        respond.setMessage("From server : " + requestFromClient.getStateType());
                        messageTransferRespond.sendXml(respond, out);
                    }
                }

            }catch (IOException e){
                System.out.println(e);
                runServer = false;
            } catch (JAXBException e) {
                e.printStackTrace();
                runServer = false;
            }
        }
    }
}
