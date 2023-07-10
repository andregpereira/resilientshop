package com.github.andregpereira.resilientshop.shoppingapi.constants;


import com.github.andregpereira.resilientshop.commons.entities.Produto;

import java.math.BigDecimal;

public class ProdutoConstants {

    public static final Produto PRODUTO = new Produto(null, "nome", BigDecimal.valueOf(12), 10);

    public static final Produto PRODUTO_ATUALIZADO = new Produto(null, "nome2", BigDecimal.valueOf(30), 87);

    public static final Produto PRODUTO_INVALIDO = new Produto(null, "", null, 0);

    public static final Produto PRODUTO_VAZIO = new Produto();

}
