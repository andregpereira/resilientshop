package com.github.andregpereira.resilientshop.authenticationapi.app.service;

import com.github.andregpereira.resilientshop.commons.dto.UsuarioCredentialRegistroDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.LoginDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.UsuarioCredentialDto;

public interface AuthService {

    UsuarioCredentialDto cadastrarAdmin(UsuarioCredentialRegistroDto dto);

    UsuarioCredentialDto cadastrarUsuario(UsuarioCredentialRegistroDto dto);

    String login(LoginDto dto);

    void validarToken(String token);

}
