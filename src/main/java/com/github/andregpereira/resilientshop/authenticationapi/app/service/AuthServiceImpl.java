package com.github.andregpereira.resilientshop.authenticationapi.app.service;

import com.github.andregpereira.resilientshop.authenticationapi.infra.entity.UsuarioCredential;
import com.github.andregpereira.resilientshop.authenticationapi.infra.repository.UserCredentialRepository;
import com.github.andregpereira.resilientshop.authenticationapi.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl {

    private final UserCredentialRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String criar(UsuarioCredential credential) {
        credential.setSenha(passwordEncoder.encode(credential.getSenha()));
        credential.setRole("admin");
        repository.save(credential);
        return "user added to the system";
    }

    public String gerarToken(String username) {
        return repository.findByEmail(username).map(jwtService::generateToken).orElseThrow(
                () -> new RuntimeException("erro ao gerar token"));
    }

    public void validarToken(String token) {
        jwtService.validateToken(token);
    }

}
