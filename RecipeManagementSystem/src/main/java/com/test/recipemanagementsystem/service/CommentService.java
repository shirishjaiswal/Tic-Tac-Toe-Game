package com.test.recipemanagementsystem.service;

import com.test.recipemanagementsystem.model.Comment;
import com.test.recipemanagementsystem.model.Recipe;
import com.test.recipemanagementsystem.model.User;
import com.test.recipemanagementsystem.repository.ICommentRepo;
import com.test.recipemanagementsystem.repository.IRecipeRepo;
import com.test.recipemanagementsystem.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    ICommentRepo commentRepo;

    @Autowired
    IUserRepo userRepo;

    @Autowired
    UserService userService;

    @Autowired
    IRecipeRepo recipeRepo;

    public ResponseEntity<String> addCommentOnRecipeById(Comment comment, String email) {
        //Is User Present in Database?
        User isUserPresent = userRepo.findFirstByEmail(email);
        if (isUserPresent == null) {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
        }
        //Is User Logged In?
        if (!userService.isLoggedIn(isUserPresent.getId())) {
            return new ResponseEntity<>("Please Log In First", HttpStatus.FORBIDDEN);
        }

        Recipe byId = recipeRepo.findById(comment.getRecipe().getId()).get();
        if(byId == null) {
            return new ResponseEntity<>("No Such Recipe", HttpStatus.NOT_FOUND);
        }
        Comment save = commentRepo.save(comment);
        if(save != null) {
            return new ResponseEntity<>("Commented", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to upload", HttpStatus.OK);
    }
}
