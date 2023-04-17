package com.github.andregpereira.resilientshop.shoppingapi.app.service;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoRegistrarDto;

public interface PedidoManutencaoService {

    PedidoDto criar(PedidoRegistrarDto dto);

    String cancelar(Long id);

}
