package com.github.andregpereira.resilientshop.shoppingapi.app.dto.usuario;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.endereco.EnderecoDto;

public record UsuarioDto(Long id,
        String nome,
        EnderecoDto endereco) {

}
