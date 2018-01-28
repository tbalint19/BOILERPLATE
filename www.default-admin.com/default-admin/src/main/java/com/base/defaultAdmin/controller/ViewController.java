package com.base.defaultAdmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping(
            {"/", "/reset", "/confirm"})
    public String index() {
        return "forward:/index.html";
    }
}
