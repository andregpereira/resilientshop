package com.github.andregpereira.resilientshop.shoppingapi.domain.usecase;

import com.github.andregpereira.resilientshop.shoppingapi.domain.model.Pedido;

public interface PedidoFindByIdUc {

    Pedido findById(Long id);

}
