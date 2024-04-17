package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserService {

    //user operations

    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(String userId);

//    void deleteUser(String userId);
//
//    User updateUser(User user);


}
