package com.codegym.appmanagersale.service.cart;

import com.codegym.appmanagersale.model.Cart;
import com.codegym.appmanagersale.model.Product;
import com.codegym.appmanagersale.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public Iterable<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Boolean save(Cart cart) {
        try {
            if (cart == null) throw new Exception("Cart is null");
            cartRepository.save(cart);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean remove(Long id) {
        try {
            if (id == null) throw new Exception("Id is null");
            cartRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cart> findAllByAccountId(Long id) {
        return cartRepository.findAllByAccountId(id);
    }
}
