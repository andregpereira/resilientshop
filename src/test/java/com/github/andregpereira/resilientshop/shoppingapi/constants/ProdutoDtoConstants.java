package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.produto.ProdutoDto;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Produto;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ProdutoDtoConstants {

    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();

    public static final LocalDateTime LOCAL_DATE_TIME_FIXADO = LocalDateTime.now(
            Clock.fixed(Instant.parse("2014-12-22T10:15:30.00Z"), ZoneId.systemDefault()));

    public static final ProdutoDto PRODUTO_DTO = new ProdutoDto(null, 123456789L, "nome", "Teste da classe Produto",
            LOCAL_DATE_TIME, BigDecimal.valueOf(12), 10);

    public static final Produto PRODUTO_LOCAL_DATE_TIME_FIXADO = new Produto(null, 123456789L, "nome",
            "Teste da classe Produto", LOCAL_DATE_TIME_FIXADO, BigDecimal.valueOf(10.59), 10);

    public static final Produto PRODUTO_ATUALIZADO = new Produto(null, 123456789L, "nome2", "Teste da classe Produto2",
            LOCAL_DATE_TIME, BigDecimal.valueOf(29.99), 87);

    public static final Produto PRODUTO_INVALIDO = new Produto(null, null, "", "", null, null, 0);

    public static final Produto PRODUTO_VAZIO = new Produto();

}
