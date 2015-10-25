package org.gabeorama.Musicster.socketserver;

import com.sun.deploy.util.SessionState;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Håvard on 10/14/2015.
 */
public class SocketServer implements Runnable {

    //Objects
    private Thread serverThread;
    private String threadName = "serverthread";
    private CopyOnWriteArrayList<ClientSocket> connectedClients;
    private AtomicInteger totalClients;
    private int maxClients;
    private int serverPort;
    private final Object threadLock = new Object();

    //Constructor
    public SocketServer(int serverPort, int maxClients) {
        this.serverPort = serverPort;
        this.maxClients = maxClients;
        this.totalClients = new AtomicInteger(0);
        this.connectedClients = new CopyOnWriteArrayList<>();
        if(serverThread == null) {
            serverThread = new Thread(this, this.threadName);
            serverThread.start();
        }
    }

    @Override
    public void run() {
        try {
            Socket socket = null;
            ServerSocket serverSocket = new ServerSocket(serverPort);

            //Run thread forever - or until interrupted
            while(!Thread.interrupted()) {

                while (connectedClients.size() < maxClients) {
                    socket = serverSocket.accept();
                    connectedClients.add(new ClientSocket(socket, this,totalClients.getAndIncrement() )); //TODO Handle maximum integervalue
                }
            }
        } catch (IOException e) {
            //TODO: Handle exception
            e.printStackTrace();
        }
    }

    //Remove client from server
    public void removeClient(ClientSocket client) {
        connectedClients.remove(client);
    }
}
