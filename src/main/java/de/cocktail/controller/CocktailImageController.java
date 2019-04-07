package de.cocktail.controller;

import de.cocktail.service.CocktailImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CocktailImageController {

    private final CocktailImageService COCKTAIL_IMAGE_SERVICE;

    @Autowired
    public CocktailImageController(CocktailImageService COCKTAIL_IMAGE_SERVICE) {
        this.COCKTAIL_IMAGE_SERVICE = COCKTAIL_IMAGE_SERVICE;
    }

    @PostMapping( "/uploadCocktailImage/{id}" )
    public String uploadFile(@PathVariable Long id, @RequestParam ("file")MultipartFile file) {
        return COCKTAIL_IMAGE_SERVICE.composeFileDownloadUriAndSavetImage(id,file);
    }


    @GetMapping( "/downloadImage/{id}/{fileName:.+}" )
    public ResponseEntity <Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request,@PathVariable Long id) {
        Resource resource = COCKTAIL_IMAGE_SERVICE.loadFileAsResource(fileName,id);
        return ResponseEntity.
                ok()
                .contentType(MediaType.parseMediaType
                        (COCKTAIL_IMAGE_SERVICE.controlContentTypeToDownload(resource,request)))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""
                        + resource.getFilename() + "\"")
                .body(resource);
    }
}