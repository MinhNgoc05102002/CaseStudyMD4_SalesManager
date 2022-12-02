package com.codegym.appmanagersale.service.orderDetail;

import com.codegym.appmanagersale.model.OrderDetail;
import com.codegym.appmanagersale.model.Product;
import com.codegym.appmanagersale.service.IGeneralService;

import java.util.List;

public interface IOrderDetailService extends IGeneralService<OrderDetail> {
    List<OrderDetail> findAllByOrderId(Long id);
}
