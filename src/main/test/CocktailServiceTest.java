/*import de.cocktail.model.Cocktail;
import de.cocktail.repository.CocktailRepository;
import de.cocktail.service.CocktailService;
import de.cocktail.web.CocktailWebOutput;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = de.cocktail.service.CocktailService.class)


@AutoConfigureMockMvc


public class CocktailServiceTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private CocktailService cocktailService;

    @MockBean
    CocktailRepository cocktailRepository;

    @Test
    public void serviceGetAllTest() {
        List<CocktailWebOutput> listService = cocktailController.getAllCocktails();
        List<Cocktail> listrepo = coctailRepository.findAll();
        Assert.assertEquals(listService.size(), listrepo.size());
        Assert.assertEquals(listrepo, listService);

    }

    @Test
    public void getCocktailsByIdTest() throws Exception {
        Cocktail cocktail = cocktailRepository.getOne(1L);
        mockMvc.perform(get("/cocktails/{id}", 1L).contentType(MediaType.APPLICATION_JSON)).andDo(print()).
                andExpect(status().isOk());
        Assertions.assertEquals(cocktailService.getCocktailById(1L), cocktail);


    }
}
*/