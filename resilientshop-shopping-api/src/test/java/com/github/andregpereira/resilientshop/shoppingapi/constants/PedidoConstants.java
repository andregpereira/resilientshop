package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Pedido;

import java.math.BigDecimal;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoConstants.LISTA_DETALHES_PEDIDO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.LocalDateTimeConstants.PEDIDO_LOCAL_DATE_TIME;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.UsuarioConstants.USUARIO;

public class PedidoConstants {

    public static final Pedido PEDIDO = new Pedido(null, PEDIDO_LOCAL_DATE_TIME, PEDIDO_LOCAL_DATE_TIME, 1,
            BigDecimal.valueOf(48), 1L, 1L, LISTA_DETALHES_PEDIDO, USUARIO);

    public static final Pedido PEDIDO_ATUALIZADO = new Pedido(null, PEDIDO_LOCAL_DATE_TIME, PEDIDO_LOCAL_DATE_TIME, 1,
            BigDecimal.valueOf(48), 2L, 2L, LISTA_DETALHES_PEDIDO, USUARIO);

}
