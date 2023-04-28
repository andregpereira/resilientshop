package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.DetalhePedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.LocalDateTimeConstants.PRODUTO_LOCAL_DATE_TIME;

public class DetalhePedidoConstants {

    public static final DetalhePedido DETALHE_PEDIDO = new DetalhePedido(null, 4, BigDecimal.valueOf(48), 1L,
            new Produto(null, 123456789L, "nome", "Teste da classe Produto", PRODUTO_LOCAL_DATE_TIME,
                    BigDecimal.valueOf(12), 10));

    public static final DetalhePedido DETALHE_PEDIDO_ATUALIZADO = new DetalhePedido(null, 4, BigDecimal.valueOf(48), 1L,
            new Produto(null, 123456789L, "nome", "Teste da classe Produto", PRODUTO_LOCAL_DATE_TIME,
                    BigDecimal.valueOf(30), 10));

    public static final List<DetalhePedido> LISTA_DETALHES_PEDIDO = new ArrayList<>() {
        {
            add(DETALHE_PEDIDO);
        }
    };

}
