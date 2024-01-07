package com.userLogin.service;

import com.userLogin.model.CustomUser;
import com.userLogin.model.CustomUserRequest;
import com.userLogin.model.ItemRequest;

public interface UserService {
    static CustomUser getUserById(Long createCustomUserId) {
        return null;
    }

    void createUser(CustomUserRequest customUser) throws Exception;
    void updateUser(CustomUserRequest customUser);

    CustomUser findUserByUsername(String username);

    void deleteUser(CustomUserRequest customUser);

}
