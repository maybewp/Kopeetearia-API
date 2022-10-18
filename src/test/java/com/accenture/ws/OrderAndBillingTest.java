package com.accenture.ws;

import com.accenture.ws.entity.Order;
import com.accenture.ws.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ContextConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderAndBillingTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    @org.junit.jupiter.api.Order(1)
    public void addOrderTest() {
        // Declaration Object
        Order order = new Order();
        // Using setter step 1
        order.setOrderName("Latte");
        order.setPrice(4.5);
        order.setDiscounted(true);
        // Check if discount == true then discountpercentage 5
        if (order.getDiscounted()) {
            order.setIsDiscountedPercentage(5.0);
        } else {
            order.setIsDiscountedPercentage(0);
        }
        // Save order
        orderRepository.save(order);

        // Using setter step 2
        order.setOrderName("Cappucino");
        order.setPrice(5.5);
        order.setDiscounted(false);
        // Check if discount == true then discountpercentage 5
        if (order.getDiscounted()) {
            order.setIsDiscountedPercentage(5.0);
        } else {
            order.setIsDiscountedPercentage(0);
        }
        // Save order
        orderRepository.save(order);

        // Save and get ID after insert
        Assertions.assertThat(order.getId()).isGreaterThan(0);
    }
    @Test
    @org.junit.jupiter.api.Order(2)
    public void getOrderListTest() {
        // Get All Data from Database and put to List
        List<Order> orderList = orderRepository.findAll();

        // Check Data, if > 0 will success
        Assertions.assertThat(orderList.size());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void updateOrderTest() {
        // Get Data by ID
        Order order = orderRepository.findById(Long.valueOf(1)).get();
        // Using Setter
        order.setOrderName("Cafe Latte");
        order.setPrice(3.5);
        order.setDiscounted(true);
        // Check if discount == true then discountpercentage 5
        if (order.getDiscounted()) {
            order.setIsDiscountedPercentage(5.0);
        } else {
            order.setIsDiscountedPercentage(0);
        }
        // Save Order
        orderRepository.save(order);

        // Update will be success and also check same expected and actual
        Assertions.assertThat(order.getOrderName()).isEqualTo("Cafe Latte");
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    @Rollback(value = false)
    public void deleteOrderTest() {
        Long id = Long.valueOf(1);
        Order order = orderRepository.findById(Long.valueOf(id)).get();
        orderRepository.delete(order);

        Order orderNull = null;
        Optional<Order> orderDeleted = orderRepository.findById(Long.valueOf(id));

        if (orderDeleted.isPresent()) {
            orderNull = orderDeleted.get();
        }
        Assertions.assertThat(orderNull).isNull();
    }
}
