package com.github.andregpereira.resilientshop.shoppingapi.app.dtos.produto;

import java.math.BigDecimal;

public record ProdutoDto(Long id,
        String nome,
        BigDecimal valorUnitario,
        int estoque) {

}
