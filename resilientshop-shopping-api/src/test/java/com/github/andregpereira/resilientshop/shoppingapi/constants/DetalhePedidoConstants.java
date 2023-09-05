package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.infra.entity.DetalhePedidoEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetalhePedidoConstants {

    public static final DetalhePedidoEntity DETALHE_PEDIDO = new DetalhePedidoEntity(null, 4, BigDecimal.valueOf(48), 1L,
            new Produto(null, "nome", BigDecimal.valueOf(12), 10));

    public static final DetalhePedidoEntity DETALHE_PEDIDO_ATUALIZADO = new DetalhePedidoEntity(null, 4, BigDecimal.valueOf(48), 1L,
            new Produto(null, "nome", BigDecimal.valueOf(30), 10));

    public static final List<DetalhePedidoEntity> LISTA_DETALHES_PEDIDO = new ArrayList<>() {
        {
            add(DETALHE_PEDIDO);
        }
    };

}
