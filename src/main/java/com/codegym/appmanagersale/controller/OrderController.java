package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.model.Account;
import com.codegym.appmanagersale.model.Order;
import com.codegym.appmanagersale.service.account.IAccountService;
import com.codegym.appmanagersale.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private IOrderService orderService;

    @GetMapping("")
    public ModelAndView showOrder() {
        ModelAndView modelAndView = new ModelAndView("/admin/order/list");
        modelAndView.addObject("orders", orderService.findAll());
        Account account = getUserCurrent();
        modelAndView.addObject("account", account);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public String showOrderDetail(@PathVariable Long id, RedirectAttributes redirect) {
        Order order = orderService.findById(id).get();
        order.setStatus(order.CANCELLED);
        try {
            orderService.save(order);
            redirect.addFlashAttribute("success", "Removed order successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/orders";
    }

    public Account getUserCurrent() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        return accountService.findByUsername(username);
    }
}
