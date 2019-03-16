package de.cocktail.controller;

import de.cocktail.service.CocktailService;
import de.cocktail.web.CocktailWeb;
import de.cocktail.web.CocktailWebOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
public class CocktailController {

    private final CocktailService cocktailService;

    @Autowired
    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }


    @GetMapping("/cocktails/ingredients/{name_ingredient}")
    public Response findCocktailsByIngredient(@PathVariable String name_ingredient) {
        return Response.
                status(Response.Status.OK)
                .entity(cocktailService.findByIngredientsContaining(name_ingredient))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @GetMapping("/cocktails/author/{name_author}")

    public Response findCocktailsBYAuthor(@PathVariable String name_author) {
        return Response.status(Response.Status.OK)
                .entity(cocktailService.findbyAuthor(name_author))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }


    @GetMapping("/cocktails/")
    public Response getAllCocktails() {
        return Response.status(Response.Status.OK)
                .entity(cocktailService.getAllCocktails())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();

    }

    @PostMapping("cocktails/")
    public Response saveCocktail(@RequestBody CocktailWeb cocktailWeb) {
        cocktailService.creatCocktail(cocktailWeb);
        return Response
                .status(Response.Status.ACCEPTED)
                .entity(cocktailService.findByTitle(cocktailWeb.getTitle()))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @GetMapping("/cocktails/{id}")
    public Response getCocktailById(@PathVariable Long id) {
        return Response.
                status(Response.Status.OK)
                .entity(cocktailService.getCocktailById(id))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();

    }

    @DeleteMapping("/cocktails/{id}")
    public Response deleteCocktailByID(@PathVariable Long id) {
        cocktailService.deleteCocktailById(id);
        return Response
                .ok()
                .language("en")
                .build();
    }


    @GetMapping("/cocktails/title/{title}/")
    public Response findCocktailByTitle(@PathVariable String title) {
        return Response.status(Response.Status.OK)
                .entity(cocktailService.findByTitle(title))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();

    }

    @PutMapping("/cocktails/{id}")
    public Response updateCocktailById(@RequestBody CocktailWebOutput cocktailWebOutput, @PathVariable Long id) {
        cocktailService.updateCocktail(cocktailWebOutput, id);
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

}
