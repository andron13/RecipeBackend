package de.cocktail.controller;

import de.cocktail.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@RestController
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

   @PostMapping(value = "/images")
    public String saveImage( @RequestBody String image, @RequestParam("cocktail_id") String id) {
        return imageService.composeFileDownloadUriAndSavetImage(Long.valueOf(id), image);
    }


    @GetMapping(value = "/images")
    public ResponseEntity<String> sendImage(@RequestParam("fileName") String fileName, @RequestParam ("id") Long id) {
        String imageBase64 = imageService.loadFileAsResource(fileName, id);
            return ResponseEntity.
                    ok()
                    .body(imageBase64);
  }
}

