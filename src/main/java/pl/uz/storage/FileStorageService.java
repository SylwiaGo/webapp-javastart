package pl.uz.storage;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private final String fileStorageLocation;
    private final String imageStorageLocation;

//    do konstruktora wstrzyknięta jest właściwość app.storage.location. Określa ona miejscu na dysku, gdzie będą przechowywane pliki
    public FileStorageService(@Value("${app.storage.location}") String fileStorageLocation) throws FileNotFoundException {
        this.fileStorageLocation = fileStorageLocation;
        this.imageStorageLocation = fileStorageLocation + "/img/";
        Path fileStoragePath = Path.of(fileStorageLocation);
        checkDirectoryExists(fileStoragePath);
        Path imageStoragePath = Path.of(imageStorageLocation);
        checkDirectoryExists(imageStoragePath);
    }

    private void checkDirectoryExists(Path path) throws FileNotFoundException {
        if(Files.notExists(path)) {
            throw new FileNotFoundException("Directory %s does not exist.".formatted(path.toString()));
        }
    }

    public String saveImage(MultipartFile file) {
        return saveFile(file, imageStorageLocation);
    }

    public String saveFile(MultipartFile file) {
        return saveFile(file, fileStorageLocation);
    }

    private String saveFile(MultipartFile file, String storageLocation) {
        Path filePath = createFilePath(file, storageLocation);
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.getFileName().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    metoda ta zapobiega nadpisywaniu zdjec dodanych do produktów, np. gdy mamy dwa produkty MSI RTX 3080 Gaming X Trio
//    ze zdjęiem MSI_RTX_3080.png i dodamy nowy produkt MSI RTX 3080 Gaming Z Trio z innym zdjeciem o tej samej nazwie
//    to zdjecie sie nie nadpisze tylko do nazw zdjec zostana dodane indexy 0 i 1.
    private Path createFilePath(MultipartFile file, String storageLocation) {
        String orignalFileName = file.getOriginalFilename();
        String fileBaseName = FilenameUtils.getBaseName(orignalFileName);
        String fileExtension = FilenameUtils.getExtension(orignalFileName);
        String completeFilename;
        Path path;
        int fileIndex = 0;
        do {
            completeFilename = fileBaseName + fileIndex + "." + fileExtension;
            path = Paths.get(storageLocation, completeFilename);
            fileIndex += 1;
        } while (Files.exists(path));
        return path;
    }
}
