package com.example.interceptor_middleware02.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "root")
public class BasicController {

    @GetMapping
    public String getGreetings() {
        return "Welcome";
    }
}
