package server;

import common.IndexStore;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final IndexStore indexStore;
    private final ExecutorService executor;
    private ServerSocket serverSocket;
    private Thread dispatcherThread;

    public Server() {
        this.indexStore = new IndexStore();
        this.executor = Executors.newCachedThreadPool();
    }

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        Dispatcher dispatcher = new Dispatcher(serverSocket, this);
        dispatcherThread = new Thread(dispatcher);
        dispatcherThread.start();
        System.out.println("Server started on port " + port);
    }

    public void stop() throws IOException {
        dispatcherThread.interrupt();
        serverSocket.close();
        executor.shutdown();
        System.out.println("Server stopped.");
    }

    public IndexStore getIndexStore() {
        return indexStore;
    }
}
