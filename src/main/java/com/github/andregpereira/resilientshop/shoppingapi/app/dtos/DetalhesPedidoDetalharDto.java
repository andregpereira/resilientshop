package com.github.andregpereira.resilientshop.shoppingapi.app.dtos;

import java.math.BigDecimal;

public record DetalhesPedidoDetalharDto(Long id,
        int quantidade,
        BigDecimal subtotal,
        ProdutoDto produto,
        UsuarioDto usuario) {

}
