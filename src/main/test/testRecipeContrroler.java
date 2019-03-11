/*import com.sun.org.glassfish.external.arc.Taxonomy;
import de.Main;
import de.cocktail.controller.CocktailController;
import de.cocktail.model.*;
import de.cocktail.web.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.telegram.telegrambots.meta.api.objects.Video;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith( SpringRunner.class )
@SpringBootTest( classes = Main.class )
@AutoConfigureMockMvc
public class testRecipeContrroler {


    private List <Ingredient> ingredientList = Arrays.asList(new Ingredient("pepper", "salt and freshly ground pepper"));
    private List <RecepiStep> recepiSteps = Arrays.asList(new RecepiStep("1", "Heat the oil in a large", 1L));
    private List <Taxonomy> taxonomyList = Arrays.asList(new Taxonomy("Italia"));
    private List <Photo> photoList = Arrays.asList(new Photo("1https://hips.hearstapps.com/del.h-cdn.co/assets/17/03/2560x1706/gallery-1484784941-white-cheddar-carbonaral1.jpg?resize=768:*"));
    private List <Video> videoList = Arrays.asList(new Video("1https://www.youtube.com/embed/D_2DBLAt57c"));

    private Cocktail cocktail = new Cocktail("Spaghetti BologneseTuna pasta sauce", "The easiest pasta dish you will ever make with just 5 ingredients in 15 min, loaded with Parmesan and crisp bacon goodness!", new Date(), new User("Iurie Railean1"), ingredientList, recepiSteps, taxonomyList, 15, 112, photoList, videoList);

    private List <IngredientWeb> ingredientWebsList = Arrays.asList(new IngredientWeb("pepper", "salt and freshly ground pepper"));
    private List <RecepiStepWeb> recepiStepsWeb = Arrays.asList(new RecepiStepWeb("1", "Heat the oil in a large"));
    private List <TaxonomyWeb> taxonomyWebsList = Arrays.asList(new TaxonomyWeb("Italia"));
    private List <PhotoWeb> photoWebsList = Arrays.asList(new PhotoWeb("1https://hips.hearstapps.com/del.h-cdn.co/assets/17/03/2560x1706/gallery-1484784941-white-cheddar-carbonaral1.jpg?resize=768:*"));
    private List <VideoWeb> videoWebListList = Arrays.asList(new VideoWeb("1https://www.youtube.com/embed/D_2DBLAt57c"));
    private CocktailWebOutput recipeWebOutput = new CocktailWebOutput(1L, "Spaghetti BologneseTuna pasta sauce", "The easiest pasta dish you will ever make with just 5 ingredients in 15 min, loaded with Parmesan and crisp bacon goodness!",  new Userweb("Iurie Railean1"), ingredientWebsList, recepiStepsWeb, taxonomyWebsList, 15, 112, photoWebsList, videoWebListList);
    private List <CocktailWebOutput> recipeWebOutputs = Arrays.asList();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
   private CocktailController recipeController;

    @Test
    public void testGetAllCocktails() throws Exception {

    //    when(recipeController.getAllCocktails()).thenReturn(list);

        mockMvc.perform(get("/recipes/")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(content().json("[{\"title\":\"title\"}]"))
        .andExpect(content().json("[{\"id\":1,\"title\":\"title\",\"announce\":\"String announce\",\"name\":{\"id\":1,\"title\":\"fgd\"},\"ingredients\":[{\"id\":1,\"title\":\"vfd\",\"description\":\"gf\"}],\"instructions\":[{\"id\":1,\"stepTitle\":\"ds\",\"stepDescription\":\"fd\"}],\"tags\":[{\"id\":1,\"nameTaxonomy\":\"ad\"}],\"comment\":{\"id\":1,\"comment\":\"ds\"},\"prepTimeMinute\":23,\"cookingTime\":23,\"rate\":{\"id\":1,\"rate\":\"sdf\"}}]\n"));
        Mockito.verify(recipeController, Mockito.times(1)).getAllCocktails();

    }
    @Test
    public void getCocktailsByIdTest() throws Exception {
when(recipeController.getCocktailById(1L)).thenReturn(recipeWebOutput);

        mockMvc.perform(get("/recipes/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status()
                        .isOk())
                .andExpect(content().json("{\"title\":\"title\"}"))
                .andExpect(content().json("{\"id\":1,\"title\":\"title\",\"announce\":\"String announce\",\"name\":{\"id\":1,\"title\":\"fgd\"},\"ingredients\":[{\"id\":1,\"title\":\"vfd\",\"description\":\"gf\"}],\"instructions\":[{\"id\":1,\"stepTitle\":\"ds\",\"stepDescription\":\"fd\"}],\"tags\":[{\"id\":1,\"nameTaxonomy\":\"ad\"}],\"comment\":{\"id\":1,\"comment\":\"ds\"},\"prepTimeMinute\":23,\"cookingTime\":23,\"rate\":{\"id\":1,\"rate\":\"sdf\"}}\n"));
        Mockito.verify(recipeController, Mockito.times(1)).getCocktailById(1L);

    }
    @Test
    public void testSearcIngredient() throws Exception {
       // when(recipeController.findByIngredientController("vfd")).thenReturn(list);

        mockMvc.perform(get("/searhc/ingredient/{name_ingredient}","vfd")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status()
                        .isOk())
                .andExpect(content().json("[{\"title\":\"title\"}]"))
                .andExpect(content().json("[{\"id\":1,\"title\":\"title\",\"announce\":\"String announce\",\"name\":{\"id\":1,\"title\":\"fgd\"},\"ingredients\":[{\"id\":1,\"title\":\"vfd\",\"description\":\"gf\"}],\"instructions\":[{\"id\":1,\"stepTitle\":\"ds\",\"stepDescription\":\"fd\"}],\"tags\":[{\"id\":1,\"nameTaxonomy\":\"ad\"}],\"comment\":{\"id\":1,\"comment\":\"ds\"},\"prepTimeMinute\":23,\"cookingTime\":23,\"rate\":{\"id\":1,\"rate\":\"sdf\"}}]\n"));
        Mockito.verify(recipeController, Mockito.times(1)).findByIngredient("vfd");

    }
    @Test
    public void testSearcbyAuthor() throws Exception {
      //  when(recipeController.findCocktailsBYAuthor("fgd")).thenReturn(list);

        mockMvc.perform(get("/search/byAuthor/{string}","fgd")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status()
                        .isOk())
                .andExpect(content().json("[{\"title\":\"title\"}]"))
                .andExpect(content().json("[{\"id\":1,\"title\":\"title\",\"announce\":\"String announce\",\"name\":{\"id\":1,\"title\":\"fgd\"},\"ingredients\":[{\"id\":1,\"title\":\"vfd\",\"description\":\"gf\"}],\"instructions\":[{\"id\":1,\"stepTitle\":\"ds\",\"stepDescription\":\"fd\"}],\"tags\":[{\"id\":1,\"nameTaxonomy\":\"ad\"}],\"comment\":{\"id\":1,\"comment\":\"ds\"},\"prepTimeMinute\":23,\"cookingTime\":23,\"rate\":{\"id\":1,\"rate\":\"sdf\"}}]\n"));
        Mockito.verify(recipeController, Mockito.times(1)).findCocktailsBYAuthor("fgd");

    }



}*/
