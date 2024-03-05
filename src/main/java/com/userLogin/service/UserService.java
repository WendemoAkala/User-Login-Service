package  com.userLogin.service;

import  com.userLogin.model.CustomUser;
import  com.userLogin.model.CustomUserRequest;


public interface  UserService {
    static CustomUser getUserById(Long createCustomUserId) {
        return null;
    }

    void  createUser(CustomUserRequest customUser) throws Exception;
    void updateUser(CustomUserRequest customUser);

    CustomUser  findUserByUsername(String username);

    CustomUser findByUsername(String username);

    Long deleteUser(CustomUser customUser) throws Exception;

    CustomUser save(CustomUser customUser);
}
