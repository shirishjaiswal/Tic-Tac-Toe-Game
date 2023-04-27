package com.shirish.usermanagement.service;

import com.shirish.usermanagement.repository.UserRepo;
import com.shirish.usermanagement.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    private List<User> userList;

    private User byId (String id) {
        userList = userRepo.getUsers();
        User user = null;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(id)) {
                user = userList.get(i);
            }
        }
        return user;
    }

    public User getUser(String id) {
        return byId(id);
    }

    public boolean addUser (User user) {
        return userRepo.addUser(user);
    }

    public boolean deleteUser (String id) {
        User user = byId(id);
        return userRepo.deleteUser(user);
    }

    public List<User> getUsers() {
        return userRepo.getUsers();
    }

    public boolean updateUser(String id, User user) {
        User toFind = byId(id);
        if(toFind != null) {
            user.setId(toFind.getId());
            if(user.getName() == null) user.setName(toFind.getName());
            if(user.getEmail() == null) user.setEmail(toFind.getEmail());
            if(user.getMobileNo() == null) user.setMobileNo(toFind.getMobileNo());
            if(user.getDateOfBirth() == null) user.setDateOfBirth(toFind.getDateOfBirth());
        }
        boolean isDeleted = deleteUser(id);
        boolean isAdded = false;
        if(isDeleted) {
            isAdded = addUser(user);
        }
        return isAdded;
    }
}
