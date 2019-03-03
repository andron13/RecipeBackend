package de.recipe.repository;


import de.recipe.model.Cocktail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends JpaRepository <Cocktail, Long> {

    Cocktail findByTitle(String title);

    List <Cocktail> findByIngredientsNameIn(String name);

    List <Cocktail> findByAuthorName(String name_author);


}