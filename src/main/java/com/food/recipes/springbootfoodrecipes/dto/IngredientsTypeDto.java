package com.food.recipes.springbootfoodrecipes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientsTypeDto {
    @JsonIgnore
    private Long id;

    @NotEmpty(message= "Ingredient Name cannot be empty")
    @Size(min = 3, message = "Ingredient Name should be at least 3 characters")
    private String name;

    @NotEmpty(message= "Quantity cannot be empty")
    private String quantity;
}
