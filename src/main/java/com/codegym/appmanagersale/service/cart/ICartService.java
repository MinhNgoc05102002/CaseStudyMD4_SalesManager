package com.codegym.appmanagersale.service.cart;

import com.codegym.appmanagersale.model.Cart;
import com.codegym.appmanagersale.model.Product;
import com.codegym.appmanagersale.service.IGeneralService;

import java.util.List;

public interface ICartService extends IGeneralService<Cart> {
    List<Cart> findAllByAccountId(Long id);
}
