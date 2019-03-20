package de.cocktail.web;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class CocktailWebOutput {

    private Long id;

    private String title;

    private String announce;

    private Date publicationDate;

    private Userweb author;

    private List<IngredientWeb >ingredients;

    private int prepTimeMinute;

    private int cookingTime;

    private List <PhotoWeb> image;

    public CocktailWebOutput() {
    }


}
