package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private IProductService productService;
    @GetMapping("")
    public ModelAndView showStore() {
        ModelAndView modelAndView = new ModelAndView("/user/store");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/showAllProducts")
    public ModelAndView showAllProducts() {
        ModelAndView modelAndView = new ModelAndView("user/productByCategory");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

//    @GetMapping()
}
