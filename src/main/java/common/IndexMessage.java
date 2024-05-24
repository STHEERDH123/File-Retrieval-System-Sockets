package common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class IndexMessage implements Serializable {
    private String documentPath;
    private String documentContent;
    private Map<String, Integer> wordCounts;

    public IndexMessage(String documentPath, String documentContent) {
        this.documentPath = documentPath;
        this.documentContent = documentContent;
        this.wordCounts = new HashMap<>();
        // Process the document content to extract word counts
        processDocumentContent();
    }

    private void processDocumentContent() {
        String[] words = documentContent.split("\\s+"); // Split content into words
        // Count occurrences of each word
        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public Map<String, Integer> getWordCounts() {
        return wordCounts;
    }

    @Override
    public String toString() {
        return "IndexMessage{" +
                "documentPath='" + documentPath + '\'' +
                ", wordCounts=" + wordCounts +
                '}';
    }
}
