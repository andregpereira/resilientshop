package com.github.andregpereira.resilientshop.shoppingapi.app.dtos.detalhe;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.produto.ProdutoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.usuario.UsuarioDto;

import java.math.BigDecimal;

public record DetalhesPedidoDetalharDto(Long id,
        int quantidade,
        BigDecimal subtotal,
        ProdutoDto produto,
        UsuarioDto usuario) {

}
