package com.github.andregpereira.resilientshop.authenticationapi.app.dto;

import com.github.andregpereira.resilientshop.commons.security.role.Role;

public record UsuarioCredentialDto(Long id,
        String email,
        String senha,
        Role role,
        boolean ativo) {

}
