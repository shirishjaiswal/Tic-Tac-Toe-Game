package com.shirish.usermanagement.controller;

import com.shirish.usermanagement.service.UserService;
import com.shirish.usermanagement.service.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/userManagement")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<User> allUsers() {
        return userService.getUsers();
    }

    @RequestMapping (value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User userById (@PathVariable String id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@Valid @RequestBody User user) {
        boolean isAdded = userService.addUser(user);
        if (isAdded) return "User Added";
        return "User Not Added";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delUser(@PathVariable String id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) return "User Deleted";
        return "User Not Deleted";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateUser(@PathVariable String id, @RequestBody User user) {
        boolean isUpdated = userService.updateUser(id, user);
        if(isUpdated) return "User Updated";
        return "User updating Failed";
    }
}