package com.userLogin.repository.mapper;

import com.userLogin.model.CustomUser;
import com.userLogin.model.Item;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class UserMapper implements RowMapper<CustomUser> {

    @Override
    public CustomUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CustomUser(
                rs.getLong("id"),
                rs.getString("lastName"),
                rs.getString("firstName"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("address"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("roles"),
                rs.getString("permissions")
        );
    }
}
