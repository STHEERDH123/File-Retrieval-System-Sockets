package server;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start(12345);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    server.stop();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
