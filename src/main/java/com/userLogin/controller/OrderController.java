package com.userLogin.controller;

import com.userLogin.model.*;
import com.userLogin.repository.ItemRepository;
import com.userLogin.repository.OrderRepository;
import com.userLogin.repository.UserRepository;
import com.userLogin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/public/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        // Return a list of orders for the logged-in user
        CustomUser customUser = (CustomUser) userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        return orderService.findByUserAndStatusIn(customUser, List.of(OrderStatus.TEMP, OrderStatus.CLOSE));
    }

    @GetMapping("/{orderId}")
    public Order getOrderDetails(@PathVariable Long orderId) {
        // Return details of a specific order
        return (Order) orderService.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @PutMapping("/modify/{orderId}")
    public Order modifyOrder(@PathVariable Long orderId, @RequestBody Order modifiedOrder) {
        // Modify a pending order (e.g., add/remove items)
        Order existingOrder = (Order) orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        // Validate that the order is pending
        if (existingOrder.getStatus() == OrderStatus.TEMP) {
            // Implement logic to modify the order, update items, quantity, etc.
            List<OrderItem> modifiedItems = (List<OrderItem>) modifiedOrder.getItems();

            // Validate and update stock for each item in the order
            for (OrderItem modifiedItem : modifiedItems) {
                Item item = modifiedItem.getItem();
                int quantityOrdered = modifiedItem.getQuantity();

                // Validate that there is sufficient stock
                if (quantityOrdered <= item.getStock()) {
                    // Update item stock
                    item.setStock(item.getStock() - quantityOrdered);
                    itemRepository.save(item);
                } else {
                    throw new RuntimeException("Insufficient stock for item: " + item.getTitle());
                }
            }

            // Update other fields as needed
            existingOrder.setItems(modifiedItems);
            // Update other fields as needed
            return orderRepository.save(existingOrder);
        } else {
            throw new RuntimeException("Cannot modify a closed order");
        }
    }
    @PostMapping("/create")
    public Order createOrder(@RequestParam Long userId, @RequestBody List<OrderItem> orderItems, @RequestParam String shippingAddress) {
        // Create a new order
        CustomUser customUser = (CustomUser) userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUserId(Long.valueOf(userId));
        order.setOrderDate(new Date());
        order.setShippingAddress(shippingAddress);

        // Calculate total price based on order items
        double totalPrice = orderItems.stream().mapToDouble(item -> item.getQuantity() * item.getItem().getPrice()).sum();
        order.setTotalPrice(totalPrice);

        // Set the initial order status to TEMP
        order.setStatus(OrderStatus.TEMP);

        // Save the order and update item stock
        orderRepository.save(order);
        updateItemStock(orderItems);

        return order;
    }

    @GetMapping("/history/{userId}")
    public List<Order> getOrderHistory(@PathVariable Long userId) {
        // Return order history for the logged-in user
        CustomUser customUser = (CustomUser) orderService.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findByUserId(userId);
    }

    @PutMapping("/updateStatus/{orderId}")
    public Order updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus status) {
        // Update the status of a specific order
        Order order = (Order) orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        // Validate that the order is pending before updating the status
        if (order.getStatus() == OrderStatus.TEMP) {
            order.setStatus(status);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Cannot update status for a closed order");
        }
    }

    private void updateItemStock(List<OrderItem> orderItems) {
        // Update item stock based on the items in the order
        for (OrderItem orderItem : orderItems) {
            Item item = orderItem.getItem();
            int quantityOrdered = orderItem.getQuantity();

            // Validate that there is sufficient stock
            if (quantityOrdered <= item.getStock()) {
                // Update item stock
                item.setStock(item.getStock() - quantityOrdered);
                itemRepository.save(item);
            } else {
                throw new RuntimeException("Insufficient stock for item: " + item.getTitle());
            }
        }
    }


    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long userId) {
        try {
            orderService.deleteOrder(userId);
            return ResponseEntity.ok("Order deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting order");
        }
    }
}
