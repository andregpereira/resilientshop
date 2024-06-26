package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.detalhepedido.DetalhePedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.detalhepedido.DetalhePedidoRegistrarDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoDtoConstants.PRODUTO_DTO;

public class DetalhePedidoDtoConstants {

    public static final DetalhePedidoDto DETALHE_PEDIDO_DTO = new DetalhePedidoDto(null, 4, BigDecimal.valueOf(48),
            PRODUTO_DTO);

    public static final DetalhePedidoRegistrarDto DETALHE_PEDIDO_REGISTRAR_DTO = new DetalhePedidoRegistrarDto(1L, 4);

    public static final List<DetalhePedidoRegistrarDto> LISTA_DETALHES_PEDIDO_REGISTRAR_DTO = new ArrayList<>() {
        {
            add(DETALHE_PEDIDO_REGISTRAR_DTO);
        }
    };
    public static final List<DetalhePedidoRegistrarDto> LISTA_DETALHES_PEDIDO_REGISTRAR_DTO_INVALIDO = new ArrayList<>() {
        {
            add(null);
        }
    };

    public static final List<DetalhePedidoDto> LISTA_DETALHES_PEDIDO_DTO = new ArrayList<>() {
        {
            add(DETALHE_PEDIDO_DTO);
        }
    };

}
