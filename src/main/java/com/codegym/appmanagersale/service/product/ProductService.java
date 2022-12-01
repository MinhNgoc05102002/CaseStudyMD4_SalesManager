package com.codegym.appmanagersale.service.product;

import com.codegym.appmanagersale.model.Product;
import com.codegym.appmanagersale.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            Product product = productRepository.findById(id).orElse(null);
            if (product == null) {
                throw new Exception("Product not found!");
            }
            return Optional.of(product); // Optional.ofNullable(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Boolean save(Product product) {
        try {
            if (product == null) throw new Exception("Product is null!");
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean remove(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
