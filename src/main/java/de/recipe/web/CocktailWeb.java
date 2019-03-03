package de.recipe.web;

import java.util.List;
import lombok.Data;

@Data
public class CocktailWeb {

    private String title;

    private String announce;

    private Userweb author;

    private List <IngredientWeb> ingredients;

    private int prepTimeMinute;

    private int cookingTime;

    private List <PhotoWeb> image;

    public CocktailWeb() {
    }

    public CocktailWeb(String title, String announce, Userweb author, List <IngredientWeb> ingredients, int prepTimeMinute, int cookingTime, List <PhotoWeb> image) {
        this.title = title;
        this.announce = announce;
        this.author = author;
        this.ingredients = ingredients;
        this.prepTimeMinute = prepTimeMinute;
        this.cookingTime = cookingTime;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnounce() {
        return announce;
    }

    public void setAnnounce(String announce) {
        this.announce = announce;
    }

    public Userweb getAuthor() {
        return author;
    }

    public void setAuthor(Userweb author) {
        this.author = author;
    }

    public List <IngredientWeb> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List <IngredientWeb> ingredients) {
        this.ingredients = ingredients;
    }

    public int getPrepTimeMinute() {
        return prepTimeMinute;
    }

    public void setPrepTimeMinute(int prepTimeMinute) {
        this.prepTimeMinute = prepTimeMinute;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public List <PhotoWeb> getImage() {
        return image;
    }

    public void setImage(List <PhotoWeb> image) {
        this.image = image;
    }
}

