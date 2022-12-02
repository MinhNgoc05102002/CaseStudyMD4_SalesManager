package com.codegym.appmanagersale.service.order;

import com.codegym.appmanagersale.model.Category;
import com.codegym.appmanagersale.model.Order;
import com.codegym.appmanagersale.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        try {
            Order order = orderRepository.findById(id).orElse(null);
            if (order == null) {
                throw new Exception("Order not found!");
            }
            return Optional.of(order); // Optional.ofNullable(order);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Boolean save(Order order) {
        try {
            if (order == null) throw new Exception("Order is null!");
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean remove(Long id) {
        Order order = orderRepository.findById(id).get();
        try {
            orderRepository.delete(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Order> findAllByAccountId(Long id) {
        return orderRepository.findAllByAccountId(id);
    }
}
