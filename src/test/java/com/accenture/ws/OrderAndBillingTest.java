package com.accenture.ws;

import com.accenture.ws.entity.Order;
import com.accenture.ws.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ContextConfiguration
public class OrderAndBillingTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void getOrderListTest() {
        // Get All Data from Database and put to List
        List<Order> orderList = orderRepository.findAll();

        // Check Data, if > 0 will success
        Assertions.assertThat(orderList.size()).isGreaterThan(0);
    }

    @Test
    public void addOrderTest() {
        // Declaration Object
        Order order = new Order();
        // Using setter
        order.setOrderName("Hot Tea");
        order.setPrice(10);
        order.setDiscounted(true);
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
    public void updateOrderTest() {
        // Get Data by ID
        Order order = orderRepository.findById(Long.valueOf(1)).get();
        // Using Setter
        order.setOrderName("Cafe Latte");
        order.setPrice(20.25);
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
    public void deleteOrderTest() {
        Long id = Long.valueOf(3);
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
