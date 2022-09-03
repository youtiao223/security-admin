package com.zhao.module.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    @GetMapping("/client/get")
    public String Hello() {
        return "get";
    }

    @PostMapping("/client/post")
    public String Hello2() {
        return "post";
    }
}
