package com.github.andregpereira.resilientshop.commons.dto;

import java.math.BigDecimal;

public record DetalhePedidoDto(Long id,
        int quantidade,
        BigDecimal subtotal,
        ProdutoDetalhesDto produto) {

}
