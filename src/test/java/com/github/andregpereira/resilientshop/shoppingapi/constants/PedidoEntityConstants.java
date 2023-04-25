package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoEntityConstants.LISTA_DETALHES_PEDIDOS_ENTITY;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoEntityConstants.LISTA_DETALHES_PEDIDOS_ENTITY_VAZIO;

public class PedidoEntityConstants {

    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();

    public static final LocalDateTime LOCAL_DATE_TIME_FIXADO = LocalDateTime.now(
            Clock.fixed(Instant.parse("2014-12-22T10:15:30.00Z"), ZoneId.systemDefault()));

    public static final PedidoEntity PEDIDO_ENTITY = new PedidoEntity(null, LOCAL_DATE_TIME, LOCAL_DATE_TIME, 1,
            BigDecimal.valueOf(48), LISTA_DETALHES_PEDIDOS_ENTITY);

    public static final PedidoEntity PEDIDO_ENTITY_VAZIO = new PedidoEntity();

    public static final PedidoEntity PEDIDO_ENTITY_INVALIDO = new PedidoEntity(null, null, null, 0, null,
            LISTA_DETALHES_PEDIDOS_ENTITY_VAZIO);

}
