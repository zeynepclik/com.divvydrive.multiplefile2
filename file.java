public class file {
    String directoryPath = "/Users/zeynepcelik/Desktop/dosyalar/file-list.txt"; // Kontrol edilecek dizin yolu
    File directory = new File(directoryPath);

if (directory.exists() && directory.isDirectory()) {
        System.out.println("Belirtilen dosya yoluna var olundu ve bir dizin.");
    } else {
        System.out.println("Belirtilen dosya yoluna ulaşılamıyor veya bir dizin değil.");
    }

}
