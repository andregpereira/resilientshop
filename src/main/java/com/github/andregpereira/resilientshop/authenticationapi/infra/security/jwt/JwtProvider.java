package com.github.andregpereira.resilientshop.authenticationapi.infra.security.jwt;

import com.github.andregpereira.resilientshop.authenticationapi.domain.entity.UsuarioCredential;

public interface JwtProvider {

    String gerarToken(UsuarioCredential user);

    void validarToken(String token);

}
