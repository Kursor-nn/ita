package com.kozachuk.ita.Application.ItaApp;

import com.kozachuk.ita.Configuration.Configuration;
import com.kozachuk.ita.Configuration.UserSession;
import com.kozachuk.ita.Persistance.HibernatePersistance;
import com.kozachuk.ita.Persistance.Model.User;
import com.kozachuk.ita.Persistance.Repository.UserRepository;
import com.kozachuk.ita.States.ApplicationState;
import com.kozachuk.ita.States.MainState;
import org.hibernate.Session;
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
    private boolean userProvideMsisdn = true;
    private MessageTransfer messageTransfer;
    private MessageTransfer messageTransferRespond;
    private Session session = null;
    private UserSession userSession;
    private BufferedReader in = null;
    private DataOutputStream out = null;
    private Request requestFromClient = null;
    private ApplicationState applicationState = new MainState();
    private UserRepository repoUser ;
    private User user;
    private Configuration configuration;


    public ItaServer(Socket socket, Configuration configuration) {
        super("ItaServer");
        this.socket = socket;
        this.configuration = configuration;
    }

    public void run(){
        init();

        try {
            Respond respond = new Respond("Please, enter your MSISDN");
            respond.setContent("MSISDN is your number");
            messageTransferRespond.sendXml(respond, out);

            while(userProvideMsisdn) {
                if (in.ready()) {
                    requestFromClient = getRequest(in);
                    Long userMsisdn = Long.valueOf(requestFromClient.getUserInput());
                    user = (User)repoUser.findByMsisdn(userMsisdn);

                    if(user != null){
                        userSession = new UserSession(user);
                        messageTransferRespond.sendXml(applicationState.handle(), out);
                        userProvideMsisdn = false;
                    } else {
                        messageTransferRespond.sendXml(respond, out);
                    }

                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(runServer) {
            try{
                if(in.ready()){
                    requestFromClient = getRequest(in);
                    if(requestFromClient.getStateType() != null){

                        userSession.setStateType(requestFromClient.getStateType());
                        userSession.setUserInput(requestFromClient.getUserInput());

                        applicationState = applicationState.next(requestFromClient.getStateType());
                        applicationState.setSession(session);
                        applicationState.setUserSession(userSession);

                        messageTransferRespond.sendXml(applicationState.handle(), out);
                    } else {
                        messageTransferRespond.sendXml(new Respond("You have chosen a wrong section. Please, try again."), out);
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
                runServer = false;
            } catch (JAXBException e) {
                e.printStackTrace();
                runServer = false;
            }
        }
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

    private void init(){
        try{
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new DataOutputStream(this.socket.getOutputStream());
            messageTransfer = new MessageTransfer(Request.class);
            messageTransfer.init();

            messageTransferRespond = new MessageTransfer(Respond.class);
            messageTransferRespond.init();
            session = HibernatePersistance.getSessionFactory(configuration).openSession();

            repoUser = new UserRepository(session);


            applicationState = new MainState();
        } catch (IOException e){
            System.out.println(e);
            runServer = false;
        }
    }
}
