package com.github.andregpereira.resilientshop.authenticationapi.app.controller;

import com.github.andregpereira.resilientshop.authenticationapi.app.dto.AuthRequestDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.UsuarioCredentialDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.UsuarioCredentialRegistroDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<UsuarioCredentialDto> criar(@RequestBody UsuarioCredentialRegistroDto dto) {
        log.info("Criando usuário...");
        UsuarioCredentialDto usuario = service.criar(dto);
        log.info("Usuário criado com sucesso");
//        URI uri = UriComponentsBuilder.fromPath("/auth/{id}").buildAndExpand(usuario.id()).toUri();
        return ResponseEntity.ok(usuario);
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