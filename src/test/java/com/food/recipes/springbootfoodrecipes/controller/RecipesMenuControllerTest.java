package com.food.recipes.springbootfoodrecipes.controller;

import com.food.recipes.springbootfoodrecipes.dto.IngredientsTypeDto;
import com.food.recipes.springbootfoodrecipes.dto.RecipesDto;
import com.food.recipes.springbootfoodrecipes.repository.FoodRecipesRepository;
import com.food.recipes.springbootfoodrecipes.service.RecipesService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipesMenuControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    RecipesMenuController recipesMenuController;

    @Mock
    RecipesService recipesService;

    @Mock
    ModelMapper modelMapper;

    @Mock
    FoodRecipesRepository foodRecipesRepository;

    @Mock
    private HttpServletResponse response;



    @Before
    public void initialize() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recipesMenuController)
                .build();
    }

    IngredientsTypeDto ingredient_1 = new IngredientsTypeDto(1l,"Maggie Noodle", "2 packet");
    IngredientsTypeDto ingredient_2 = new IngredientsTypeDto(2l,"Chicken", "300 gms");
    List<IngredientsTypeDto> list = Arrays.asList(ingredient_1,ingredient_2);

    RecipesDto RECORD_1 = new RecipesDto(1l, "Noodles", "Veg", "20 members",list);

   String sampleJson = "{\n" +
           "    \"name\":\"Noodles\",\n" +
           "    \"category\":\"Veg\",\n" +
           "    \"servings\":\"2 members\",\n" +
           "   \"ingredients\":\"[{\"name\": \"Maggie Noodle\" , \"quantity\":\"2 packet\"},{\"name\":\"Salt\",\"quantity\":\"1 tablespoon\"},{\"name\": \"Onions\",\n" +
           "\"name\": \"Chillie\",\"quantity\": \"3\"}]\"\n" +
           "}";

    @Test
    public void testPostSuccess() throws Exception {
        ResponseEntity responseEntity=(recipesMenuController.createPost(RECORD_1));
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testDeleteSuccess() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/recipes/deleteFoodRecipeDetails/{id}}", "1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());
    }
}
