package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.produto.ProdutoDto;

import java.math.BigDecimal;

public class ProdutoDtoConstants {

    public static final ProdutoDto PRODUTO_DTO = new ProdutoDto(null, "nome", BigDecimal.valueOf(12), 10);

}
