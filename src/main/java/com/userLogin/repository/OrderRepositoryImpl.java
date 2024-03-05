package com.userLogin.repository;

import com.userLogin.model.CustomUser;
import com.userLogin.model.Order;
import com.userLogin.model.OrderStatus;
import com.userLogin.repository.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;



@Repository
public class OrderRepositoryImpl implements  OrderRepository{
    private static final String ORDER_TABLE_NAME = "orders";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public void createOrder(Order order) {
        String sql1 = "INSERT INTO " + ORDER_TABLE_NAME + " (userId, item, orderDate, shippingAddress, totalPrice, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql1, order.getUserId(), order.getItems(), order.getDate(), order.getShippingAddress(), order.getTotalPrice(),
                order.getStatus());
    }



    @Override
    public List<Order> findByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Order> findOrderByStatus( OrderStatus status) {
        String sql = "SELECT * FROM " + ORDER_TABLE_NAME + " WHERE status=?";
        try {
            return jdbcTemplate.query(sql,  orderMapper, status.name());
        } catch (EmptyResultDataAccessException error) {
            System.out.println("Empty Data Warning");
            return null;
        }
    }

    @Override
    public List<Order> findOrderByUserId(Long userId) {
        String sql = "SELECT * FROM " + ORDER_TABLE_NAME + " WHERE userId=?";
        try {
            return Collections.singletonList(jdbcTemplate.queryForObject(sql, new OrderMapper(), userId));
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public void deleteById(Long customUser) {

    }

    @Override
    public List<Order> findByUserAndStatusIn(CustomUser customUser, List<OrderStatus> statuses) {
        return null;
    }

    @Override
    public Order save(Order existingOrder) {
        return null;
    }

    @Override
    public Optional<Object> findById(Long orderId) {
        return Optional.empty();
    }

}
