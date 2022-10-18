package com.accenture.ws.impl;

import com.accenture.ws.entity.Order;

import java.util.List;

public class DiscountedBill extends OrderBill {

    public DiscountedBill() {
//        super(clark);
    }

    @Override
    public double getTotalBill(List<Order> orderList) {
        double total = 0;
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getDiscounted() == true) {
                double remain = orderList.get(i).getPrice() * 0.05;
                total += orderList.get(i).getPrice() - remain;
            } else {
                total += orderList.get(i).getPrice();
            }
        }
        return total;
    }
}
