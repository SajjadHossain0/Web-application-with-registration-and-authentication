package com.authentication_web.Service;

import java.util.List;

import com.authentication_web.Entities.User;

public interface UserDataService {

    List<User> getAllUsers();

    User getUserByID(Long userId);

    void saveUser(User user);

    void deleteUser(Long userId);

}
