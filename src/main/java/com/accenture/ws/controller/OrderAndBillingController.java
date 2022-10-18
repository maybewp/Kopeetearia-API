package com.accenture.ws.controller;

import com.accenture.ws.entity.Order;
import com.accenture.ws.impl.DiscountedBill;
import com.accenture.ws.impl.RegularBill;
import com.accenture.ws.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class OrderAndBillingController {

    @Autowired
    private OrderRepository orderRepository;

//    private CafeClerk clerk;

    // Import Logger
    private static final Logger log = LoggerFactory.getLogger(OrderAndBillingController.class);

//    public OrderAndBillingController(CafeClerk clark) {
//        super(clark);
//    }

    @GetMapping("/orders")
//    public List<Order> getOrderList() {
//        log.info("Logging => Get All Orders");
//        return orderRepository.findAll();
//    }
    public ResponseEntity<?> getOrderList() {
        List<Order> orderList = orderRepository.findAll();
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", 200);
        map.put("status", true);
        map.put("message", "Data Found");
        map.put("data", orderList);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        try {
            Order setOrder = new Order();
            setOrder.setOrderName(order.getOrderName());
            setOrder.setPrice(order.getPrice());
            setOrder.setDiscounted(order.getDiscounted());
            if (order.getDiscounted()) {
                setOrder.setIsDiscountedPercentage(5.0);
            } else {
                setOrder.setIsDiscountedPercentage(0);
            }
            // Log
            log.info("Logging => Insert");
            orderRepository.save(setOrder);
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("code", 200);
            map.put("status", true);
            map.put("message", "Order is successfully added.");
            map.put("data", setOrder);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("code", 500);
            map.put("status", false);
            map.put("message", ex.getMessage());
            map.put("data", null);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") long id, @RequestBody Order order) {
        try {
            Order getOrder = orderRepository.findById(id).get();
            getOrder.setOrderName(order.getOrderName());
            getOrder.setPrice(order.getPrice());
            getOrder.setDiscounted(order.getDiscounted());
            if (order.getDiscounted()) {
                getOrder.setIsDiscountedPercentage(5.0);
            } else {
                getOrder.setIsDiscountedPercentage(0);
            }
            // Log
            log.info("Logging => Update");
            orderRepository.save(getOrder);
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("code", 200);
            map.put("status", true);
            map.put("message", "Order is successfully updated.");
            map.put("data", getOrder);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("code", 500);
            map.put("status", false);
            map.put("message", ex.getMessage());
            map.put("data", null);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") long id) {
        try {
            // Log
            log.info("Logging => Delete");
            orderRepository.deleteById(id);
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("code", 200);
            map.put("status", true);
            map.put("message", "Order is successfully deleted.");
            map.put("data", null);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("code", 500);
            map.put("status", false);
            map.put("message", ex.getMessage());
            map.put("data", null);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") long id) {
        try {
            // Log
            log.info("Logging => Get Orders by ID");
            Order orderDetail = orderRepository.findById(id).get();
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("code", 200);
            map.put("status", true);
            map.put("message", "Data was existed");
            map.put("data", orderDetail);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("code", 500);
            map.put("status", false);
            map.put("message", ex.getMessage());
            map.put("data", null);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/orders/total-regular")
    public ResponseEntity<?> getTotalRegulerBill() {
        try {
            List<Order> orderList = orderRepository.findAll();
            RegularBill regularBill = new RegularBill();

            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("code", 200);
            map.put("status", true);
            map.put("message", "Data Found");
            map.put("data", regularBill.getTotalBill(orderList));

            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("code", 404);
            map.put("status", false);
            map.put("message", "Data Not Found");
            map.put("data", null);

            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/orders/total-discounted")
    public ResponseEntity<?> getTotalDiscountedBill() {
        try {
            List<Order> orderList = orderRepository.findAll();
            DiscountedBill discountedBill = new DiscountedBill();

            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("code", 200);
            map.put("status", true);
            map.put("message", "Data Found");
            map.put("data", discountedBill.getTotalBill(orderList));

            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("code", 404);
            map.put("status", false);
            map.put("message", "Data Not Found");
            map.put("data", null);

            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}