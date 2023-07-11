package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;

import java.math.BigDecimal;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoEntityConstants.*;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.LocalDateTimeConstants.PEDIDO_LOCAL_DATE_TIME;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.UsuarioConstants.USUARIO;

public class PedidoEntityConstants {

    public static final PedidoEntity PEDIDO_ENTITY = new PedidoEntity(null, PEDIDO_LOCAL_DATE_TIME,
            PEDIDO_LOCAL_DATE_TIME, 1, BigDecimal.valueOf(48), 1L, 1L, LISTA_DETALHES_PEDIDO_ENTITY, USUARIO);

    public static final PedidoEntity PEDIDO_ENTITY_MAPEADO = new PedidoEntity(null, null, null, 0, null, 1L, 1L,
            LISTA_DETALHES_PEDIDO_ENTITY_MAPEADO, null);

    public static final PedidoEntity PEDIDO_ENTITY_ATUALIZADO = new PedidoEntity(null, PEDIDO_LOCAL_DATE_TIME,
            PEDIDO_LOCAL_DATE_TIME, 1, BigDecimal.valueOf(150), 2L, 2L, LISTA_DETALHES_PEDIDO_ENTITY_ATUALIZADO,
            USUARIO);

    public static final PedidoEntity PEDIDO_ENTITY_VAZIO = new PedidoEntity();

    public static final PedidoEntity PEDIDO_ENTITY_INVALIDO = new PedidoEntity(null, null, null, 0, null, null, null,
            LISTA_DETALHES_PEDIDO_ENTITY_VAZIO, null);

}
