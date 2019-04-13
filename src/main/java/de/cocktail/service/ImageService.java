package de.cocktail.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {


    String composeFileDownloadUriAndSavetImage(Long id, String string);

    String loadFileAsResource(String fileName, Long id);

    String splitExtensionFile(MultipartFile file);
}