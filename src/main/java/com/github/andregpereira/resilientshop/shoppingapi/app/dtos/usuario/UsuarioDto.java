package com.github.andregpereira.resilientshop.shoppingapi.app.dtos.usuario;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.endereco.EnderecoDto;

public record UsuarioDto(Long id,
        String nome,
        EnderecoDto endereco) {

}
