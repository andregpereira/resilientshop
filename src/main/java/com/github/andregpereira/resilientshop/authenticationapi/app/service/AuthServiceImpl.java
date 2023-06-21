package com.github.andregpereira.resilientshop.authenticationapi.app.service;

import com.github.andregpereira.resilientshop.authenticationapi.app.dto.LoginDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.UsuarioCredentialDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.UsuarioCredentialRegistroDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.mapper.UsuarioCredentialMapper;
import com.github.andregpereira.resilientshop.authenticationapi.domain.entity.UsuarioCredential;
import com.github.andregpereira.resilientshop.authenticationapi.infra.repository.UserCredentialRepository;
import com.github.andregpereira.resilientshop.authenticationapi.infra.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final UserCredentialRepository repository;
    private final UsuarioCredentialMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public UsuarioCredentialDto criar(UsuarioCredentialRegistroDto dto) {
        UsuarioCredential usuario = mapper.toUsuarioCredential(dto);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setRole("admin");
        return mapper.toUsuarioCredentialDto(repository.save(usuario));
    }

    @Override
    public String gerarToken(LoginDto dto) {
        try {
            log.info("auth1");
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.email(), dto.senha()));
            log.info("auth2");
            if (authenticate.isAuthenticated()) {
                log.info(repository.toString());
                return repository.findByEmail(dto.email()).map(jwtProvider::gerarToken).orElseThrow(
                        () -> new RuntimeException("erro ao gerar token"));
            } else {
                throw new RuntimeException("invalid access");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public void validarToken(String token) {
        jwtProvider.validarToken(token);
    }

}
