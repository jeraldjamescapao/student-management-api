package com.jeraldjamescapao.studentmanagementapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI baseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Management API")
                        .version("v1.0")
                        .description("Simple API for students, courses, enrollments, and grades.")
                        .contact(new Contact().name("Jerald James Capao").email("jjcapaodev@protonmail.com"))
                        .license(new License().name("MIT")))
                .addServersItem(new Server().url("http://localhost:8080").description("Local"));
    }
}
