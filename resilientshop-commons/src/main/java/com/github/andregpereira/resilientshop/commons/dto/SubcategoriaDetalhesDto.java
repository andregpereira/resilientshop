package com.github.andregpereira.resilientshop.commons.dto;

public record SubcategoriaDetalhesDto(Long id,
        String nome,
        String descricao,
        CategoriaDto categoria) {

}
