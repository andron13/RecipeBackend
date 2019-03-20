package de.cocktail.service;

import de.cocktail.model.Cocktail;
import de.cocktail.repository.CocktailRepository;
import de.cocktail.web.CocktailWeb;
import de.cocktail.web.CocktailWebOutput;
import de.exeption.NotFoundCocktailById;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CocktailServiceImpl<Y, T> implements CocktailService {

    @Autowired
    private CocktailRepository cocktailRepository;



    public List <CocktailWebOutput> getAllCocktail() {
        List <Cocktail> outputList = cocktailRepository.findAll();
        List <CocktailWebOutput> outputList1 = outputList.stream().map(this::creatCocktailWebOutputToRecipe).collect(Collectors.toList());
        if (outputList.isEmpty()) throw new NotFoundCocktailById("Cocktail is NotFound");
        return outputList1;
    }

    public CocktailWebOutput getCocktailById(Long id) {
        Optional <Cocktail> optionalRecipe = cocktailRepository.findById(id);
        if (!optionalRecipe.isPresent()) {
            throw new NotFoundCocktailById("This cocktail does not exist");
        } else return creatCocktailWebOutputToRecipe(optionalRecipe.get());
    }

    public Object convertTheeCoctailsIntoAnotherEmbodiment(Object t, Class refactoryclass) {
        ModelMapper modelMapper = new ModelMapper();
        Y y = (Y) modelMapper.map(t, refactoryclass);
        return y;
    }

    public void creatCocktail(CocktailWeb cocktailWeb) {
        Cocktail cocktail = creatRecipeToCocktailWeb(cocktailWeb);
        cocktailRepository.save(cocktail);
    }

    public void deleteCocktailById(long id) {
        if (!cocktailRepository.existsById(id))
            throw new NotFoundCocktailById("This cocktail does not exist");
        cocktailRepository.deleteById(id);
    }

    public Cocktail creatRecipeToCocktailWeb(CocktailWeb cocktailWeb) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cocktailWeb, Cocktail.class);
    }

    public CocktailWebOutput creatCocktailWebOutputToRecipe(Cocktail cocktail) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cocktail, CocktailWebOutput.class);
    }

    public List<Cocktail> findByIngredientsContaining(String nameIngredient) {
        return cocktailRepository.findByIngredientsTitle(nameIngredient);
    }

    public List <CocktailWebOutput> findbyAuthor(String name_author) {
        List <Cocktail> byAuthorName = cocktailRepository.findByAuthor_Name(name_author);
        if (byAuthorName.isEmpty()) throw new NotFoundCocktailById("This  name does not exist");
        return byAuthorName.stream().map(this::creatCocktailWebOutputToRecipe).collect(Collectors.toList());
    }

    public void updateCocktail(CocktailWebOutput cocktailWebOutput, Long id) {
        Optional <Cocktail> byId = cocktailRepository.findById(id);
        Cocktail cocktail1 = (Cocktail) convertTheeCoctailsIntoAnotherEmbodiment(cocktailWebOutput, Cocktail.class);
        if (byId.isPresent()) {
            cocktail1.setId(id);
            cocktailRepository.save(cocktail1);
        } else {
            cocktailRepository.save(cocktail1);
        }
    }

    public CocktailWebOutput findByTitle(String title) {
        Cocktail cocktail = cocktailRepository.findByTitle(title);
        return creatCocktailWebOutputToRecipe(cocktail);
    }
}

