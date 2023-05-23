package com.test.recipemanagementsystem.service;

import com.test.recipemanagementsystem.model.Recipe;
import com.test.recipemanagementsystem.model.User;
import com.test.recipemanagementsystem.repository.IRecipeRepo;
import com.test.recipemanagementsystem.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    IRecipeRepo recipeRepo;

    @Autowired
    UserService userService;

    @Autowired
    IUserRepo userRepo;

    public ResponseEntity<String> addRecipe(Recipe recipe, String email) {
        //Is User Present in Database?
        User isUserPresent = userRepo.findFirstByEmail(email);
        if (isUserPresent == null) {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
        }
        //Is User Logged In?
        if (!userService.isLoggedIn(isUserPresent.getId())) {
            return new ResponseEntity<>("Please Log In First", HttpStatus.FORBIDDEN);
        }
        recipe.setUser(isUserPresent);
        Recipe save = recipeRepo.save(recipe);
        if (save != null) {
            return new ResponseEntity<>("Recipe Saved", HttpStatus.OK);
        } else return new ResponseEntity<>("Recipe Not Saved", HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<String> removeRecipe(String id, String email) {
        //Is User Present in Database?
        User isUserPresent = userRepo.findFirstByEmail(email);
        if (isUserPresent == null) {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
        }
        //Is User Logged In?
        if (!userService.isLoggedIn(isUserPresent.getId())) {
            return new ResponseEntity<>("Please Log In First", HttpStatus.FORBIDDEN);
        }
        Recipe byId = recipeRepo.findById(Integer.parseInt(id)).get();
        if(byId.getUser().getId() != isUserPresent.getId()) {
            return new ResponseEntity<>("You Have No Such Recipe", HttpStatus.NOT_FOUND);
        }
        recipeRepo.deleteById(Integer.parseInt(id));
        return new ResponseEntity<>("Recipe Deleted",HttpStatus.OK);
    }

    public ResponseEntity<List<Recipe>> getAllRecipe() {
        List<Recipe> all = recipeRepo.findAll();
        if(!all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateRecipe(Recipe recipe, String email) {
        //Is User Present in Database?
        User isUserPresent = userRepo.findFirstByEmail(email);
        if (isUserPresent == null) {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
        }
        //Is User Logged In?
        if (!userService.isLoggedIn(isUserPresent.getId())) {
            return new ResponseEntity<>("Please Log In First", HttpStatus.FORBIDDEN);
        }
        Recipe byId = recipeRepo.findById(recipe.getId()).get();
        if(byId.getUser().getId() != isUserPresent.getId()) {
            return new ResponseEntity<>("You Have No Such Recipe", HttpStatus.NOT_FOUND);
        }
        if(recipe.getName() != null) {
            return new ResponseEntity<>("Name cannot be updated", HttpStatus.BAD_REQUEST);
        }
        if(recipe.getIngredients() != null) {
            byId.setIngredients(recipe.getIngredients());
        }
        if(recipe.getInstructions() != null) {
            byId.setInstructions(recipe.getInstructions());
        }
        Recipe save = recipeRepo.save(byId);
        if(save != null) {
            return new ResponseEntity<>("Recipe Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Recipe Not Updated", HttpStatus.BAD_REQUEST);
    }
}
