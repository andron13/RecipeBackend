import de.Main;
import de.cocktail.service.CocktailService;
import de.cocktail.web.CocktailWebOutput;
import de.cocktail.web.IngredientWeb;
import de.cocktail.web.UserWeb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)

public class CocktailControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CocktailService cocktailService;

    private CocktailWebOutput cocktailWebOutput = new CocktailWebOutput(1L, COCKTAIL_NAME, "So I have to say one of the best drink's I've drunk so far has become my favorite drink as well", null, new UserWeb(COCKTAIL_AUTHOR_NAME), Arrays.asList(new IngredientWeb(INGREDIENT_NAME, "4 cl Wodka")), 1, 1, null);
    private List<CocktailWebOutput> cocktailWebOutputList = Collections.singletonList(cocktailWebOutput);


    private final static String COCKTAIL_NAME = "Sex on the Beach Cocktail Recipe";
    private final static String COCKTAIL_AUTHOR_NAME = "Iurie Railean";
    private final static String INGREDIENT_NAME = "Wodka";
    private final static String COCKTAIL_WEB_ARRAY_JSON = "[{\"id\":1,\"title\":\"Sex on the Beach Cocktail Recipe\",\"announce\":\"So I have to say one of the best drink's I've drunk so far has become my favorite drink as well\",\"publicationDate\":null,\"author\":{\"name\":\"Iurie Railean\"},\"ingredients\":[{\"title\":\"Wodka\",\"description\":\"4 cl Wodka\"}],\"prepTimeMinute\":1,\"cookingTime\":1,\"image\":null}]";
    private final static String COCKTAIL_WEB_JSON = "{\"id\":1,\"title\":\"Sex on the Beach Cocktail Recipe\",\"announce\":\"So I have to say one of the best drink's I've drunk so far has become my favorite drink as well\",\"publicationDate\":null,\"author\":{\"name\":\"Iurie Railean\"},\"ingredients\":[{\"title\":\"Wodka\",\"description\":\"4 cl Wodka\"}],\"prepTimeMinute\":1,\"cookingTime\":1,\"image\":null}";

    @Test
    public void findCocktailsByIngredientsTitleTest() throws Exception {
        when(cocktailService.getCocktailByIngredient(INGREDIENT_NAME))
                .thenReturn(cocktailWebOutputList);

        mockMvc.perform(get("/cocktails/ingredients/{name_ingredient}", INGREDIENT_NAME)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(content().string(COCKTAIL_WEB_ARRAY_JSON));

        verify(cocktailService, Mockito.times(1))
                .getCocktailByIngredient(INGREDIENT_NAME);
    }

    @Test
    public void getCocktailByIdTest() throws Exception {
        when(cocktailService
                .getCocktailById(1L)).thenReturn(cocktailWebOutput);

        mockMvc.perform(get("/cocktails/{id}",1L)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(COCKTAIL_WEB_JSON));

        verify(cocktailService, Mockito.times(1)).getCocktailById(1L);
    }

    @Test
    public void findByAuthor_NameTest() throws Exception {
        when(cocktailService.getCocktailByAuthor(COCKTAIL_AUTHOR_NAME)).thenReturn(cocktailWebOutputList);


        mockMvc.perform(get("/cocktails/author/{name_author}", COCKTAIL_AUTHOR_NAME)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(COCKTAIL_WEB_ARRAY_JSON));

        verify(cocktailService, Mockito.times(1))
                .getCocktailByAuthor(COCKTAIL_AUTHOR_NAME);
    }

    @Test
    public void findByTitleTest() throws Exception {
        when(cocktailService.getCocktailByTitle(COCKTAIL_NAME)).thenReturn(cocktailWebOutputList);

        mockMvc.perform(get("/cocktails/title/{title}", COCKTAIL_NAME)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(content().string(COCKTAIL_WEB_ARRAY_JSON));

        verify(cocktailService, Mockito.times(1))
                .getCocktailByTitle(COCKTAIL_NAME);
    }
    @Test
    public void deleteCocktailByIdTest() throws Exception {
        mockMvc.perform(delete("/cocktails/{id}", 1L)
                .contentType("application/json;charset=UTF-8")).andDo(print())
                .andExpect(status().isOk());
        verify(cocktailService, Mockito.times(1)).deleteCocktailById(1L);
    }
}