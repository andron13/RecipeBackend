package de.cocktail.controller;

import de.cocktail.service.CocktailService;
import de.cocktail.web.CocktailWeb;
import de.cocktail.web.CocktailWebOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping(value = "cocktails")
public class CocktailController {

    private final CocktailService cocktailService;

    @Autowired
    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }


    @RequestMapping(method = RequestMethod.GET, params = "name_ingredient")
    public ResponseEntity findCocktailsByIngredient(@RequestParam("name_ingredient") String name_ingredient) {
        return ResponseEntity.ok().body(cocktailService.
                findByIngredientsContaining(name_ingredient));
    }

    @RequestMapping(method = RequestMethod.GET, params = "name_author")
    public ResponseEntity findCocktailsBYAuthor(@QueryParam("name_author") String name_author) {
        return ResponseEntity.
                ok().body(cocktailService.
                findbyAuthor(name_author));
    }


    @GetMapping
    public ResponseEntity getAllCocktails() {
        return ResponseEntity.
                ok().body(cocktailService.getAllCocktail());

    }

    @PostMapping
    public ResponseEntity saveCocktail(@RequestBody CocktailWeb cocktailWeb, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok().body(cocktailService.creatCocktail(cocktailWeb, userDetails));
    }

    @RequestMapping(method = RequestMethod.GET, params = "cocktail_id")
    public ResponseEntity getCocktailById(@RequestParam("cocktail_id") Long cocktail_id) {
        return ResponseEntity.
                ok().
                body(cocktailService.getCocktailById(cocktail_id));

    }

    @RequestMapping(method = RequestMethod.DELETE, params = "cocktail_id")
    public void deleteCocktailByID(@RequestParam("cocktail_id") Long cocktail_id) {
        cocktailService.deleteCocktailById(cocktail_id);
    }


    @RequestMapping(method = RequestMethod.GET, params = "cocktail_title")
    public ResponseEntity findCocktailByTitle(@RequestParam("cocktail_title") String cocktail_title) {
        return ResponseEntity.ok().body(cocktailService.findByTitle(cocktail_title));
    }

    @RequestMapping(method = RequestMethod.PUT, params = "cocktail_id")
    public ResponseEntity updateCocktailById(@RequestBody CocktailWebOutput cocktailWeb,
                                             @QueryParam("cocktail_id") Long cocktail_id,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        cocktailService.updateCocktail(cocktailWeb, cocktail_id, userDetails);
        return ResponseEntity.ok().body(cocktailService.getCocktailById(cocktail_id));
    }
}