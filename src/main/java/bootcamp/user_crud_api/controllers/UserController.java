package bootcamp.user_crud_api.controllers;

import bootcamp.user_crud_api.model.User;
import bootcamp.user_crud_api.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users")
@Tag(name = "users")
public class UserController {

    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /////////////////////////////////
    ////////////// POST /////////////
    /////////////////////////////////
    // SWAGGER
    @Operation(summary = "Cadastro de um novo usuario", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "usuario criado!"),
            @ApiResponse(responseCode = "400", description = "username nulo ou vazio"),
            @ApiResponse(responseCode = "409", description = "usuario existente"),
    })
    // Endpoint
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            logger.debug("passei aqui");
            logger.warn("createUser(): tentou criar com usuario nulo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username eh obrigatorio"); // HTTP 400
        }
        try {
            User createdUser = userService.createUser(user);
            logger.info("createUser(): criou o usuario: " + createdUser.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); // HTTP 201
        }catch (Exception e) {
            logger.warn("createUser(): tentou criar usuario ja existente: " + user.getUsername());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());  // HTTP 409
        }
    }

    /////////////////////////////////
    ////////////// GET //////////////
    /////////////////////////////////
    // SWAGGER
    @Operation(summary = "Busca usuario", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "usuario encontrado!"),
            @ApiResponse(responseCode = "404", description = "usuario nao encontrado"),
    })
    // Endpoint
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            logger.debug("getUserByUsername(): usuario encontrado: " + username);
            return ResponseEntity.ok(user);  // HTTP 200
        } else {
            logger.debug("getUserByUsername(): usuario nao encontrado: " + username);
            return ResponseEntity.notFound().build();  // HTTP 404
        }
    }

    /////////////////////////////////
    ////////////// DELETE ///////////
    /////////////////////////////////
    // SWAGGER
    @Operation(summary = "Atualiza usuario", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "usuario apagado!"),
            @ApiResponse(responseCode = "404", description = "usuario nao encontrado"),
    })
    // Endpoint
    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        boolean isDeleted = userService.deleteUser(username);
        if (isDeleted) {
            logger.info("deleteUser(): usuario apagado: " + username);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("deleteUser(): usuario nao encontrado: " + username);
            return ResponseEntity.notFound().build();
        }
    }

}