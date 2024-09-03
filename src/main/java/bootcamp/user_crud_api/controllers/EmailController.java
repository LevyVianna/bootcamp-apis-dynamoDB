package bootcamp.user_crud_api.controllers;

import bootcamp.user_crud_api.services.EmailService;
import bootcamp.user_crud_api.model.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {

    private final EmailService emailService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    // SWAGGER
    @Operation(summary = "Busca usuario por email", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "email encontrado!"),
            @ApiResponse(responseCode = "404", description = "email nao encontrado"),
    })
    // Endpoint
    @GetMapping("/{email}")
    public ResponseEntity<List> getUsersByEmail(@PathVariable String email) {
        List<User> users =  emailService.getUsersByEmail(email);

        if (users.size() != 0) {
            logger.info("getUsersByEmail(): email encontrado: " + email);
            return ResponseEntity.ok(users);  // HTTP 200
        } else {
            logger.info("getUsersByEmail(): email nao encontrado: " + email);
            return ResponseEntity.notFound().build();  // HTTP 404
        }
    }
}