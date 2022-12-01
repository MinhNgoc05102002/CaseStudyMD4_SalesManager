package com.codegym.appmanagersale.service.categorywithproduct;

import com.codegym.appmanagersale.service.IGeneralService;

import java.util.List;

public interface ICategoryWithProduct extends IGeneralService<CategoryWithProduct> {
    List<CategoryWithProduct> findAllByProductId(Long id);
}
