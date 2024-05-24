package common;

import java.io.Serializable;
import java.util.List;

public class SearchMessage implements Serializable {
    private List<String> words;

    public SearchMessage(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }

    @Override
    public String toString() {
        return "SearchMessage{" +
                "words=" + words +
                '}';
    }
}
