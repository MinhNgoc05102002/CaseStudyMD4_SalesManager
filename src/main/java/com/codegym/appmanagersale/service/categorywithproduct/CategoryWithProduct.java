package com.codegym.appmanagersale.service.categorywithproduct;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CategoryWithProduct implements ICategoryWithProduct {

    @Autowired
    private ICategoryWithProduct categoryWithProductRepository;


    @Override
    public Iterable<CategoryWithProduct> findAll() {
        return null;
    }

    @Override
    public Optional<CategoryWithProduct> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Boolean save(CategoryWithProduct categoryWithProduct) {
        return null;
    }

    @Override
    public Boolean remove(Long id) {
        return null;
    }

    @Override
    public List<CategoryWithProduct> findAllByProductId(Long id) {
        return categoryWithProductRepository.findAllByProductId(id);
    }
}
