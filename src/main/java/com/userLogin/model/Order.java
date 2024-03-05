package com.userLogin.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Item> item;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    private String shippingAddress;
    private double totalPrice;
    @Enumerated(EnumType.STRING)
    private  OrderStatus status;



    public Order (Long id, Long userId, List<Item> item, Date orderDate, String shippingAddress, double totalPrice, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.item = item;
        this.orderDate = orderDate;
        this.shippingAddress = shippingAddress;
        this.totalPrice = totalPrice;
        this.status = status;

    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }


    public OrderStatus getStatus() {
        return status;
    }

    public Object getDate() {
        return null;
    }

    public Object getTotalPrice() {
        return null;
    }

    public Object getItems() {
        return null;
    }

    public void setItems(List<OrderItem> modifiedItems) {
    }
}
