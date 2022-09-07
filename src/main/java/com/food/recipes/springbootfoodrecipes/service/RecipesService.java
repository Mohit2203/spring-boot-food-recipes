package com.food.recipes.springbootfoodrecipes.service;

import com.food.recipes.springbootfoodrecipes.dto.RecipesDto;
import com.food.recipes.springbootfoodrecipes.exception.ResourceNotFoundException;
import com.food.recipes.springbootfoodrecipes.model.IngredientsType;
import com.food.recipes.springbootfoodrecipes.model.Recipes;
import com.food.recipes.springbootfoodrecipes.repository.FoodRecipesRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class RecipesService {

    @Autowired
    private FoodRecipesRepository recipeRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final String NOT_FOUND = "Not Found!";

    // This method retrieves all the available Recipe data from the database
    public List<RecipesDto> getAllRecipes() {
        log.debug(":::::::In getAllRecipes method :::::::::::");
        return recipeRepository.findAll()
                .stream().map(this::convertEntityToDto).collect(toList());
    }

    // This method saves the recipe & ingredients data in the database.
    public void save(RecipesDto recipesDto) {
        log.debug(":::::::In save method :::::::::::");

        List<IngredientsType> ingredientsTypeList=recipesDto.
                getIngredients().stream().map(t -> {return modelMapper.map(t, IngredientsType.class);
        }).collect(Collectors.toList());

        Recipes recipe=modelMapper.map(recipesDto, Recipes.class);
        for(IngredientsType ingredients: ingredientsTypeList ) {
            recipe.addToIngredients(ingredients);
        }
        recipeRepository.save(recipe);
    }
    public void update(RecipesDto recipesDto, Long id) {
        log.info(":::::::In update method Service:::::::::::");

        Recipes recipe = recipeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException());
        List<IngredientsType> ingredientList = recipe.getIngredients();
        recipe.setName(recipesDto.getName());
        recipe.setServings(recipesDto.getServings());

        for(int index=0;index<ingredientList.size();index++) {
            ingredientList.get(index).setName(recipesDto.getIngredients().get(index).getName());
            ingredientList.get(index).setQuantity(recipesDto.getIngredients().get(index).getQuantity());
        }
        recipeRepository.save(recipe);
    }

    // This method deletes the Recipe data from the database based on the Id
    public void delete(Long id) {
        log.info(":::::::In delete method :::::::::::");
        recipeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException());
        recipeRepository.deleteById(id);
    }
    // This method converts the Recipe view object to RecipeDto object.
    public RecipesDto convertEntityToDto(Recipes recipe) {
        log.debug(":::::::In convertEntityToDto method :::::::::::");
        return modelMapper.map(recipe, RecipesDto.class);
    }
}
