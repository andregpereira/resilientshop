package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoRegistrarDto;

public interface PedidoManutencaoService {

    PedidoDetalharDto criar(PedidoRegistrarDto dto);

    void cancelar(Long id);

}
