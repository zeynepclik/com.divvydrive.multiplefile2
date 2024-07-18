import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileUploaderGUI {
    // GUI bileşenleri
    private JTextField textFieldDirectory;
    private JButton buttonLoad;
    private JTextArea textAreaFileInfo;
    private JTextField textFieldSearch;
    private JButton buttonSearch;
    private JTextArea textAreaSearchResults;
    private JPanel mainPanel;

    private FileProcessor fileProcessor;
    private FileSearcher fileSearcher;

    public FileUploaderGUI() {
        fileProcessor = new FileProcessor();
        fileSearcher = new FileSearcher();

        buttonLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadFiles();
            }
        });

        buttonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchFiles();
            }
        });
    }

    private void loadFiles() {
        String directoryPath = textFieldDirectory.getText();
        File dir = new File(directoryPath);
        if (!dir.exists() || !dir.isDirectory()) {
            textAreaFileInfo.setText("The provided path is not valid or is not a directory.");
            return;
        }

        File[] files = dir.listFiles((d, name) -> name.endsWith(".docx")
                || name.endsWith(".xls")
                || name.endsWith(".pdf")
                || name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            textAreaFileInfo.setText("No compatible files found in the directory.");
            return;
        }

        StringBuilder fileInfo = new StringBuilder();

        for (File file : files) {
            fileInfo.append(fileProcessor.processFile(file)).append("\n\n");
        }

        textAreaFileInfo.setText(fileInfo.toString());
    }

    private void searchFiles() {
        String keyword = textFieldSearch.getText();
        if (keyword.isEmpty()) {
            textAreaSearchResults.setText("Enter a keyword to search.");
            return;
        }

        String fileInfo = textAreaFileInfo.getText();
        String searchResults = fileSearcher.searchInFiles(fileInfo, keyword);

        if (searchResults.isEmpty()) {
            textAreaSearchResults.setText("No results found for: " + keyword);
        } else {
            textAreaSearchResults.setText(searchResults);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FileUploaderGUI");
        frame.setContentPane(new FileUploaderGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        String filePath = "/Users/zeynepcelik/Desktop/dosyalar/file-list.txt"; // Kontrol edilecek dosya yolu
        File file = new File(filePath);

      /*  if (file.exists()) {
            if (file.canRead()) {
                System.out.println("Dosya okunabilir.");
            } else {
                System.out.println("Dosya okunamıyor. Erişim izni yok.");
            }

            if (file.canWrite()) {
                System.out.println("Dosya yazılabilir.");
            } else {
                System.out.println("Dosya yazılamıyor. Erişim izni yok.");
            }

            if (file.canExecute()) {
                System.out.println("Dosya çalıştırılabilir.");
            } else {
                System.out.println("Dosya çalıştırılamıyor. Erişim izni yok.");
            }
        } else {
            System.out.println("Dosya mevcut değil.");
        }
        */
    }
}
