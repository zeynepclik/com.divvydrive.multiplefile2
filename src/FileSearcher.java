public class FileSearcher {

    public String searchInFiles(String fileInfo, String keyword) {
        StringBuilder searchResults = new StringBuilder();
        String[] entries = fileInfo.split("\n\n");

        for (String entry : entries) {
            if (entry.toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.append(entry).append("\n\n");
            }
        }
        return searchResults.toString();
    }
}
