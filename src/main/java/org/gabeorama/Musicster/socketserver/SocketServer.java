package org.gabeorama.Musicster.socketserver;

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
    private CopyOnWriteArrayList<ClientSocket> connectedClients = new CopyOnWriteArrayList<>();
    private int maxClients;
    private int serverPort;
    private final Object threadLock = new Object();

    public SocketServer(int serverPort, int maxClients) {
        this.serverPort = serverPort;
        this.maxClients = maxClients;
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

            while(!Thread.interrupted()) {

                while (connectedClients.size() < maxClients) {
                    socket = serverSocket.accept();

                }

                synchronized (threadLock) {
                    threadLock.wait();
                }
            }

        } catch (IOException | InterruptedException e) {
            //TODO: Handle exception
            e.printStackTrace();
        }
    }

    public void removeClient(ClientSocket client) {

        //Remove client from list
        connectedClients.remove(client);
        //Notify thread if it's waiting
        if(serverThread.getState() == Thread.State.WAITING) {
            threadLock.notify();
        }
    }
}
