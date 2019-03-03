package de.recipe.service;

import de.exeption.NotFoundCocktailById;
import de.recipe.model.Cocktail;
import de.recipe.repository.CocktailRepository;
import de.recipe.web.CocktailWeb;
import de.recipe.web.CocktailWebOutput;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CocktailServiceImpl<Y, T> implements CocktailService {

    private final CocktailRepository cocktailRepository;

    @Autowired
    public CocktailServiceImpl(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public List <CocktailWebOutput> getAllCocktail() {
        List <Cocktail> outputList = cocktailRepository.findAll();
        List <CocktailWebOutput> outputList1 = outputList.stream().map(this::creatCocktailWebOutputToRecipe).collect(Collectors.toList());
        if (outputList.isEmpty()) throw new NotFoundCocktailById("Cocktail is NotFound");
        return outputList1;
    }

    public CocktailWebOutput getCocktailById(Long id) {
        Optional <Cocktail> optionalRecipe = cocktailRepository.findById(id);
        if (!optionalRecipe.isPresent()) {
            throw new NotFoundCocktailById("This recipe does not exist");
        } else return creatCocktailWebOutputToRecipe(optionalRecipe.get());
    }
    public Y convertTheeCoctailsIntoAnotherEmbodiment(Object t, Class refactoryclass) {
        ModelMapper modelMapper = new ModelMapper();
        Y y = (Y) modelMapper.map(t, refactoryclass);
        return y;
    }

    public List <T> refactoryObjectListToObjectwebList(List list, Class tClass) {
        ModelMapper modelMapper = new ModelMapper();
        return (List <T>) list.stream().map(a -> modelMapper.map(a, tClass)).collect(Collectors.toList());
    }
    public void creatCocktail(CocktailWeb cocktailWeb) {
        Cocktail cocktail = creatRecipeToCocktailWeb(cocktailWeb);
        cocktailRepository.save(cocktail);
        convertTheeCoctailsIntoAnotherEmbodiment(cocktailRepository.findByTitle(cocktail.getTitle()), CocktailWebOutput.class);
    }

    public void deleteCocktailById(long id) {
        if (!cocktailRepository.existsById(id)) throw new NotFoundCocktailById("This recipe does not exist");
        cocktailRepository.deleteById(id);
    }

    public void deleteCocktailByIngredients(String ingredientName) {
        List <Cocktail> list = cocktailRepository.findByIngredientsNameIn(ingredientName);
        for (Cocktail s : list) {
            cocktailRepository.deleteById(s.getId());
        }
    }


    public Cocktail creatRecipeToCocktailWeb(CocktailWeb cocktailWeb) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cocktailWeb, Cocktail.class);
    }


    public CocktailWebOutput creatCocktailWebOutputToRecipe(Cocktail cocktail) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cocktail, CocktailWebOutput.class);
    }

    public List <CocktailWebOutput> findByIngredientsContaining(String nameIngredient) {
        List <Cocktail> byIngredients = cocktailRepository.findByIngredientsNameIn(nameIngredient);
        if (byIngredients.isEmpty()) throw new NotFoundCocktailById("This recipe name does not exist");
        return byIngredients.stream().map(this::creatCocktailWebOutputToRecipe).collect(Collectors.toList());
    }

    public List <CocktailWebOutput> findbyAuthor(String name_author) {
        List <Cocktail> byAuthorName = cocktailRepository.findByAuthorName(name_author);
        if (byAuthorName.isEmpty()) throw new NotFoundCocktailById("This  author does not exist");
        return byAuthorName.stream().map(this::creatCocktailWebOutputToRecipe).collect(Collectors.toList());
    }

    public void updateCocktail(CocktailWebOutput cocktailWebOutput, Long id) {
        Optional <Cocktail> byId = cocktailRepository.findById(id);
        Cocktail cocktail1 = (Cocktail) convertTheeCoctailsIntoAnotherEmbodiment(cocktailWebOutput, Cocktail.class);
        if (byId.isPresent()) {
            cocktail1.setId(id);
            cocktailRepository.saveAndFlush(cocktail1);
        }else {
            cocktailRepository.save(cocktail1);
        }
    }

    public CocktailWebOutput findByTitle(String title) {
        Cocktail cocktail = cocktailRepository.findByTitle(title);
        return creatCocktailWebOutputToRecipe(cocktail);
    }
}

