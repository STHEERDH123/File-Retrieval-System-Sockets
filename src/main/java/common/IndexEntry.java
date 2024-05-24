package common;

import java.io.Serializable;

public class IndexEntry implements Serializable {
    private String filePath;
    private int frequency;

    public IndexEntry(String filePath, int frequency) {
        this.filePath = filePath;
        this.frequency = frequency;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return "IndexEntry{" +
                "filePath='" + filePath + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
