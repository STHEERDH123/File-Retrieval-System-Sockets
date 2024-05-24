package common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SearchResult implements Serializable {
    private Map<String, Map<String, Integer>> results = new HashMap<>();

    public void addWordResult(String word, Map<String, Integer> result) {
        results.put(word, result);
    }

    public Map<String, Map<String, Integer>> getResults() {
        return results;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SearchResult{\n");
        results.forEach((word, docMap) -> {
            sb.append("  ").append(word).append(": \n");
            docMap.forEach((doc, count) -> sb.append("    ").append(doc).append(": ").append(count).append("\n"));
        });
        sb.append("}");
        return sb.toString();
    }
}
