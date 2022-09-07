package com.food.recipes.springbootfoodrecipes.controller;

import com.food.recipes.springbootfoodrecipes.constant.ApplicationConstants;
import com.food.recipes.springbootfoodrecipes.dto.RecipesDto;
import com.food.recipes.springbootfoodrecipes.model.MessageResponse;
import com.food.recipes.springbootfoodrecipes.service.RecipesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("api/recipes")
@Slf4j
public class RecipesMenuController {

    @Autowired
    private RecipesService recipeService;

    /** Returns all recipes information  */
    @GetMapping(value="/allrecipes",consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<RecipesDto>> getAllRecipes(){
        log.info(":::::::In getAllRecipes method :::::::::::");
        return status(HttpStatus.OK).body(recipeService.getAllRecipes());
    }

    /**
     * @param recipeDto - RecipeDto
     * @param - Recipe ID
     */
    @PostMapping(value = "/addFoodRecipeDetails",consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResponseEntity<MessageResponse> createPost(@Valid @RequestBody  RecipesDto recipeDto) {
        log.info(":::::::In create post method :::::::::::");
        recipeService.save(recipeDto);
        return new	ResponseEntity<MessageResponse>(new MessageResponse(ApplicationConstants.ADD_SUCCESS),
                HttpStatus.CREATED);
    }

    /**
     * Update recipe data based on the id
     *
     * @param recipeDto - RecipeDto
     * @param id - Recipe ID
     */
    @PutMapping(value="/updateFoodRecipeDetails/{id}",consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<MessageResponse> update(@Valid @RequestBody RecipesDto recipeDto, @PathVariable Long id) {
        log.info(":::::::In update method Controller :::::::::::");
        recipeService.update(recipeDto, id);
        return new ResponseEntity<MessageResponse>(new MessageResponse(ApplicationConstants.UPDATE_SUCCESS),HttpStatus.OK);
    }

    /**
     * deletes recipe data based on the id
     *
     * @param id - Recipe ID
     */
    @DeleteMapping(value="/deleteFoodRecipeDetails/{id}",consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<MessageResponse>  delete(@PathVariable Long id) {
        log.info(":::::::In delete method :::::::::::");
        recipeService.delete(id);
        return new ResponseEntity<MessageResponse>(new MessageResponse(ApplicationConstants.DELETE_SUCCESS),HttpStatus.OK);
    }

}
