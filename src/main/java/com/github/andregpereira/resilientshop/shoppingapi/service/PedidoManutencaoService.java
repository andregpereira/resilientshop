package com.github.andregpereira.resilientshop.shoppingapi.service;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.PedidoRegistrarDto;

public interface PedidoManutencaoService {

    PedidoDto criar(PedidoRegistrarDto dto);

    String cancelar(Long id);

}
