package common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexStore {
    private Map<String, Map<String, Integer>> index;

    public IndexStore() {
        index = new HashMap<>();
    }

    public synchronized void indexDatasetFolder(String datasetFolderPath) throws IOException {
        File datasetFolder = new File(datasetFolderPath);
        if (datasetFolder.exists() && datasetFolder.isDirectory()) {
            System.out.println("Indexing folder: " + datasetFolderPath);
            indexFilesInFolder(datasetFolder);
        } else {
            System.out.println("Invalid folder path: " + datasetFolderPath);
        }
    }

    private void indexFilesInFolder(File folder) throws IOException {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // Recursively index files in subfolders
                        indexFilesInFolder(file);
                    } else if (file.getName().toLowerCase().endsWith(".txt")) {
                        // Index content of text files
                        System.out.println("Indexing file: " + file.getAbsolutePath());
                        indexFile(file);
                    } else {
                        System.out.println("Skipping non-text file: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }

    private void indexFile(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        for (String line : lines) {
            String[] words = line.split("\\s+"); // Split line into words
            for (String word : words) {
                // Index each word
                indexWord(word, file.getAbsolutePath());
            }
        }
    }

    public synchronized void updateIndex(String word, int count, String documentPath) {
        word = word.toLowerCase();
        Map<String, Integer> docFrequency = index.computeIfAbsent(word, k -> new HashMap<>());
        docFrequency.put(documentPath, count);
    }

    public synchronized void indexWord(String word, String documentPath) {
        word = word.toLowerCase();
        Map<String, Integer> docFrequency = index.computeIfAbsent(word, k -> new HashMap<>());
        docFrequency.put(documentPath, docFrequency.getOrDefault(documentPath, 0) + 1);
    }

    public synchronized Map<String, Integer> search(String word) {
        word = word.toLowerCase();
        return index.getOrDefault(word, new HashMap<>());
    }
}
