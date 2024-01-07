package com.userLogin.repository;

import com.userLogin.model.CustomUser;
import com.userLogin.model.CustomUserResponse;
import com.userLogin.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String USER_TABLE_NAME = "user";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createUser(CustomUser customUser) {
        String sql = "INSERT INTO " + USER_TABLE_NAME + " (firstname,lastname, email, phone, address,username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, customUser.getFirstName(), customUser.getLastName(), customUser.getEmail(), customUser.getPhone(),
                customUser.getAddress(),customUser.getUsername(), customUser.getPassword());
//        return jdbcTemplate.queryForObject("SELECT LAST_INSERTED_ID()",Long.class);
    }

    @Override
    public CustomUser findUserByUsername(String username) {
        String sql = "SELECT * FROM " + USER_TABLE_NAME + " WHERE username=?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), username);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public void deleteById(Long userId) {
        String sql = "DELETE " + USER_TABLE_NAME + " WHERE id=?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public CustomUser save(CustomUser customUser) {
        return null;
    }

    @Override
    public CustomUser findByUsername(String username) {
        return null;
    }

    @Override
    public Optional<Object> findById(Long userId) {
        return Optional.empty();
    }



    @Override
    public void deleteUser(CustomUser customUser) {

    }
    public void updateUser(CustomUser customUser){
        String sql = "UPDATE " + USER_TABLE_NAME + " firstname=?,lastname=?, email=?, phone=?, address=?,username=?, password=?  WHERE id=?";
        jdbcTemplate.update(sql, customUser.getFirstName(), customUser.getLastName(), customUser.getEmail(), customUser.getPhone(),
                customUser.getAddress(),customUser.getUsername(), customUser.getPassword());
    }
}


