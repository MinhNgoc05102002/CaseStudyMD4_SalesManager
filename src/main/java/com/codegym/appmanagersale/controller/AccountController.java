package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.model.Account;
import com.codegym.appmanagersale.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @GetMapping("/")
    public String showFormLogin() {
        return "/login";
    }

    @GetMapping("/login")
    public String reShowFormLogin() {
        return "/login";
    }

    @GetMapping("/register")
    public ModelAndView showFormRegister() {
        ModelAndView modelAndView = new ModelAndView("/register");
        modelAndView.addObject("account", new Account());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerAccount(@ModelAttribute("account") Account account) {
        ModelAndView modelAndView;
        if (accountService.save(account)) {
            modelAndView = new ModelAndView("/login");
            modelAndView.addObject("message", "Register successfully!");
        } else {
            modelAndView = new ModelAndView("/register");
            modelAndView.addObject("message", "Register failed!");
            modelAndView.addObject("account", new Account());
        }
        return modelAndView;
    }
}
