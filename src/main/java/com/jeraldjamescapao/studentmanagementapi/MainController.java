package com.jeraldjamescapao.studentmanagementapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Main Controller", description = "The Main Controller of the API.")
@RestController
public class MainController {

    @Operation(summary = "Welcome", description = "Returns a welcome message.")
    @GetMapping("/")
    public String home() {
        return "Hello, welcome to Student Management API!";
    }

    @Operation(summary = "API Info", description = "Shows basic version info.")
    @RequestMapping(method = {RequestMethod.GET} , value = "/info")
    public String info() {
        return "Student Management API v1.0";
    }
}
