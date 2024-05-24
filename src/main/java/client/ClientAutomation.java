// client/ClientAutomation.java
package client;

import common.IndexMessage;
import common.SearchMessage;
import common.SearchResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientAutomation {
    private final Client client;

    public ClientAutomation(Client client) {
        this.client = client;
    }

    public void indexFolder(String folderPath) throws IOException {
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".txt")) {
                        indexFile(file);
                    }
                }
            }
        }
    }

    private void indexFile(File file) throws IOException {
        String content = new String(Files.readAllBytes(file.toPath()));
        IndexMessage message = new IndexMessage(file.getPath(), content);
        client.sendIndexMessage(message);
    }

    public SearchResult search(String query) throws IOException, ClassNotFoundException {
        List<String> words = Arrays.stream(query.split("\\W+"))
                                   .filter(word -> !word.isEmpty())
                                   .collect(Collectors.toList());
        SearchMessage message = new SearchMessage(words);
        return client.sendSearchMessage(message);
    }
}
