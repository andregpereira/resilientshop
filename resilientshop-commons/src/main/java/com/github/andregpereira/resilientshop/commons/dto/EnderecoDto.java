package com.github.andregpereira.resilientshop.commons.dto;

public record EnderecoDto(Long id,
        String apelido,
        String cep,
        String estado,
        String cidade,
        String bairro,
        String rua,
        String numero,
        String complemento,
        boolean padrao,
        PaisDto pais) {

}
