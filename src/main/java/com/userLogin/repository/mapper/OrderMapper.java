package com.userLogin.repository.mapper;

import com.userLogin.model.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
@Component
public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Object OrderStatus;
        return new Order(
                (Long) rs.getObject("id"),
                (Long) rs.getObject("userId"),
                (List<Item>) rs.getObject("item"),
                (Date) rs.getDate("orderDate"),
                rs.getString("shippingAddress"),
                rs.getDouble("totalPrice"),
                (OrderStatus) rs.getObject("status")
        );
    }
}
