package com.github.andregpereira.resilientshop.authenticationapi.infra.security.jwt;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface JwtProvider {

    String gerarToken(String email, Collection<? extends GrantedAuthority> roles);

    void validarToken(String token);

}
