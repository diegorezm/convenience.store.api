package com.conveniencestore.conveniencestore.controllers;

import com.conveniencestore.conveniencestore.domain.Error.ErrorDTO;
import com.conveniencestore.conveniencestore.domain.users.*;
import com.conveniencestore.conveniencestore.domain.users.exceptions.UserNotFoundException;
import com.conveniencestore.conveniencestore.infra.security.TokenService;
import com.conveniencestore.conveniencestore.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private static final List<String> VALID_SEARCH_PARAMETERS = List.of("id", "username", "email", "asc", "desc");

    @GetMapping
    public ResponseEntity<?> getAllUsers(
            @RequestParam(required = false, defaultValue = "id")
            String orderby,
            @RequestParam(required = false, defaultValue = "asc")
            String order
    ) {
        if (VALID_SEARCH_PARAMETERS.contains(orderby) && VALID_SEARCH_PARAMETERS.contains(order))
            return ResponseEntity.ok().body(this.service.getAll(orderby, order));
        ErrorDTO error = new ErrorDTO("Request param is not valid.", 400);
        return ResponseEntity.status(400).body(error);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        try {
            return ResponseEntity.ok(this.service.getById(id));
        } catch (UserNotFoundException e) {
            ErrorDTO error = new ErrorDTO("This user was not found.", 404);
            return ResponseEntity.status(404).body(error);
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid UserAuthDTO data) {
        try {
            UsernamePasswordAuthenticationToken usernameAndPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.authenticationManager.authenticate(usernameAndPassword);
            User user = (User) auth.getPrincipal();
            var token = this.tokenService.genToken(user);
            UserResponseDTO responseDTO = new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(),
                    user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
            return ResponseEntity.ok().body(new LoginResponseDTO(token.token(), token.expiresAt(), responseDTO));
        } catch (BadCredentialsException e) {
            ErrorDTO error = new ErrorDTO("Invalid email or password", 401);
            return ResponseEntity.status(401).body(error);
        } catch (UserNotFoundException e) {
            ErrorDTO error = new ErrorDTO("User not found.", 404);
            return ResponseEntity.status(404).body(error);
        }

    }

    @PostMapping
    public ResponseEntity<?> registerNewUser(@RequestBody @Valid UserDTO data) {
        if (data.password() == null) {
            ErrorDTO error = new ErrorDTO("Please provide the password.", 400);
            return ResponseEntity.status(400).body(error);
        }
        UserResponseDTO user = this.service.insert(data);
        return ResponseEntity.ok(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> editUser(@PathVariable Integer id, @RequestBody @Valid UserDTO data) {
        try {
            UserResponseDTO user = this.service.update(id, data);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            ErrorDTO error = new ErrorDTO("This user was not found.", 404);
            return ResponseEntity.status(404).body(error);
        }
    }
}
