package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;

import java.math.BigDecimal;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoEntityConstants.*;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.LocalDateTimeConstants.PEDIDO_LOCAL_DATE_TIME;

public class PedidoEntityConstants {

    public static final PedidoEntity PEDIDO_ENTITY = new PedidoEntity(null, PEDIDO_LOCAL_DATE_TIME,
            PEDIDO_LOCAL_DATE_TIME, 1, BigDecimal.valueOf(48), LISTA_DETALHES_PEDIDO_ENTITY);

    public static final PedidoEntity PEDIDO_ENTITY_MAPEADO = new PedidoEntity(null, null, null, 0, null,
            LISTA_DETALHES_PEDIDO_ENTITY_MAPEADO);

    public static final PedidoEntity PEDIDO_ENTITY_ATUALIZADO = new PedidoEntity(null, PEDIDO_LOCAL_DATE_TIME,
            PEDIDO_LOCAL_DATE_TIME, 1, BigDecimal.valueOf(150), LISTA_DETALHES_PEDIDO_ENTITY_ATUALIZADO);

    public static final PedidoEntity PEDIDO_ENTITY_VAZIO = new PedidoEntity();

    public static final PedidoEntity PEDIDO_ENTITY_INVALIDO = new PedidoEntity(null, null, null, 0, null,
            LISTA_DETALHES_PEDIDO_ENTITY_VAZIO);

}
