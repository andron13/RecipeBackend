package de.cocktail.controller;

import de.cocktail.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/images/{id}")
    public String saveImage(@PathVariable Long id, @RequestBody String image) {
        return imageService.composeFileDownloadUriAndSavetImage(id, image);
    }


    @GetMapping("/images/{id}/{fileName}")
    public ResponseEntity<String> sendImage(@PathVariable String fileName, @PathVariable Long id) {
        String imageBase64 = imageService.loadFileAsResource(fileName, id);
            return ResponseEntity.
                    ok()
                    .body(imageBase64);
  }
}

