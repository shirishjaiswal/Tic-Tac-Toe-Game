package com.test.recipemanagementsystem.controller;

import com.test.recipemanagementsystem.model.Comment;
import com.test.recipemanagementsystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping(value = "{email}/add")
    public ResponseEntity<String> addCommentOnRecipeById(@RequestBody Comment comment, @PathVariable String email) {
        return commentService.addCommentOnRecipeById(comment, email);
    }
}
