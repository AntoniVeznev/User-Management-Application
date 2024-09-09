package org.example.user_management_application;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "User Management Application",
                version = "2.5.2",
                description = "This project is made by Antoni Veznev for Markovski-Solutions",
                termsOfService = "",
                contact = @Contact(
                        name = "Antoni Veznev",
                        email = "antoniveznev@gmail.com",
                        url = "https://github.com/AntoniVeznev?tab=repositories"
                ),
                license = @License(
                        name = "MIT License",
                        url = "https://github.com/AntoniVeznev/User-Management-Application/blob/main/LICENSE"
                )
        )
)
public class UserManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }
}
