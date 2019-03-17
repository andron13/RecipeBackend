package de.cocktail.repository;


import de.cocktail.model.Cocktail;
import java.util.List;

import de.cocktail.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends JpaRepository <Cocktail, Long> {

    Cocktail findByTitle(String title);

  // List<Cocktail> findByIngredients_title(String ingredients_title);

    List <Cocktail> findByUser_Name(String name_author);


}