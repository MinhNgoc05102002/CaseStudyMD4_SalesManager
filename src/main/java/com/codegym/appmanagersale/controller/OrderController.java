package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("")
    public ModelAndView showOrder() {
        ModelAndView modelAndView = new ModelAndView("/admin/order/list");
        modelAndView.addObject("orders", orderService.findAll());
        return modelAndView;
    }
}
