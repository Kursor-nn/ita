package com.kozachuk.ita.Server;

import java.net.Socket;

/**
 * Created by alexanderkozachuk on 12.03.16.
 */
public class ItaServer extends Thread{
    private Socket socket = null;

    public ItaServer(Socket socket) {
        super("ItaServer");
        this.socket = socket;
    }

    public void run(){
        System.out.println("Test run!");
    }
}
