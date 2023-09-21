package com.wms.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/b")
public class book {
    @PostMapping
    public String save(){
        System.out.println("jsdad");
        return "kkk";
    }
}
