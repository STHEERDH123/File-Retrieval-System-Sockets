// server/Dispatcher.java
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Dispatcher implements Runnable {
    private final ServerSocket serverSocket;
    private final Server server;

    public Dispatcher(ServerSocket serverSocket, Server server) {
        this.serverSocket = serverSocket;
        this.server = server;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, server);
                Thread workerThread = new Thread(clientHandler);
                workerThread.start();
            } catch (IOException e) {
                if (serverSocket.isClosed()) {
                    break;
                }
                e.printStackTrace();
            }
        }
    }
}
