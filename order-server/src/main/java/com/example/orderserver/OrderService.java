package com.example.orderserver;

import com.example.mallserver.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Transactional
    public Order createOrder(Order order) {
        log.info("Attempting to create order: {}", order);
        Order savedOrder = orderMapper.save(order);
        log.info("Order created successfully with ID: {}", savedOrder.getId());
        return savedOrder;
    }

    public Optional<Order> getOrderById(Long id) {
        log.info("Attempting to find order by ID: {}", id);
        return orderMapper.findById(id);
    }

    public List<Order> getAllOrders() {
        log.info("Retrieving all orders");
        return orderMapper.findAll();
    }

    @Transactional
    public Order updateOrder(Long id, Order orderDetails) {
        log.info("Attempting to update order with ID: {}", id);
        Order orderToUpdate = orderMapper.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + id));
        // 更新订单的逻辑...

        return orderMapper.save(orderToUpdate);
    }

    @Transactional
    public void deleteOrder(Long id) {
        log.info("Attempting to delete order with ID: {}", id);
        orderMapper.deleteById(id);
        log.info("Order deleted successfully with ID: {}", id);
    }

}
