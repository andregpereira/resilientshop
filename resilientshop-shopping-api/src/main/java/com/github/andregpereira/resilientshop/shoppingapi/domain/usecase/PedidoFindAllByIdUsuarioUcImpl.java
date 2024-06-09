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
public class PedidoFindAllByIdUsuarioUcImpl implements PedidoFindAllByIdUsuarioUc {

    private final PedidoGateway gateway;

    @Override
    public Page<Pedido> findAllByIdUsuario(Long id, Pageable pageable) {
        return gateway.findAllByIdUsuario(id, pageable);
    }

}
