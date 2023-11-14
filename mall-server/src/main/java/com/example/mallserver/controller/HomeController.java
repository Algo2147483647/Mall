package com.example.mallserver.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(HomeController.class);
    @GetMapping("/home")
    public String home() {
        logger.info("HomeController's home() method has been accessed.");
        return "home";
    }
}
