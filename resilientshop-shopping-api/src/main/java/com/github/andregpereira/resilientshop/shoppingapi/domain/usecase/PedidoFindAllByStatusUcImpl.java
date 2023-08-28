package com.github.andregpereira.resilientshop.shoppingapi.domain.usecase;

import com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.PedidoGateway;
import com.github.andregpereira.resilientshop.shoppingapi.domain.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class PedidoFindAllByStatusUcImpl implements PedidoFindAllByStatusUc {

    private final PedidoGateway gateway;

    @Override
    public Page<Pedido> findAllByStatus(Integer status, Pageable pageable) {
        return gateway.findAllByStatus(status, pageable);
    }

}
