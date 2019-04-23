package de.cocktail.service;

import de.cocktail.model.Cocktail;
import de.cocktail.web.UserCurent;
import de.cocktail.repository.CocktailRepository;
import de.cocktail.web.CocktailWeb;
import de.cocktail.web.CocktailWebOutput;
import de.exeption.NotFoundCocktail;
import de.exeption.PermissionDeny;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.TemporalType;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CocktailServiceImpl implements CocktailService {

    @Autowired
    private CocktailRepository cocktailRepository;
    @Autowired
    private SecurityService securityService;


    public List<CocktailWebOutput> getAllCocktail() {
        List<Cocktail> outputList = cocktailRepository.findAll();
        List<CocktailWebOutput> outputList1 = outputList
                .stream()
                .map(this::creatCocktailWebOutputToCocktail)
                .collect(Collectors.toList());
        if (outputList.isEmpty()) throw new NotFoundCocktail("Cocktail is NotFound");
        return outputList1;
    }

    public CocktailWebOutput getCocktailById(Long id) {
        Optional<Cocktail> optionalRecipe = cocktailRepository.findById(id);
        if (!optionalRecipe.isPresent()) {
            log.warn("This cocktail does not exist " + id);
            throw new NotFoundCocktail("This cocktail does not exist " + id);
        } else return creatCocktailWebOutputToCocktail(optionalRecipe.get());
    }

    private Cocktail transformingTheCocktailAnotherDTO(CocktailWebOutput cocktailWebOutput) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cocktailWebOutput, Cocktail.class);
    }

    public String creatCocktail(CocktailWeb cocktailWeb, UserDetails userDetails) {
        UserCurent userCurent = securityService.currentUser(userDetails);
        Cocktail cocktail = creatCocktailToCocktailWeb(cocktailWeb);
        cocktail.getAuthor().setName(userCurent.getName());
        log.warn("Who created this Cocktail =" + userCurent.getName() + userCurent.getRoles());
        cocktailRepository.save(cocktail);
        return "Who created this Cocktail =" + userCurent.getName() + userCurent.getRoles();
    }

    public void deleteCocktailById(long id) {
        if (!cocktailRepository.existsById(id)) {
            log.warn("This cocktail does not exist " + id);
            throw new NotFoundCocktail("This cocktail does not exist");
        }
        cocktailRepository.deleteById(id);
    }

    public Cocktail creatCocktailToCocktailWeb(CocktailWeb cocktailWeb) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cocktailWeb, Cocktail.class);
    }

    public CocktailWebOutput creatCocktailWebOutputToCocktail(Cocktail cocktail) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cocktail, CocktailWebOutput.class);
    }

    public List<CocktailWebOutput> findByIngredientsContaining(String nameIngredient) {
        List<CocktailWebOutput> outputList = cocktailRepository.findByIngredients_Title(nameIngredient)
                .stream()
                .map(this::creatCocktailWebOutputToCocktail)
                .collect(Collectors.toList());
        if (outputList.isEmpty()) {
            log.warn("This Ingredients not exist " + nameIngredient);
            throw new NotFoundCocktail("This Ingredients not exist " + nameIngredient);
        }
        return outputList;
    }

    public List<CocktailWebOutput> findbyAuthor(String name_author) {
        List<Cocktail> byAuthorName = cocktailRepository
                .findByAuthor_Name(name_author);
        if (byAuthorName.isEmpty()) {
            log.warn("This  author does not exist" + name_author);
            throw new NotFoundCocktail("This  author does not exist");
        }
        return byAuthorName
                .stream()
                .map(this::creatCocktailWebOutputToCocktail)
                .collect(Collectors.toList());
    }

    public void updateCocktail(CocktailWebOutput cocktailWebOutput, Long id, UserDetails userDetails) {
        UserCurent userCurent = securityService.currentUser(userDetails);
        if (userCurent.getName().equals(cocktailWebOutput.getAuthor().getName()) || userCurent.getRoles().contains("ROLE_ADMIN")) {
            Optional<Cocktail> byId = cocktailRepository.findById(id);
            Cocktail cocktail1 = transformingTheCocktailAnotherDTO(cocktailWebOutput);
            if (byId.isPresent()) {
                cocktail1.setId(id);
                cocktail1.getAuthor().setName(userCurent.getName());
                cocktailRepository.save(cocktail1);
                log.warn("Update cocktail id " + id + " /" + userCurent.getName() + " / " + userCurent.getRoles());
            }
        } else {
            log.warn("you do not have the right to change this cocktail"
                    + userCurent.getName() + " /Cocktail ID = " + id + "/ " + userCurent.getRoles());
            throw new PermissionDeny("You do not have the right to change this cocktail");
        }
    }

    public List<CocktailWebOutput> findByTitle(String title) {
        List<Cocktail> byTitle = cocktailRepository.findByTitle(title);
        if (byTitle.isEmpty()) {
            log.warn("This title does not exist " + title);
            throw new NotFoundCocktail("This title does not exist");
        }
        return byTitle
                .stream()
                .map(this::creatCocktailWebOutputToCocktail)
                .collect(Collectors.toList());
    }
}