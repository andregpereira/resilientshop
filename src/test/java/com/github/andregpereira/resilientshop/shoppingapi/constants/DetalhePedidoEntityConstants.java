package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.DetalhePedidoEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.PEDIDO_ENTITY;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoConstants.PRODUTO;

public class DetalhePedidoEntityConstants {

    public static final DetalhePedidoEntity DETALHE_PEDIDO_ENTITY = new DetalhePedidoEntity(null, 4,
            BigDecimal.valueOf(48), 1L, PEDIDO_ENTITY, PRODUTO);

    public static final DetalhePedidoEntity DETALHE_PEDIDO_ENTITY_VAZIO = new DetalhePedidoEntity();

    public static final List<DetalhePedidoEntity> DETALHES_PEDIDO_ENTITY = new ArrayList<>() {
        {
            add(DETALHE_PEDIDO_ENTITY);
        }
    };
    public static final List<DetalhePedidoEntity> DETALHES_PEDIDO_ENTITY_VAZIO = new ArrayList<>() {
        {
            add(DETALHE_PEDIDO_ENTITY_VAZIO);
        }
    };

}
