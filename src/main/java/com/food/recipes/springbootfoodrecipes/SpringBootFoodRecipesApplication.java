package com.food.recipes.springbootfoodrecipes;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@Import(ModelMapper.class)
public class SpringBootFoodRecipesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFoodRecipesApplication.class, args);

	}
}
