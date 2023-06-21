package com.github.andregpereira.resilientshop.authenticationapi.app.controller;

import com.github.andregpereira.resilientshop.authenticationapi.app.dto.LoginDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.UsuarioCredentialDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.UsuarioCredentialRegistroDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    @PostMapping
    public ResponseEntity<UsuarioCredentialDto> criar(@RequestBody UsuarioCredentialRegistroDto dto) {
        log.info("Criando usuário...");
        UsuarioCredentialDto usuario = service.criar(dto);
        log.info("Usuário criado com sucesso");
//        URI uri = UriComponentsBuilder.fromPath("/auth/{id}").buildAndExpand(usuario.id()).toUri();
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/token")
    public ResponseEntity<String> token(@RequestBody LoginDto authRequest) {
        return ResponseEntity.ok(service.gerarToken(authRequest));
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validarToken(token);
        return "Token is valid";
    }

}