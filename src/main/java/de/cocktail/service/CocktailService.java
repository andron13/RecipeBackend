package de.cocktail.service;

import de.cocktail.model.Cocktail;
import de.cocktail.web.CocktailWeb;
import de.cocktail.web.CocktailWebOutput;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CocktailService<Y, T> {
    public List <CocktailWebOutput> getAllCocktail();

    public CocktailWebOutput getCocktailById(Long id);

    public void creatCocktail(CocktailWeb cocktailWeb);

    public void deleteCocktailById(long id);

    public Cocktail creatRecipeToCocktailWeb(CocktailWeb cocktailWeb);

    public CocktailWebOutput creatCocktailWebOutputToRecipe(Cocktail cocktail);

    public List<Cocktail> findByIngredientsContaining(String nameIngredient);

    public List <CocktailWebOutput> findbyAuthor(String nameauthor);

    public void updateCocktail(CocktailWebOutput recipe, Long id);

    public CocktailWebOutput findByTitle(String title);

    public Y convertTheeCoctailsIntoAnotherEmbodiment(T t, Class <Y> refactoryclass);
}