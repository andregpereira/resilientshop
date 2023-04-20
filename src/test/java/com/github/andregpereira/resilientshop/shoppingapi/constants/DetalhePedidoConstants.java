package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.DetalhePedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoConstants.PRODUTO;

public class DetalhePedidoConstants {

    public static final DetalhePedido DETALHE_PEDIDO = new DetalhePedido(null, 4, BigDecimal.valueOf(48), 1L, PRODUTO);

    public static final List<DetalhePedido> DETALHES_PEDIDO = new ArrayList<>() {
        {
            add(DETALHE_PEDIDO);
        }
    };

}
