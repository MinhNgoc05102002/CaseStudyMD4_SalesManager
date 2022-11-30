package com.codegym.appmanagersale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ManagerController {
    @RequestMapping("")
    public String showProduct() {
        return "/admin/manager";
    }
}
