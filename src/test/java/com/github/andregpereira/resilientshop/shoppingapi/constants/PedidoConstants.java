package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Pedido;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoConstants.DETALHES_PEDIDO;

public class PedidoConstants {

    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();

    public static final LocalDateTime LOCAL_DATE_TIME_FIXADO = LocalDateTime.now(
            Clock.fixed(Instant.parse("2014-12-22T10:15:30.00Z"), ZoneId.systemDefault()));

    public static final Pedido PEDIDO = new Pedido(null, LOCAL_DATE_TIME, LOCAL_DATE_TIME, 1, BigDecimal.valueOf(48),
            DETALHES_PEDIDO);

}
