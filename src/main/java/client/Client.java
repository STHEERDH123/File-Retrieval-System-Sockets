package client;

import common.IndexMessage;
import common.SearchMessage;
import common.SearchResult;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void connect(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public void disconnect() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }

    public void sendIndexMessage(IndexMessage message) throws IOException {
        out.writeObject(message);
    }

    public SearchResult sendSearchMessage(SearchMessage message) throws IOException, ClassNotFoundException {
        out.writeObject(message);
        return (SearchResult) in.readObject();
    }
}
