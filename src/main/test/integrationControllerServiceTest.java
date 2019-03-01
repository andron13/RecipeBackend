import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.Main;
import de.recipe.controller.CocktailController;
import de.recipe.model.*;
import de.recipe.service.CocktailService;
import de.recipe.web.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith( SpringRunner.class )
@SpringBootTest( classes = Main.class )
@AutoConfigureMockMvc
public class integrationControllerServiceTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    CocktailController recipeController;
    @MockBean
    CocktailService cocktailService;


    private List <Ingredient> ingredientList = Arrays.asList(new Ingredient("Wodka", "4 cl Wodka"),new Ingredient("Pfirsich Likör","4 cl Pfirsich Likör"),new Ingredient("cranberry juice or Cranberry Rectal","8 cl cranberry juice or Cranberry Rectal"),new Ingredient("orange juice","8 cl of orange juice"),new Ingredient("ice","ice cubes"));


    private List <Photo> photoList = Arrays.asList(new Photo("http//google.com"));

    private Cocktail cocktail = new Cocktail("Sex on the Beach Cocktail Recipe", "So I have to say one of the best drink's I've drunk so far has become my favorite drink as well",  new User("Iurie Railean"), ingredientList, 15, 112, photoList);

    private ModelMapper modelMapper=new ModelMapper();
    private CocktailWebOutput cocktailWebOutput=modelMapper.map(cocktail,CocktailWebOutput.class);
    private CocktailWeb cocktailWeb=modelMapper.map(cocktail,CocktailWeb.class);
    private List<CocktailWebOutput>cocktailWebOutputs=Arrays.asList(cocktailWebOutput);
    private ObjectMapper objectMapper = new ObjectMapper();
    private String jason = objectMapper.writeValueAsString(cocktailWebOutput);
    private String jasonArr = objectMapper.writeValueAsString(cocktailWebOutputs);

    public integrationControllerServiceTest() throws JsonProcessingException {
    }

    @Test
    public void testGetAllCocktails() throws Exception {
        when(cocktailService.getAllCocktail()).thenReturn(cocktailWebOutputs);
        mockMvc.perform(get("/cocktails/").contentType(MediaType.APPLICATION_JSON)).
                andDo(print()).
                andExpect(status().isOk())
                .andExpect(content().
                        contentType("application/json;charset=UTF-8")).
                andExpect(content()
                        .json(jasonArr));
        verify(cocktailService, Mockito.times(1)).getAllCocktail();
    }


    @Test
    public void creatCocktailsTest() throws Exception {
        mockMvc.perform(post("/cocktails/", cocktailWebOutput)
                .contentType("application/json;charset=UTF-8")
                .content(jason)).andDo(print())
                .andExpect(status().isOk());
        verify(cocktailService, Mockito.times(1)).creatCocktail(cocktailWeb);

    }

    @Test
    public void getCocktailsByIdTest() throws Exception {
        when(cocktailService.getCocktailById(1L)).thenReturn(cocktailWebOutput);

        mockMvc.perform(get("/cocktails//{id}", 1L).contentType(MediaType.APPLICATION_JSON)).andDo(print()).
                andExpect(status().isOk())
                .andExpect(content()
                        .contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(jason));
        verify(cocktailService, Mockito.times(1)).getCocktailById(1L);

    }

    @Test
    public void delletCocktailsByIDTest() throws Exception {
        mockMvc.perform(delete("/cocktails/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status().isOk());
        verify(cocktailService, Mockito.times(1)).deleteCocktailById(1L);
    }

    @Test
    public void findByIngretientTest() throws Exception {
        when(cocktailService.findByIngredientsContaining(cocktailWebOutput.getIngredients().get(0).getName())).thenReturn(cocktailWebOutputs);
        mockMvc.perform(get("/cocktails/ingredient/{name_ingredient}", cocktail.getIngredients().get(0).getName()).contentType("application/json;charset=UTF-8")).andDo(print()).andExpect(status().isOk()).andExpect(content().json(jasonArr));
        verify(cocktailService, Mockito.times(1)).findByIngredientsContaining(cocktail.getIngredients().get(0).getName());

    }


    @Test
    public void findBYAuthorTest() throws Exception {
        when(cocktailService.findbyAuthor(cocktail.getAuthor().getName())).thenReturn(cocktailWebOutputs);
        mockMvc.perform(get("/cocktails/author/{name_author}" , "Iurie Railean").contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status()
                        .isOk()).
                andExpect(content()
                        .json(jasonArr));
        verify(cocktailService, Mockito.times(1)).findbyAuthor(cocktail.getAuthor().getName());
    }

    @Test
    public void updateRecipeByIdTest() throws Exception {


        mockMvc.perform(put("/cocktails/{id}",1).contentType(MediaType.APPLICATION_JSON).content(jason)
                .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status()
                        .isOk());
        verify(cocktailService, Mockito.times(1)).updateCocktail(cocktailWebOutput,1L);
    }
}
