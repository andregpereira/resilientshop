package com.github.andregpereira.resilientshop.shoppingapi.app.dto.detalhepedido;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.produto.ProdutoDto;

import java.math.BigDecimal;

public record DetalhePedidoDto(Long id,
        int quantidade,
        BigDecimal subtotal,
        ProdutoDto produto) {

}
