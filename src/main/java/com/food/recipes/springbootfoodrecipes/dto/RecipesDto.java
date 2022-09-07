package com.food.recipes.springbootfoodrecipes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipesDto {

    @JsonIgnore
    private Long id;

    @NotEmpty(message= "Name of Recipe cannot be empty")
    @Size(min = 4, message = "Recipe Name should have at least 4 characters long")
    private String name;

    @NotEmpty(message= "Category cannot be empty")
    private String category;

    @NotEmpty(message= "Servings cannot be empty")
    @Size(min = 1, message = "Recipe Servings should have at least 1 person")
    private String servings;

    public List<IngredientsTypeDto> ingredients = new ArrayList<>();
}
