package server;

import common.IndexStore;
import common.IndexMessage;
import common.SearchMessage;
import common.SearchResult;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Server server;
    private final IndexStore indexStore;

    public ClientHandler(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
        this.indexStore = server.getIndexStore();
    }

    @Override
    public void run() {
        try (
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())
        ) {
            Object request;
            while ((request = in.readObject()) != null) {
                if (request instanceof IndexMessage) {
                    handleIndexMessage((IndexMessage) request);
                } else if (request instanceof SearchMessage) {
                    handleSearchMessage((SearchMessage) request, out);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void handleIndexMessage(IndexMessage message) {
        Map<String, Integer> wordCounts = message.getWordCounts();
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            // Update the index with the word and its count
            indexStore.updateIndex(word, count, message.getDocumentPath());
        }
    }


//    private void handleIndexMessage(IndexMessage message) {
//        indexStore.updateIndex(message);
//    }

    private void handleSearchMessage(SearchMessage message, ObjectOutputStream out) throws IOException {
        SearchResult result = new SearchResult();
        message.getWords().forEach(word -> result.addWordResult(word, indexStore.search(word)));
        out.writeObject(result);
    }
}
