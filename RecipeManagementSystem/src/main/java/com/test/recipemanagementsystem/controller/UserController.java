package com.test.recipemanagementsystem.controller;

import com.test.recipemanagementsystem.dto.SignInRequest;
import com.test.recipemanagementsystem.dto.SignInResponse;
import com.test.recipemanagementsystem.dto.SignUpRequest;
import com.test.recipemanagementsystem.dto.SignUpResponse;
import com.test.recipemanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "home")
    public String home() {
        return "Hello Welcome";
    }

    @RequestMapping(method = RequestMethod.POST, value = "signUp")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return userService.signUp(signUpRequest);
    }

    @RequestMapping(method = RequestMethod.POST, value = "signIn")
    public SignInResponse signIn(@RequestBody SignInRequest signInRequest) {
        return userService.signIn(signInRequest);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "signOut/{email}")
    public ResponseEntity<String> signOut(@PathVariable String email) {
        return userService.signOut(email);
    }
}
