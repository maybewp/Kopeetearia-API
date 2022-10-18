package com.accenture.ws.impl;

import com.accenture.ws.entity.CafeClerk;
import com.accenture.ws.entity.Order;

import java.util.List;

public abstract class OrderBill {

    private List<Order> orderList;
    private CafeClerk clark;

//    public OrderBill(CafeClerk clark) {
//        this.clark = clark;
//    }

    public OrderBill() {
//        this.clark = clark;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public CafeClerk getClark() {
        return clark;
    }

    public void setClark(CafeClerk clark) {
        this.clark = clark;
    }

    protected abstract double getTotalBill(List<Order> orderList);
}
