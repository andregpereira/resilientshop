package com.github.andregpereira.resilientshop.authenticationapi.app.service;

import com.github.andregpereira.resilientshop.commons.dto.UsuarioCredentialRegistroDto;
import com.github.andregpereira.resilientshop.commons.exception.UsuarioNotFoundException;
import com.github.andregpereira.resilientshop.commons.security.role.Role;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.LoginDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.UsuarioCredentialDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.mapper.UsuarioCredentialMapper;
import com.github.andregpereira.resilientshop.authenticationapi.domain.entity.UsuarioCredential;
import com.github.andregpereira.resilientshop.authenticationapi.infra.repository.UserCredentialRepository;
import com.github.andregpereira.resilientshop.authenticationapi.infra.security.jwt.JwtProvider;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional
    public UsuarioCredentialDto cadastrarAdmin(UsuarioCredentialRegistroDto dto) {
        UsuarioCredential admin = setarSenha(dto);
        admin.setRole(Role.ADMIN);
        admin.setAtivo(true);
        return mapper.toUsuarioCredentialDto(repository.save(admin));
    }

    @Override
    @Transactional
    public UsuarioCredentialDto cadastrarUsuario(UsuarioCredentialRegistroDto dto) {
        UsuarioCredential usuario = setarSenha(dto);
        usuario.setRole(Role.USER);
        usuario.setAtivo(true);
        return mapper.toUsuarioCredentialDto(repository.save(usuario));
    }

    private UsuarioCredential setarSenha(UsuarioCredentialRegistroDto dto) {
        UsuarioCredential usuario = mapper.toUsuarioCredential(dto);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        return usuario;
    }

    @Override
    public String login(LoginDto dto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.senha()));
        if (authenticate.isAuthenticated())
            try {
                return jwtProvider.gerarToken(authenticate.getName(),
                        (List<? extends GrantedAuthority>) authenticate.getAuthorities());
            } catch (JwtException e) {
                log.info(e.getMessage());
                return null;
            }
        else
            throw new RuntimeException("Erro ao gerar token");
    }

    @Override
    public void desativar(Long id) {
        repository.findByIdAndAtivoTrue(id).ifPresentOrElse(u -> {
            u.setAtivo(false);
            repository.save(u);
            log.info("Usuário com id {} desativado com sucesso", id);
        }, () -> {
            log.info("Usuário ativo com id {} não encontrado", id);
            throw new UsuarioNotFoundException(id, true);
        });
    }

    @Override
    public void reativar(Long id) {
        repository.findByIdAndAtivoFalse(id).ifPresentOrElse(u -> {
            u.setAtivo(true);
            repository.save(u);
            log.info("Usuário com id {} reativado com sucesso", id);
        }, () -> {
            log.info("Usuário inativo com id {} não encontrado", id);
            throw new UsuarioNotFoundException(id, false);
        });
    }

    @Override
    public void validarToken(String token) {
        jwtProvider.validarToken(token);
    }

}
