package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoRegistrarDto;

public interface PedidoManutencaoService {

    PedidoDetalharDto criar(PedidoRegistrarDto dto);

    String cancelar(Long id);

}
