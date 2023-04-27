package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Pedido;

import java.math.BigDecimal;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoConstants.DETALHES_PEDIDO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.LocalDateTimeConstants.PEDIDO_LOCAL_DATE_TIME;

public class PedidoConstants {

    public static final Pedido PEDIDO = new Pedido(null, PEDIDO_LOCAL_DATE_TIME, PEDIDO_LOCAL_DATE_TIME, 1,
            BigDecimal.valueOf(48), DETALHES_PEDIDO);

}
