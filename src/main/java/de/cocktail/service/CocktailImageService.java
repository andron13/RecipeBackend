package de.cocktail.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface CocktailImageService {
    String storeFile(MultipartFile file, Long id);

    Resource loadFileAsResource(String fileName, Long id);

    void setImageToCocktail(Long id, String uri, String fileName);

    Path createDirectory(Long id, Path path);

    String composeFileDownloadUri(Long id, MultipartFile file);
}