package com.userLogin.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class OrderRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Long userId;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> item;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    private String shippingAddress;
    private double totalPrice;
    private  OrderStatus status;

    public OrderRequest() {
    }

    public OrderRequest(Long id, Long userId, List<Item> item, Date orderDate, String shippingAddress, double totalPrice, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.item = item;
        this.orderDate = orderDate;
        this.shippingAddress = shippingAddress;
        this.totalPrice = totalPrice;
        this.status = status;
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

    public void setItem(List<Item> items) {
        this.item = item;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Order toOrder(){
        return new Order(
                (Long) null,
        this.userId,
        this.item,
        this.orderDate,
        this.shippingAddress,
        (Double) this.totalPrice,
        this.status
        );
    }

}
