package com.github.andregpereira.resilientshop.shoppingapi.app.dtos;

import java.math.BigDecimal;

public record DetalhesPedidoDto(Long id,
        int quantidade,
        BigDecimal subtotal,
        ProdutoDto produto) {

}
