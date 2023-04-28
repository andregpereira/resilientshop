package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.produto.ProdutoDto;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Produto;

import java.math.BigDecimal;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.LocalDateTimeConstants.PRODUTO_LOCAL_DATE_TIME;

public class ProdutoDtoConstants {

    public static final ProdutoDto PRODUTO_DTO = new ProdutoDto(null, 123456789L, "nome", "Teste da classe Produto",
            PRODUTO_LOCAL_DATE_TIME, BigDecimal.valueOf(12), 10);

    public static final Produto PRODUTO_DTO_ATUALIZADO = new Produto(null, 123456789L, "nome2",
            "Teste da classe Produto2", PRODUTO_LOCAL_DATE_TIME, BigDecimal.valueOf(29.99), 87);

    public static final Produto PRODUTO_DTO_INVALIDO = new Produto(null, null, "", "", null, null, 0);

    public static final Produto PRODUTO_DTO_VAZIO = new Produto();

}
