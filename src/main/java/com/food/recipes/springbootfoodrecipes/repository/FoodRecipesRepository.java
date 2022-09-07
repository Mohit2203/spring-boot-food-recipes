package com.food.recipes.springbootfoodrecipes.repository;

import com.food.recipes.springbootfoodrecipes.model.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRecipesRepository extends JpaRepository<Recipes, Long> {
}
