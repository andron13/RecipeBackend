package de.cocktail.repository;


import de.cocktail.model.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {

    List<Cocktail> findByTitle(String title);

    List<Cocktail> findByIngredients_Title(String name);

    List<Cocktail> findByAuthor_Name(String name_author);


}