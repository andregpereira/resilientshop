package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.DetalhePedidoEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.PEDIDO_ENTITY;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.PEDIDO_ENTITY_ATUALIZADO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoConstants.PRODUTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoConstants.PRODUTO_ATUALIZADO;

public class DetalhePedidoEntityConstants {

    public static final DetalhePedidoEntity DETALHE_PEDIDO_ENTITY = new DetalhePedidoEntity(null, 4,
            BigDecimal.valueOf(48), 1L, PEDIDO_ENTITY, PRODUTO);

    public static final DetalhePedidoEntity DETALHE_PEDIDO_ENTITY_MAPEADO = new DetalhePedidoEntity(null, 4, null, 1L,
            null, null);

    public static final DetalhePedidoEntity DETALHE_PEDIDO_ENTITY_ATUALIZADO = new DetalhePedidoEntity(null, 5,
            BigDecimal.valueOf(150), 2L, PEDIDO_ENTITY_ATUALIZADO, PRODUTO_ATUALIZADO);

    public static final DetalhePedidoEntity DETALHE_PEDIDO_ENTITY_VAZIO = new DetalhePedidoEntity();

    public static final DetalhePedidoEntity DETALHE_PEDIDO_ENTITY_INVALIDO = new DetalhePedidoEntity(null, 0, null,
            null, null, null);

    public static final List<DetalhePedidoEntity> LISTA_DETALHES_PEDIDO_ENTITY = new ArrayList<>() {
        {
            add(DETALHE_PEDIDO_ENTITY);
        }
    };
    public static final List<DetalhePedidoEntity> LISTA_DETALHES_PEDIDO_ENTITY_MAPEADO = new ArrayList<>() {
        {
            add(DETALHE_PEDIDO_ENTITY_MAPEADO);
        }
    };

    public static final List<DetalhePedidoEntity> LISTA_DETALHES_PEDIDO_ENTITY_ATUALIZADO = new ArrayList<>() {
        {
            add(DETALHE_PEDIDO_ENTITY_ATUALIZADO);
        }
    };

    public static final List<DetalhePedidoEntity> LISTA_DETALHES_PEDIDO_ENTITY_VAZIO = new ArrayList<>() {
        {
            add(DETALHE_PEDIDO_ENTITY_VAZIO);
        }
    };

}
