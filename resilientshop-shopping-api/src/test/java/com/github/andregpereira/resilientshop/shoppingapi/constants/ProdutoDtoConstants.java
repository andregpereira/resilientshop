package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.produto.ProdutoDto;

import java.math.BigDecimal;

public class ProdutoDtoConstants {

    public static final ProdutoDto PRODUTO_DTO = new ProdutoDto(null, "nome", BigDecimal.valueOf(12), 10);

}
