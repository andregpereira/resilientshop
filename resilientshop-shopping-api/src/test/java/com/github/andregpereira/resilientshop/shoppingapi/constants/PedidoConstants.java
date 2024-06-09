package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.infra.entity.PedidoEntity;

import java.math.BigDecimal;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoConstants.LISTA_DETALHES_PEDIDO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.LocalDateTimeConstants.PEDIDO_LOCAL_DATE_TIME;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.UsuarioConstants.USUARIO;

public class PedidoConstants {

    public static final PedidoEntity PEDIDO_ENTITY = new PedidoEntity(null, PEDIDO_LOCAL_DATE_TIME, PEDIDO_LOCAL_DATE_TIME, 1,
            BigDecimal.valueOf(48), 1L, 1L, LISTA_DETALHES_PEDIDO, USUARIO);

    public static final PedidoEntity PEDIDO_ENTITY_ATUALIZADO = new PedidoEntity(null, PEDIDO_LOCAL_DATE_TIME, PEDIDO_LOCAL_DATE_TIME, 1,
            BigDecimal.valueOf(48), 2L, 2L, LISTA_DETALHES_PEDIDO, USUARIO);

}
