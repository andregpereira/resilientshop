package com.github.andregpereira.resilientshop.shoppingapi.domain.usecase;

import com.github.andregpereira.resilientshop.shoppingapi.domain.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoFindAllByStatusUc {

    Page<Pedido> findAllByStatus(Integer status, Pageable pageable);

}
