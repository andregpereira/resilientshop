package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoRegistrarDto;

import java.math.BigDecimal;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoDtoConstants.LISTA_DETALHE_PEDIDO_DTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoDtoConstants.LISTA_DETALHE_PEDIDO_REGISTRAR_DTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.LOCAL_DATE_TIME;

public class PedidoDtoConstants {

    public static final PedidoRegistrarDto PEDIDO_REGISTRAR_DTO = new PedidoRegistrarDto(
            LISTA_DETALHE_PEDIDO_REGISTRAR_DTO);
    public static final PedidoRegistrarDto PEDIDO_REGISTRAR_DTO_INVALIDO = new PedidoRegistrarDto(null);

    public static final PedidoDetalharDto PEDIDO_DETALHAR_DTO = new PedidoDetalharDto(null, LOCAL_DATE_TIME,
            LOCAL_DATE_TIME, 1, BigDecimal.valueOf(48), LISTA_DETALHE_PEDIDO_DTO);

}
