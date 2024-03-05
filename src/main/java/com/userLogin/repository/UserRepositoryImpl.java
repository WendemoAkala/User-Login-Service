package com.userLogin.repository;

import com.userLogin.model.CustomUser;
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

    public Long createUser(CustomUser customUser) {
        String sql = "INSERT INTO " + USER_TABLE_NAME + " (firstName,lastName, email, phone, address,username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, customUser.getFirstName(), customUser.getLastName(), customUser.getEmail(), customUser.getPhone(),
                customUser.getAddress(),customUser.getUsername(), customUser.getPassword());
        return null;
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
    public Long deleteUser(CustomUser customUser) {
        String sql = "DELETE " + USER_TABLE_NAME + " (firstName,lastName, email, phone, address,username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, customUser.getFirstName(), customUser.getLastName(), customUser.getEmail(), customUser.getPhone(),
                customUser.getAddress(),customUser.getUsername(), customUser.getPassword());
        return  null;
    }

    @Override
    public Object findByUsername(String username) {
        return null;
    }

    @Override
    public Long updateUser(CustomUser customUser){
        String sql = "UPDATE " + USER_TABLE_NAME + " firstName=?,lastName=?, email=?, phone=?, address=?,username=?, password=?  WHERE id=?";
        jdbcTemplate.update(sql, customUser.getFirstName(), customUser.getLastName(), customUser.getEmail(), customUser.getPhone(),
                customUser.getAddress(),customUser.getUsername(), customUser.getPassword());
        return null;
    }

    @Override
    public Optional<Object> findById(Long id) {
        return Optional.empty();
    }
}


