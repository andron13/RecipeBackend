package de.recipe.controller;

import de.recipe.service.CocktailService;
import de.recipe.web.CocktailWeb;
import de.recipe.web.CocktailWebOutput;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CocktailController {
  private CocktailService cocktailService;

    @Autowired
    public CocktailController( CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }


    @GetMapping( value = "/cocktails " )
    public List getAllRecipe() {
        return cocktailService.getAllCocktail();
    }

    @PostMapping( value = "/cocktails" )
    public CocktailWebOutput creatRecipeController(@RequestBody CocktailWeb cocktailWeb) {
        cocktailService.creatCocktail(cocktailWeb);
        return (CocktailWebOutput) cocktailService.convertTheeCoctailsIntoAnotherEmbodiment( cocktailWeb,CocktailWebOutput.class);
    }

    @GetMapping( value = "/cocktails?id={id} " )
    public CocktailWebOutput getCocktailById(@PathVariable Long id) {
        return cocktailService.getCocktailById(id);
    }

    @DeleteMapping( value = "/cocktails/{id} " )
    public void delletCocktailByID(@PathVariable Long id) {
        cocktailService.deleteCocktailById(id);

           }

    @GetMapping(value = "/cocktails?ingredient={name_ingredient} " )
    public List findByIngredient(@PathVariable String name_ingredient) {
        return cocktailService.findByIngredientsContaining(name_ingredient);
    }

    @GetMapping( value = "/cocktails?author={name_author} " )
    public List <CocktailWebOutput> findBYAuthor(@PathVariable String name_author) {
        return cocktailService.findbyAuthor(name_author);
    }

    @GetMapping( value = "/cocktails?title={title} " )
    public CocktailWebOutput findByTitle(@PathVariable String title) {
        return cocktailService.findByTitle(title);

    }

    @PutMapping( value = "/cocktails/{id} " )
    public void updateCocktailById(@RequestBody CocktailWebOutput cocktailWebOutput, @PathVariable Long id) {
        cocktailService.updateCocktail(cocktailWebOutput, id);
    }

}
