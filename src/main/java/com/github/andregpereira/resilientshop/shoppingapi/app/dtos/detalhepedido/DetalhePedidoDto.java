package com.github.andregpereira.resilientshop.shoppingapi.app.dtos.detalhe;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.produto.ProdutoDto;

import java.math.BigDecimal;

public record DetalhePedidoDto(Long id,
        int quantidade,
        BigDecimal subtotal,
        ProdutoDto produto) {

}
