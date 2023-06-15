package com.github.andregpereira.resilientshop.authenticationapi.app.controller;

import com.github.andregpereira.resilientshop.authenticationapi.app.dto.AuthRequestDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.service.AuthServiceImpl;
import com.github.andregpereira.resilientshop.authenticationapi.infra.entity.UsuarioCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceImpl service;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public String criar(@RequestBody UsuarioCredential dto) {
        return service.criar(dto);
    }

    @PostMapping("/token")
    public String token(@RequestBody AuthRequestDto authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.senha()));
        if (authenticate.isAuthenticated()) {
            return service.gerarToken(authRequest.email());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validarToken(token);
        return "Token is valid";
    }

}