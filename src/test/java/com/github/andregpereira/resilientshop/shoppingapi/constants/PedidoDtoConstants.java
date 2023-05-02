package com.github.andregpereira.resilientshop.shoppingapi.constants;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoRegistrarDto;

import java.math.BigDecimal;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoDtoConstants.LISTA_DETALHES_PEDIDO_DTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoDtoConstants.LISTA_DETALHES_PEDIDO_REGISTRAR_DTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.LocalDateTimeConstants.PEDIDO_LOCAL_DATE_TIME;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.UsuarioDtoConstants.USUARIO_DTO;

public class PedidoDtoConstants {

    public static final PedidoDto PEDIDO_DTO = new PedidoDto(null, PEDIDO_LOCAL_DATE_TIME, PEDIDO_LOCAL_DATE_TIME, 1,
            BigDecimal.valueOf(48));

    public static final PedidoDto PEDIDO_DTO_ATUALIZADO = new PedidoDto(null, PEDIDO_LOCAL_DATE_TIME,
            PEDIDO_LOCAL_DATE_TIME, 1, BigDecimal.valueOf(150));

    public static final PedidoDetalharDto PEDIDO_DETALHAR_DTO = new PedidoDetalharDto(null, PEDIDO_LOCAL_DATE_TIME,
            PEDIDO_LOCAL_DATE_TIME, 1, BigDecimal.valueOf(48), USUARIO_DTO, LISTA_DETALHES_PEDIDO_DTO);

    public static final PedidoRegistrarDto PEDIDO_REGISTRAR_DTO = new PedidoRegistrarDto(1L,
            LISTA_DETALHES_PEDIDO_REGISTRAR_DTO);
    public static final PedidoRegistrarDto PEDIDO_REGISTRAR_DTO_INVALIDO = new PedidoRegistrarDto(null, null);

}
