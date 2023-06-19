package com.github.andregpereira.resilientshop.authenticationapi.app.service;

import com.github.andregpereira.resilientshop.authenticationapi.app.dto.UsuarioCredentialDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.UsuarioCredentialRegistroDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.mapper.UsuarioCredentialMapper;
import com.github.andregpereira.resilientshop.authenticationapi.domain.entity.UsuarioCredential;
import com.github.andregpereira.resilientshop.authenticationapi.infra.repository.UserCredentialRepository;
import com.github.andregpereira.resilientshop.authenticationapi.infra.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final UserCredentialRepository repository;
    private final UsuarioCredentialMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UsuarioCredentialDto criar(UsuarioCredentialRegistroDto dto) {
        UsuarioCredential usuario = mapper.toUsuarioCredential(dto);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setRole("admin");
        return mapper.toUsuarioCredentialDto(repository.save(usuario));
    }

    public String gerarToken(String username) {
        return repository.findByEmail(username).map(jwtProvider::generateToken).orElseThrow(
                () -> new RuntimeException("erro ao gerar token"));
    }

    public void validarToken(String token) {
        jwtProvider.validateToken(token);
    }

}
