package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.model.Product;
import com.codegym.appmanagersale.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public ModelAndView showProduct() {
        ModelAndView modelAndView = new ModelAndView("/admin/product/list");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/admin/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
}
