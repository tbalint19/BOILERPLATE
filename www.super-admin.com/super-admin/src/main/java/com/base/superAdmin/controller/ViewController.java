package com.base.superAdmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping(
            {"/", "/start", "/reset"})
    public String index() {
        return "forward:/index.html";
    }
}
