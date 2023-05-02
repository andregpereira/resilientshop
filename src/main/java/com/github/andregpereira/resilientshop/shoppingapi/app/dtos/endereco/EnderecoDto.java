package com.github.andregpereira.resilientshop.shoppingapi.app.dtos.endereco;

public record EnderecoDto(Long id,
        String apelido,
        String cep,
        String estado,
        String cidade,
        String bairro,
        String rua,
        String numero,
        String complemento) {

}
