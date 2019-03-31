package de.cocktail.service;

import de.cocktail.model.Cocktail;
import de.cocktail.web.CocktailWeb;
import de.cocktail.web.CocktailWebOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CocktailService<Y, T> {
    List <CocktailWebOutput> getAllCocktail();

    CocktailWebOutput getCocktailById(Long id);

    void creatCocktail(CocktailWeb cocktailWeb);

    void deleteCocktailById(long id);

    Cocktail creatCocktailToCocktailWeb(CocktailWeb cocktailWeb);

    CocktailWebOutput creatCocktailWebOutputToCocktail(Cocktail cocktail);

    List <CocktailWebOutput> findByIngredientsContaining(String nameIngredient);

    List <CocktailWebOutput> findbyAuthor(String nameauthor);

    void updateCocktail(CocktailWebOutput recipe, Long id);

    List <CocktailWebOutput> findByTitle(String title);

    Y convertTheeCoctailsIntoAnotherEmbodiment(T t, Y clas);


}