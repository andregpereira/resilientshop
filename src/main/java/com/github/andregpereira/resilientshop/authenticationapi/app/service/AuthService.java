package com.github.andregpereira.resilientshop.authenticationapi.app.service;

import com.github.andregpereira.resilientshop.authenticationapi.app.dto.LoginDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.UsuarioCredentialDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.UsuarioCredentialRegistroDto;

public interface AuthService {

    UsuarioCredentialDto criar(UsuarioCredentialRegistroDto dto);

    String gerarToken(LoginDto username);

    void validarToken(String token);

}
