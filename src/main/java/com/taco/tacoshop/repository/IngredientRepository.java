package com.taco.tacoshop.repository;


import com.taco.tacoshop.tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {


}
