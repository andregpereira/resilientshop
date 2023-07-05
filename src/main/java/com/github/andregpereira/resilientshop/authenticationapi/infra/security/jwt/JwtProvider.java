package com.github.andregpereira.resilientshop.authenticationapi.infra.security.jwt;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface JwtProvider {

    String gerarToken(String email, List<? extends GrantedAuthority> roles);

    void validarToken(String token);

}
