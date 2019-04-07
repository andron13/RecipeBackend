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

    private final CocktailImageService cocktailImageService;

    @Autowired
    public CocktailImageController(CocktailImageService cocktailImageService) {
        this.cocktailImageService = cocktailImageService;
    }

    @PostMapping( "/uploadCocktailImage/{id}" )
    public String uploadFile(@PathVariable Long id, @RequestParam ("file")MultipartFile file) {
        return cocktailImageService.composeFileDownloadUriAndSavetImage(id,file);
    }


    @GetMapping( "/downloadImage/{id}/{fileName:.+}" )
    public ResponseEntity <Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request,@PathVariable Long id) {
        Resource resource = cocktailImageService.loadFileAsResource(fileName,id);
        return ResponseEntity.
                ok()
                .contentType(MediaType.parseMediaType
                        (cocktailImageService.controlContentTypeToDownload(resource,request)))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""
                        + resource.getFilename() + "\"")
                .body(resource);
    }
}