package com.food.recipes.springbootfoodrecipes.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;


@Data
@Entity
@NoArgsConstructor
public class IngredientsType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String quantity;

    @ManyToOne
    @JoinColumn(name="ingredient_id", nullable=false)
    private Recipes recipe;

    public IngredientsType(Long id, String name, String quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }
}
