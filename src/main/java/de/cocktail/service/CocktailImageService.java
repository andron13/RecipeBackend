package de.cocktail.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface CocktailImageService {
    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileName);

    void setImageToCocktail(Long id,String uri,String fileName);
  //  boolean controlWhetherItIsImage(MultipartFile multipart);
}