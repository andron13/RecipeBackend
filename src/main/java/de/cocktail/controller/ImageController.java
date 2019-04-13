package de.cocktail.controller;

import de.cocktail.service.ImageService;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.Optional;

@RestController
public class CocktailImageController {

    private final ImageService imageService;

    @Autowired
    public CocktailImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/images/{id}")
    public String saveImage(@PathVariable Long id, @RequestBody String image) {
        return imageService.composeFileDownloadUriAndSavetImage(id, image);
    }


    @GetMapping("/images/{id}/{fileName:.+}")
    public ResponseEntity<String> sendImage(@PathVariable String fileName, HttpServletRequest request, @PathVariable Long id) {
        String imageBase64 = imageService.loadFileAsResource(fileName, id);
            return ResponseEntity.
                    ok()
                    .body(imageBase64);
  }
}

