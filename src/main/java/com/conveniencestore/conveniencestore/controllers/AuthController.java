package com.conveniencestore.conveniencestore.controllers;

import com.conveniencestore.conveniencestore.domain.users.LoginResponseDTO;
import com.conveniencestore.conveniencestore.domain.users.User;
import com.conveniencestore.conveniencestore.domain.users.UserAuthDTO;
import com.conveniencestore.conveniencestore.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenService service;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserAuthDTO data){
        UsernamePasswordAuthenticationToken usernameAndPassword = new UsernamePasswordAuthenticationToken(data.email(),data.password());
        var auth = this.authenticationManager.authenticate(usernameAndPassword);
        String token = this.service.genToken((User) auth.getPrincipal());
        return ResponseEntity.ok().body(new LoginResponseDTO(token));
    }
}
