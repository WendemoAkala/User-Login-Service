package com.userLogin.repository;

import com.userLogin.model.CustomUser;
import com.userLogin.model.Order;
import com.userLogin.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    void createOrder(Order order);
    List<Order> findByUserId(Long userId);

    List<Order> findOrderByStatus(OrderStatus status);

    default List<Order> findOrderByUserId(Long userId) {
        return null;
    }

    default void deleteById(Long userId) {
    }
    List<Order> findByUserAndStatusIn(CustomUser customUser, List<OrderStatus> statuses);

    Order save(Order existingOrder);

    Optional<Object> findById(Long orderId);
}
