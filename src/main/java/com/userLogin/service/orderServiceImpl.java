package com.userLogin.service;

import com.userLogin.model.CustomUser;
import com.userLogin.model.Order;
import com.userLogin.model.OrderRequest;
import com.userLogin.model.OrderStatus;
import com.userLogin.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class orderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void createOrder(OrderRequest orderRequest) throws Exception {
        Order existingOrder = (Order) orderRepository.findOrderByUserId(orderRequest.toOrder().getUserId());
        if(existingOrder != null){
            throw new Exception("UserId " + orderRequest.getUserId() + " is already taken");
        }
        if(existingOrder.getStatus() == OrderStatus.TEMP){
            List<Order> tempOrders = orderRepository.findOrderByStatus(OrderStatus.TEMP);
        }else{
            orderRepository.createOrder(orderRequest.toOrder());
        }
    }

    @Override
    public Order findOrderByUserId(Long userId) {
        return (Order) orderRepository.findOrderByUserId(userId);
    }


    @Override
    public void deleteOrder(Long userId) {
        orderRepository.deleteById(userId);
    }

    @Override
    public List<Order> findByUserAndStatusIn(CustomUser customUser, List<OrderStatus> temp) {
        return null;
    }

    @Override
    public Optional<Object> findById(Long orderId) {
        return Optional.empty();
    }
}
