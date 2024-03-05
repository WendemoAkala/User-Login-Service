package com.userLogin.controller;

import com.userLogin.model.CustomUser;
import com.userLogin.model.CustomUserRequest;
import com.userLogin.repository.UserRepository;
import com.userLogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/public/users")
public class UserController {

    @Autowired
    private UserService userService ;
//    @Autowired
//    private UserRepository;

    @PostMapping("/create")
    @CrossOrigin
    public ResponseEntity<?> createUser(@RequestBody CustomUserRequest customUser){
        try{
            userService.createUser(customUser);
            return null;
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PostMapping("/register")
    public CustomUser registerUser(@RequestBody CustomUser customUser) {
        // Implement user registration logic
        // Check if the username or email is not already taken
        // Encrypt the password before saving
        return userService.save(customUser);
    }

    @PostMapping("/login")
    public CustomUser loginUser(@RequestBody CustomUser loginRequest) {
        // Implement user login logic
        // Check if the username and password match
        // Return user details if successful
        return userService.findByUsername(loginRequest.getUsername());
    }

    @DeleteMapping("/delete/{id}")
    public Long deleteUser(@RequestParam CustomUser customUser) throws Exception {
//            userService.deleteUser(id);
          userService.deleteUser(customUser);
          return null;
    }

}
