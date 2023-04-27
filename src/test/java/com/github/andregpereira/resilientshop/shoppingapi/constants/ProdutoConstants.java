package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Produto;

import java.math.BigDecimal;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.LocalDateTimeConstants.PRODUTO_LOCAL_DATE_TIME;

public class ProdutoConstants {

    public static final Produto PRODUTO = new Produto(null, 123456789L, "nome", "Teste da classe Produto",
            PRODUTO_LOCAL_DATE_TIME, BigDecimal.valueOf(12), 10);

    public static final Produto PRODUTO_ATUALIZADO = new Produto(null, 123456789L, "nome2", "Teste da classe Produto2",
            PRODUTO_LOCAL_DATE_TIME, BigDecimal.valueOf(30), 87);

    public static final Produto PRODUTO_INVALIDO = new Produto(null, null, "", "", null, null, 0);

    public static final Produto PRODUTO_VAZIO = new Produto();

}
