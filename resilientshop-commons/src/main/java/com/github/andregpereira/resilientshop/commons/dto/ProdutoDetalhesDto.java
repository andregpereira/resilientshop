package com.github.andregpereira.resilientshop.commons.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoDetalhesDto(Long id,
        Long sku,
        String nome,
        String descricao,
        BigDecimal valorUnitario,
        int estoque,
        @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/uuuu HH:mm") LocalDateTime dataCriacao,
        @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/uuuu HH:mm") LocalDateTime dataModificacao,
        boolean ativo,
        CategoriaDto categoria,
        SubcategoriaDto subcategoria) {

}
