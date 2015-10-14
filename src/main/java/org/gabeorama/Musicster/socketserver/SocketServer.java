package org.gabeorama.Musicster.socketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Håvard on 10/14/2015.
 */
public class SocketServer implements Runnable {

    //Objects
    private Thread serverThread;
    private String threadName = "serverthread";
    private AtomicInteger connectedClients;
    private int maxClients;
    private int serverPort;
    private final Object threadLock = new Object();

    public SocketServer(int serverPort, int maxClients) {
        this.serverPort = serverPort;
        this.maxClients = maxClients;
        this.connectedClients = new AtomicInteger(0);
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

                while (connectedClients.get() < maxClients) {
                    socket = serverSocket.accept();

                }
            }

        } catch (IOException e) {
            //TODO: Handle exception
            e.printStackTrace();
        }
    }
}
