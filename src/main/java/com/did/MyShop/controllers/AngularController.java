package com.did.MyShop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularController {
    @RequestMapping(value = "/{path:[^\\.]*}")
    public String index(@PathVariable String path) {
        System.out.println("path: " + path);
        return "forward:/index.html";
    }
}
