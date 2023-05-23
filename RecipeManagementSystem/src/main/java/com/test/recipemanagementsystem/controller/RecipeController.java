package com.test.recipemanagementsystem.controller;

import com.test.recipemanagementsystem.model.Recipe;
import com.test.recipemanagementsystem.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @PostMapping (value = "{email}/recipe/add")
    public ResponseEntity<String> addRecipe(@RequestBody Recipe recipe, @PathVariable String email) {
        System.out.println(email);
        return recipeService.addRecipe(recipe, email);
    }

    @DeleteMapping(value = "{email}/recipe/remove/{id}")
    public ResponseEntity<String> removeRecipe(@PathVariable String id, @PathVariable String email) {
        return recipeService.removeRecipe(id, email);
    }

    @GetMapping(value = "recipe")
    public ResponseEntity<List<Recipe>> getRecipes() {
        return recipeService.getAllRecipe();
    }

    @PutMapping(value = "{email}/recipe/update")
    public ResponseEntity<String> updateRecipe(@RequestBody Recipe recipe, @PathVariable String email) {
        return recipeService.updateRecipe(recipe, email);
    }
}
