package de.cocktail.service;

import de.cocktail.model.Cocktail;
import de.cocktail.repository.CocktailRepository;
import de.exeption.CocktailImageFileNotFoundException;
import de.exeption.FileStorageException;
import de.exeption.NotFoundCocktail;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class ImageServiceImpl implements ImageService {

    private final CocktailRepository cocktailRepository;
//   private static final Logger logger = LogManager.getLogger(ImageServiceImpl.class);
//
        private final Path FILE_STORAGE_LOCATION =Paths
                 .get("src"+File.separator+"main"+File.separator+"resources"+File.separator+"image")
                    .toAbsolutePath()
                 .normalize();

    @Autowired
    public ImageServiceImpl(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public String composeFileDownloadUriAndSavetImage(Long id, String string) {
        MultipartFile file = Base64InputStream(string);
        String fileName = generateImageName(file, id);
        String fileDownloadUri = ServletUriComponentsBuilder.
                fromCurrentContextPath()
                .path("/images/")
                .path(id.toString())
                .path("/")
                .path(fileName)
                .toUriString();
        String image_path= FILE_STORAGE_LOCATION.toString()+File.separator+id+File.separator+fileName;
        savedImageToServer(id,fileName,file);
        checkIfIsImage(image_path);
        setImageToCocktail(id,fileDownloadUri,fileName, getByHeightAndWidthToImage(image_path));
        return fileDownloadUri;
    }
    private String generateImageName(MultipartFile file, Long id) {
        Random random=new Random();
                if(file.getOriginalFilename().contains("..")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + file.getOriginalFilename());                }
               if (cocktailRepository.findById(id).isPresent()) {
                   int name=random.nextInt((999999999 - 100000) + 1) + 100000;
                   String fileName = StringUtils.cleanPath(name+id.toString()+ splitExtensionFile(file));
                   return fileName;
               }
               else throw new NotFoundCocktail("This cocktail does not exist");
        }

        public String loadFileAsResource(String fileName,Long id) {
        String imageBase64=null;

                try {
                    Path filePath = Paths.get(FILE_STORAGE_LOCATION.toString())
                            .resolve(id.toString())
                            .resolve(fileName);
                    Resource resource = new UrlResource(filePath.toUri());
                    if (resource.exists()) {
                       imageBase64 = convertImageBase64(resource.getFile());
                        return imageBase64;
                    } else {
                        throw new CocktailImageFileNotFoundException("File not found " + fileName);
                    }
                } catch (MalformedURLException ex) {
                    throw new CocktailImageFileNotFoundException("File not found " + fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return imageBase64;
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
        }
        private Path createDirectory(Long id, Path path) {
            File uploadRootDir = new File(path+File.separator+id.toString());
            boolean isDirectoryCreated= uploadRootDir.exists();
            if (!isDirectoryCreated && uploadRootDir.mkdirs()) {
                return uploadRootDir.toPath();
            }
            else return uploadRootDir.toPath();
        }
        public String splitExtensionFile(MultipartFile file) {
            String split = file.getOriginalFilename();
            split= Objects.requireNonNull(split).substring(Objects.requireNonNull(split).lastIndexOf('.'));
            if (validatorExtensionImage(split))return split;
            else throw new FileStorageException("Sorry! Your image is not of acceptable format. " +
                    "Please try a .jpg , .jpeg or .png image again"+" model- xxxxx.jpg & xxxx.jpeg & xxxxx.png "
                    +file.getOriginalFilename());

        }
        private boolean validatorExtensionImage(String string) {
            List<String>list=Arrays.asList(".jpg",".png",".jpeg");
            return list.contains(string);
        }

        private void savedImageToServer(Long id, String fileName, MultipartFile file) {
            Path targetLocation = createDirectory(id, FILE_STORAGE_LOCATION).resolve(fileName);
            try {
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
            }
            }

   private void checkIfIsImage(String image_path){
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
    private MultipartFile Base64InputStream(String base64String) {
        byte[] imageByteArray = Base64.decodeBase64(base64String);
       return new MockMultipartFile("image","image.jpg","image/jpg",imageByteArray) ;
    }

    private String convertImageBase64(File file) {
        String imageStr=null;
        InputStream finput ;
        try {
            finput = new FileInputStream(file);
            byte[] imageBytes = new byte[(int)file.length()];
            finput.read(imageBytes,0 , imageBytes.length);
            finput.close();
            imageStr= Base64.encodeBase64String(imageBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageStr;
    }
}