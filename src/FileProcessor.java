import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;

import java.io.File;
import java.io.FileInputStream;

public class FileProcessor {


    private final Tika tika;

    public FileProcessor() {
        this.tika = new Tika();
    }

    public String processFile(File file) {
        StringBuilder fileInfo = new StringBuilder();
        try {
            fileInfo.append("File: ").append(file.getName()).append("\n");
            String fileType = tika.detect(file);
            Metadata metadata = new Metadata();
            String content; // content değişkenini tanımladık
            FileInputStream fis = new FileInputStream(file);
            content = tika.parseToString(fis, metadata); // content değişkenine değeri atadık

            fileInfo.append("Type: ").append(fileType).append("\n");
            String author = metadata.get("Author") != null ? metadata.get("Author") : "Unknown";
            String creationDate = metadata.get("Creation-Date") != null ? metadata.get("Creation-Date") : "Unknown";
            fileInfo.append("Author: ").append(author).append("\n");
            fileInfo.append("Creation Date: ").append(creationDate).append("\n");
            fileInfo.append("Content: ").append(content);

            // Dosya bilgilerini virgülle ayrılmış bir şekilde return etmek
            return file.getName() + ", " + fileType + ", " + author + ", " + creationDate;
        } catch (Exception ex) {
            return "Error reading file: " + file.getName() + ", " + ex.getMessage();
        }
    }

}
