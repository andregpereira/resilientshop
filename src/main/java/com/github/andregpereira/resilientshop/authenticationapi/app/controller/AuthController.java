package com.github.andregpereira.resilientshop.authenticationapi.app.controller;

import com.github.andregpereira.resilientshop.commons.dto.UsuarioCredentialRegistroDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.LoginDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.UsuarioCredentialDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.service.AuthService;
import jakarta.validation.Valid;
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

    @PostMapping("/admin")
    public ResponseEntity<UsuarioCredentialDto> cadastrarAdmin(@RequestBody @Valid UsuarioCredentialRegistroDto dto) {
        log.info("Criando admin...");
        UsuarioCredentialDto admin = service.cadastrarAdmin(dto);
        log.info("Admin criado com sucesso");
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioCredentialDto> cadastrarUsuario(@RequestBody @Valid UsuarioCredentialRegistroDto dto) {
        log.info("Criando usuário...");
        UsuarioCredentialDto usuario = service.cadastrarUsuario(dto);
        log.info("Usuário criado com sucesso");
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto dto) {
        log.info("Fazendo login...");
        return ResponseEntity.ok(service.login(dto));
    }

    @GetMapping("/validar")
    public String validarToken(@RequestParam("token") String token) {
        service.validarToken(token);
        return "Token inválido";
    }

}
