package com.jeraldjamescapao.studentmanagementapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the OpenAPI/Swagger documentation of the Student Management API.
 *
 * <p><b>Purpose:</b> Provides centralized API metadata for Swagger UI and OpenAPI schema generation.
 * Defines the title, version, description, contact information, and license details.</p>
 *
 * <p><b>Usage:</b> Automatically picked up by Spring Boot when running the application.
 * The generated documentation can be accessed via the Swagger UI endpoint
 * (typically {@code /docs}).</p>
 *
 * <p><b>Server:</b> Registers a single local server entry for development purposes
 * ({@code http://localhost:8080}).</p>
 *
 * @see io.swagger.v3.oas.annotations.OpenAPIDefinition
 */
@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI baseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Management API")
                        .version("v1.0")
                        .description("Simple API for students, courses, enrollments, and grades.")
                        .contact(new Contact()
                                .name("Jerald James Capao")
                                .email("jjcapaodev@protonmail.com"))
                        .license(new License()
                                .name("MIT")))
                .addServersItem(new Server()
                        .url("http://localhost:8080")
                        .description("Local"));
    }
}
