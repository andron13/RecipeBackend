package de.cocktail.controller;

import de.cocktail.service.CocktailImageService;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class CocktailImageController {

    private final CocktailImageService cocktailImageService;

    @Autowired
    public CocktailImageController(CocktailImageService cocktailImageService) {
        this.cocktailImageService = cocktailImageService;
    }

    @PostMapping( "/uploadCocktailImage/{id}" )
    public String uploadFile(@PathVariable Long id, @RequestParam ("file")MultipartFile file) {

        String fileName = cocktailImageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.
                fromCurrentContextPath()
                .path("/downloadImage/")
                .path(fileName)
                .toUriString();
        cocktailImageService.setImageToCocktail(id,fileDownloadUri,fileName);
        return fileDownloadUri;
    }
    @PostMapping( "/uploadImage/{id}" )
    public String uploadFile1(@PathVariable Long id, @RequestParam ("file")MultipartFile file) {
        String fileName = cocktailImageService.storeFile(file);

        return ServletUriComponentsBuilder.
                fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
    }


    @GetMapping( "/downloadImage/{fileName:.+}" )
    public ResponseEntity <Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = cocktailImageService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.
                ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + resource.getFilename() + "\"")
                .body(resource);
    }

}