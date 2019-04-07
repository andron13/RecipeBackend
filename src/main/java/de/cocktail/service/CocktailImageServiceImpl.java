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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
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

    private final CocktailRepository cocktailRepository;

        private final Path fileStorageLocation=Paths
                 .get("src\\main\\resources\\image")
                    .toAbsolutePath()
                 .normalize();

    @Autowired
    public CocktailImageServiceImpl(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }
    public String composeFileDownloadUriAndSavetImage(Long id, MultipartFile file) {
        String fileName = composesTheNameofTheImage(file, id);
        String fileDownloadUri = ServletUriComponentsBuilder.
                fromCurrentContextPath()
                .path("/downloadImage/")
                .path(id.toString())
                .path("/")
                .path(fileName)
                .toUriString();
        String image_path=fileStorageLocation.toString()+"/"+id+"/"+fileName;

        savedImageToServer(id,fileName,file);
        controlsWhetherTheFailYouReceiveIsImage(image_path);
        setImageToCocktail(id,fileDownloadUri,fileName, getByHeightAndWidthToImage(image_path));
        return fileDownloadUri;
    }
    private String composesTheNameofTheImage(MultipartFile file, Long id) {
            String fileName = StringUtils.cleanPath("cocktails"+id.toString()+ splitExtensionFile(file));
                if(fileName.contains("..")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);                }
                return fileName;
        }

        public Resource loadFileAsResource(String fileName,Long id) {
            try {
                Path filePath = Paths.get(fileStorageLocation.toString())
                        .resolve(id.toString())
                        .resolve(fileName);
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
        private void setImageToCocktail(Long id, String uri, String fileName,String alt) {
            Optional <Cocktail> byId = cocktailRepository.findById(id);
            if (byId.isPresent()) {
                Cocktail cocktail = byId.get();
                cocktail.getImage().setPatch(uri);
                cocktail.getImage().setTitle(fileName);
                cocktail.getImage().setAlt(alt);
                cocktailRepository.save(cocktail);
            }
            else throw new NotFoundCocktail("This cocktail does not exist");

        }
        private Path createDirectory(Long id, Path path) {
            File uploadRootDir = new File(String.valueOf(path)+"/"+id.toString());
            boolean isDirectoryCreated= uploadRootDir.exists();
            if (!isDirectoryCreated && uploadRootDir.mkdirs()) {
                return uploadRootDir.toPath();
            }
            else return uploadRootDir.toPath();
        }
        private String splitExtensionFile(MultipartFile file) {
            String split = file.getOriginalFilename();
            split=split.substring(Objects.requireNonNull(split).lastIndexOf('.'));
            if (validatorExtensionImage(split))return split;
            else throw new FileStorageException("Sorry! Your image is not of acceptable format. " +
                    "Please try a .jpg or .png image again"+" model- xxxxx.jpg&xxxx.jpeg&xxxxx.png "
                    +file.getOriginalFilename());

        }
        private boolean validatorExtensionImage(String string) {
            List<String>list=Arrays.asList(".jpg",".png",".jpeg");
            return list.contains(string);
        }

        private void savedImageToServer(Long id, String fileName, MultipartFile file) {
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
   private void controlsWhetherTheFailYouReceiveIsImage(String image_path){
       BufferedImage miniImage = null;
       try {
           miniImage = ImageIO.read(new File(image_path));
       } catch (IOException e) {
           e.printStackTrace();
       }

       if (miniImage == null) {
            File file= new File(image_path);
            Path fp = file.toPath();
            try {
                Files.delete(fp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw  new FileStorageException("Sorry! Your image is not of acceptable format. ");

        }

    }

    private String getByHeightAndWidthToImage(String image_path){
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(new File(image_path));
        } catch (IOException e) {
            e.printStackTrace();
        }
      return Objects.requireNonNull(bimg).getHeight()+" x "+bimg.getWidth();
    }

        }