package com.userLogin.repository.mapper;

import com.userLogin.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class FavoriteMapper implements RowMapper<Favorite> {
    @Override
    public Favorite mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Favorite(
                rs.getLong("id"),
                rs.getLong("userId"),
                (List<Item>) rs.getObject("item")

        );
    }
}
