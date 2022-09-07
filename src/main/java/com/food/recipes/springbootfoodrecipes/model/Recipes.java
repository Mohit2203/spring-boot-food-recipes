package com.food.recipes.springbootfoodrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotBlank
    private String servings;


    @OneToMany(cascade = CascadeType.ALL, mappedBy= "recipe")
    private List<IngredientsType> ingredients = new ArrayList<>();

    protected void setIngredients(List<IngredientsType> ingredient) {
        this.ingredients = ingredient;
    }

    public void addToIngredients(IngredientsType ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
    }

}
