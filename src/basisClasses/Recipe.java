package basisClasses;

import java.util.Date;
import java.util.List;

public class Recipe {
    private String title;
    private String announce;
    private Date publicationDate;
    private Person author;
    private List<Ingredient> ingredients;
    private List<RecepiSteps> instructions;
    private List<Taxonomy> tags;
    private Comments comments;
    private Long id;
    private int prepTimeMinute;
    private int cookingTime;
    private Rate rate;

}
