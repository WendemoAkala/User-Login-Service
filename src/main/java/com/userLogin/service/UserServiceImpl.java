package com.userLogin.service;

import com.userLogin.model.CustomUser;
import com.userLogin.model.CustomUserRequest;
import com.userLogin.model.OrderRequest;
import com.userLogin.model.OrderResponse;
import com.userLogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

     @Override

    public void createUser(CustomUserRequest customUserRequest) throws Exception {
        CustomUser existingCustomUser = (CustomUser) userRepository.findByUsername(customUserRequest.getUsername());
        if(existingCustomUser != null){
        throw new Exception("Username " + customUserRequest.getUsername() + " is already taken");
    }
        userRepository.createUser(customUserRequest.toCustomUser());
    }

    @Override
    public void updateUser(CustomUserRequest customUser) {

    }

    @Override
    public CustomUser findUserByUsername(String username) {

        return userRepository.findUserByUsername(username);
    }

    @Override
    public void deleteUser(CustomUserRequest customUserRequest) {
        userRepository.deleteUser(customUserRequest.toCustomUser());
    }
}


