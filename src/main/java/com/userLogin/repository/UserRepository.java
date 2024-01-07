package com.userLogin.repository;

import com.userLogin.model.CustomUser;
import com.userLogin.model.CustomUserRequest;

import java.util.Optional;

public interface UserRepository{
    void createUser(CustomUser customUser);
    CustomUser findUserByUsername(String username);

    void deleteById(Long Id);

    CustomUser save(CustomUser customUser);

    CustomUser findByUsername(String username);

    Optional<Object> findById(Long userId);


    void deleteUser(CustomUser customUser);
}
