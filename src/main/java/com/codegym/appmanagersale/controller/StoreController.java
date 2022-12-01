package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.repository.ICategoryWithProduct;
import com.codegym.appmanagersale.service.category.ICategoryService;
import com.codegym.appmanagersale.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ICategoryWithProduct categoryWithProduct;
    @GetMapping("")
    public ModelAndView showStore() {
        ModelAndView modelAndView = new ModelAndView("/user/store");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/showAllProducts")
    public ModelAndView showAllProducts() {
        ModelAndView modelAndView = new ModelAndView("/user/productByCategory");
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/productDetail/{id}")
    public ModelAndView showProductDetail(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/user/productDetail");
        modelAndView.addObject("product", productService.findById((long) id).get());
        modelAndView.addObject("categories", categoryWithProduct.findAllByProductId((long) id));
        return modelAndView;
    }
}
