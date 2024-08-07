package com.authentication_web.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication_web.Entities.User;
import com.authentication_web.Repository.UserRepository;
import com.authentication_web.Service.UserDataService;

import java.util.List;
import java.util.Optional;

@Service
public class UserDataServiceImpl implements UserDataService {

    @Autowired
    private UserRepository userRepository;

    public UserDataServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByID(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() == null) {
            // New user, check email uniqueness
            if (isEmailTaken(user.getEmail())) {
                throw new RuntimeException("Email already exists!");
            }
        } else {
            // Update existing user, email check can be skipped if the email has not changed
            User existingUser = userRepository.findById(user.getId()).orElse(null);
            if (existingUser != null && !existingUser.getEmail().equals(user.getEmail()) && isEmailTaken(user.getEmail())) {
                throw new RuntimeException("Email already exists!");
            }
        }
        userRepository.save(user);
    }

    private boolean isEmailTaken(String email) {
        Optional<User> userOpt = Optional.ofNullable(userRepository.findByEmail(email));
        return userOpt.isPresent() && userOpt.get().isActive();
    }


    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


}