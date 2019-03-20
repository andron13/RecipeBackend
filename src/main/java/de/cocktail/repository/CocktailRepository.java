package de.cocktail.repository;


import de.cocktail.model.Cocktail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends JpaRepository <Cocktail, Long> {

    Cocktail findByTitle(String title);

    List<Cocktail> findByIngredientsTitle(String name);

    List <Cocktail> findByAuthor_Name(String name_author);


}