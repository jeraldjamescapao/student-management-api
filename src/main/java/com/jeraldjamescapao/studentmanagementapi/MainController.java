package com.jeraldjamescapao.studentmanagementapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String home() {
        return "Hello, welcome to Student Management API!";
    }

    @RequestMapping(method = {RequestMethod.GET} , value = "/info")
    public String info() {
        return "Student Management API v1.0";
    }
}
