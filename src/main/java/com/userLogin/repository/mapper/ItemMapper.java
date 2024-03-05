package com.userLogin.repository.mapper;

import com.userLogin.model.CustomUser;
import com.userLogin.model.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper<Item> {
  @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Item(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("photoUrl"),
                rs.getDouble("price"),
                rs.getInt("stockCount")

        );
    }
}
