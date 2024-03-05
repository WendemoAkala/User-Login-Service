package com.userLogin.repository;

import com.userLogin.model.CustomUser;
import com.userLogin.model.CustomUserRequest;

import java.util.Optional;

public interface UserRepository{
    Long createUser(CustomUser customUser);

    CustomUser findUserByUsername(String username);


    Long deleteUser(CustomUser customUser);

    Object findByUsername(String username);

    Long updateUser(CustomUser customUser);

    Optional<Object> findById(Long id);
}
