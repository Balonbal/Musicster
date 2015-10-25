package org.gabeorama.Musicster.socketserver;

import com.sun.istack.internal.NotNull;

import java.io.*;
import java.net.Socket;

/**
 * Created by Håvard on 10/14/2015.
 */
public class ClientSocket implements Runnable {
    //Objects
    private Socket socket;
    private SocketServer socketServer;
    private Thread clientThread;
    private String threadName;
    private BufferedWriter output;
    private BufferedReader input;

    public ClientSocket(@NotNull Socket socket, @NotNull SocketServer server, @NotNull int clientNum) throws IOException{
        this.socket = socket;
        this.socketServer = server;
        this.threadName = "client" + Integer.toString(clientNum);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.socket.setSoTimeout(0); //TODO: Set socket timeout according to a configuration file
        if(clientThread == null) {
            clientThread = new Thread(this, threadName);
        }
    }

    @Override
    public void run() {
        String response = "";
        //TODO Handle requests here
        //TODO Build a commandhandler to handle requests from clients
        //TODO Respond
        //TODO Terminate connection
        try {
            //TODO Handle response
            output.write(response.length());
            output.write(response);
        } catch (IOException e) {
            //TODO Handle exception
        }
        socketServer.removeClient(this);
    }
}
