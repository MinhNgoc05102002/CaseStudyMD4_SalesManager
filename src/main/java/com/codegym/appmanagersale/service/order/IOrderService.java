package com.codegym.appmanagersale.service.order;

import com.codegym.appmanagersale.model.Order;
import com.codegym.appmanagersale.service.IGeneralService;

import java.util.List;

public interface IOrderService extends IGeneralService<Order> {
    List<Order> findAllByAccountId(Long id);
}
