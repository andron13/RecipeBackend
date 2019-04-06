package de.cocktail.service;

import de.cocktail.model.Cocktail;
import de.cocktail.repository.CocktailRepository;
import de.exeption.FileStorageException;
import de.exeption.MyFileNotFoundException;
import de.exeption.NotFoundCocktail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CocktailImageServiceImpl implements CocktailImageService{

    @Autowired
    CocktailRepository cocktailRepository;

        private final Path fileStorageLocation=

         Paths.get("src\\main\\resources\\image")
                    .toAbsolutePath().normalize();


        public String storeFile(MultipartFile file,Long id) {
            String fileName = StringUtils.cleanPath("cocktails"+id.toString()+ splitExtensionFile(file));
                if(fileName.contains("..")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);                }
                return fileName;
        }

        public Resource loadFileAsResource(String fileName,Long id) {
            try {
                Path filePath = createDirectory(id,fileStorageLocation).resolve(fileName).normalize();
                Resource resource = new UrlResource(filePath.toUri());
                if(resource.exists()) {
                    return resource;
                } else {
                    throw new MyFileNotFoundException("File not found " + fileName);
                }
            } catch (MalformedURLException ex) {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        }
        public void setImageToCocktail(Long id,String uri,String fileName) {
            Optional <Cocktail> byId = cocktailRepository.findById(id);
            if (byId.isPresent()) {
                Cocktail cocktail = byId.get();
                cocktail.getImage().setPatch(uri);
                cocktail.getImage().setTitle(fileName);
                cocktailRepository.save(cocktail);
            }
            else throw new NotFoundCocktail("This cocktail does not exist");

        }
        public Path createDirectory(Long id, Path path) {

            File uploadRootDir = new File(String.valueOf(path)+"/"+id.toString());
            if (!uploadRootDir.exists()) {
                uploadRootDir.mkdirs();
            }
            return uploadRootDir.toPath();

        }
        private String splitExtensionFile(MultipartFile file) {
            String split = file.getOriginalFilename();
            split=split.substring(Objects.requireNonNull(split).lastIndexOf('.'));
            if (validatorExtensionImage(split))return split;
            else throw new FileStorageException("Sorry! Your image is not of acceptable format. " +
                    "Please try a .jpg or .png image again"+file.getOriginalFilename());

        }
        private boolean validatorExtensionImage(String string) {
            List<String>list=Arrays.asList(".jpg",".png");
            return list.contains(string);
        }
        public String composeFileDownloadUri(Long id,MultipartFile file) {
            String fileName = storeFile(file, id);
            String fileDownloadUri = ServletUriComponentsBuilder.
                    fromCurrentContextPath()
                    .path("/downloadImage/")
                    .path(id.toString())
                    .path("/")
                    .path(fileName)
                    .toUriString();

            setImageToCocktail(id,fileDownloadUri,fileName);
            savedImageToServer(id,fileName,file);
        return fileDownloadUri;
        }
        public void savedImageToServer(Long id, String fileName, MultipartFile file) {
            Path targetLocation = createDirectory(id,fileStorageLocation).resolve(fileName);
            try {
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
            }
            }
            public String controlContentTypeToDownload(Resource resource, HttpServletRequest request) {
                String contentType = null;
                try {
                    contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }
                return contentType;
            }
        }