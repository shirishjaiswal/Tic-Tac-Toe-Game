package com.shirish.usermanagement.repository;

import com.shirish.usermanagement.service.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepo {
    private List<User> userList = new ArrayList<>();

    public List<User> getUsers() {
        return userList;
    }

    public boolean addUser (User user) {
        return userList.add(user);
    }

    public boolean deleteUser (User user) {
        return userList.remove(user);
    }
}
