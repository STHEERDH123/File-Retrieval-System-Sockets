package client;

import common.SearchResult;

import java.io.IOException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        Client client = new Client();
        ClientAutomation automation = new ClientAutomation(client);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();
            String[] parts = command.split("\\s+", 2);
            String action = parts[0];

            if ("quit".equalsIgnoreCase(action)) {
                try {
                    client.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } else if ("connect".equalsIgnoreCase(action)) {
                if (parts.length < 2) {
                    System.out.println("Usage: connect <server IP> <port>");
                    continue;
                }
                String[] address = parts[1].split(":");
                String host = address[0];
                int port = Integer.parseInt(address[1]);
                try {
                    client.connect(host, port);
                    System.out.println("Connected to server");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if ("index".equalsIgnoreCase(action)) {
                if (parts.length < 2) {
                    System.out.println("Usage: index <folder path>");
                    continue;
                }
                String folderPath = parts[1];
                try {
                    automation.indexFolder(folderPath);
                    System.out.println("Indexing completed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if ("search".equalsIgnoreCase(action)) {
                if (parts.length < 2) {
                    System.out.println("Usage: search <query>");
                    continue;
                }
                String query = parts[1];
                try {
                    SearchResult result = automation.search(query);
                    System.out.println("Search results: " + result);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Unknown command");
            }
        }

        scanner.close();
    }
}
