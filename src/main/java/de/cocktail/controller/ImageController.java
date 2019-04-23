package de.cocktail.controller;

import de.cocktail.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public String saveImage(@RequestBody String image,
                            @RequestParam("cocktail_id") Long id) {
        return imageService.composeFileDownloadUriAndSavetImage(id, image);
    }


    @GetMapping
    public ResponseEntity<String> sendImage(@RequestParam("fileName") String fileName,
                                            @RequestParam("id") Long id) {
        String imageBase64 = imageService.loadFileAsResource(fileName, id);
        return ResponseEntity.
                ok()
                .body(imageBase64);
    }
}

