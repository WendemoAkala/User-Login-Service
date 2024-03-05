
package com.userLogin.service ;

import  com.userLogin.model.CustomUser;
import  com.userLogin.model.CustomUserRequest;
import  com.userLogin.repository.UserRepository;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.jdbc.core.JdbcTemplate;
import  org.springframework.stereotype.Service;

@Service
 public class UserServiceImpl implements UserService {

    @Autowired
    private UserService userService;
     @Autowired
     private UserRepository userRepository;
@Autowired
    private JdbcTemplate jdbcTemplate;
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
    public Long deleteUser(CustomUser customUser) throws Exception {
        CustomUser existingCustomUser = (CustomUser) userRepository.findByUsername(customUser.getUsername());
        if(existingCustomUser != null){
            throw new Exception("Username " + customUser.getUsername() + " is already taken");
        }
        userRepository.deleteUser(customUser);
        return null;
        }

    @Override
    public CustomUser save(CustomUser customUser) {
        return null;
    }
    @Override
    public CustomUser findByUsername(String username) {
        return null;
    }
}
