package com.accenture.ws.impl;

import com.accenture.ws.entity.Order;

import java.util.List;

public class RegularBill extends OrderBill {

    public RegularBill() {
//        super(clark);
    }

    @Override
    public double getTotalBill(List<Order> orderList) {
        double total = 0;
        for (int i = 0; i < orderList.size(); i++) {
            total += orderList.get(i).getPrice();
        }
        return total;
    }
}
