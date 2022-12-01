package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.model.Product;
import com.codegym.appmanagersale.repository.ICategoryWithProduct;
import com.codegym.appmanagersale.service.account.IAccountService;
import com.codegym.appmanagersale.service.category.ICategoryService;
import com.codegym.appmanagersale.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ICategoryWithProduct categoryWithProduct;

    @GetMapping("")
    public ModelAndView showProduct() {
        ModelAndView modelAndView = new ModelAndView("/admin/product/list");
        List<Product> products = (List<Product>) productService.findAll();
        modelAndView.addObject("products", productService.findAll());
        modelAndView.addObject("categories", categoryService.findAll());
        for (Product product : products) {
            setCategoryForProduct(product);
        }

        return modelAndView;
    }

    private void setCategoryForProduct(Product product) {
        product.setCategoryWithProducts((categoryWithProduct.findAllByProductId(product.getId())));
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/admin/product/list");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }
}
