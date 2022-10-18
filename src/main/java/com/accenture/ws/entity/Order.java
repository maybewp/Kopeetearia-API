package com.accenture.ws.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_name")
    private String orderName;
    @Column(name = "price")
    private double price;
    @Column(name = "is_discounted")
    private Boolean isDiscounted;
    @Column(name = "is_discounted_percentage")
    private double isDiscountedPercentage; // if yes = 5 and also no = 0

    public Order() {
    }

    public Order(String orderName, double price, Boolean isDiscounted) {
        this.orderName = orderName;
        this.price = price;
        this.isDiscounted = isDiscounted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getDiscounted() {
        return isDiscounted;
    }

    public void setDiscounted(Boolean discounted) {
        isDiscounted = discounted;
    }

    public double getIsDiscountedPercentage() {
        return isDiscountedPercentage;
    }

    public void setIsDiscountedPercentage(double isDiscountedPercentage) {
        this.isDiscountedPercentage = isDiscountedPercentage;
    }
}