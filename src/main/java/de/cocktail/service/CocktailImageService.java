package de.cocktail.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface CocktailImageService {

    String controlContentTypeToDownload(Resource resource, HttpServletRequest request);

    String composeFileDownloadUri(Long id, MultipartFile file);

    Resource loadFileAsResource(String fileName, Long id);


}