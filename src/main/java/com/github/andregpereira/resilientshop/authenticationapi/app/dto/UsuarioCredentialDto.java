package com.github.andregpereira.resilientshop.authenticationapi.app.dto;

public record UsuarioCredentialDto(Long id,
        String nome,
        String email,
        String senha) {

}
