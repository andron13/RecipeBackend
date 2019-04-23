package de.cocktail.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CocktailWebOutput {

    private Long id;

    private String title;

    private String announce;

    private Date publicationDate;

    private Userweb author;

    private List<IngredientWeb> ingredients;

    private int prepTimeMinute;

    private int cookingTime;

    private PhotoWeb image;


}
