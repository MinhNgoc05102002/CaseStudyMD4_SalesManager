package com.codegym.appmanagersale.service.orderDetail;

import com.codegym.appmanagersale.model.OrderDetail;
import com.codegym.appmanagersale.repository.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderDetailService implements IOrderDetailService{
    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @Override
    public Iterable<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public Boolean save(OrderDetail orderDetail) {
        try {
            if (orderDetail == null) throw new Exception("Orderdetail is null!");
            orderDetailRepository.save(orderDetail);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean remove(Long id) {
        try {
            orderDetailRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<OrderDetail> findAllByOrderId(Long id) {
        return orderDetailRepository.findAllByOrderId(id);
    }
}
