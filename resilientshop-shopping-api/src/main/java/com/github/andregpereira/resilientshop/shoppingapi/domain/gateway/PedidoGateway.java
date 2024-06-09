package com.github.andregpereira.resilientshop.shoppingapi.domain.gateway;

import com.github.andregpereira.resilientshop.shoppingapi.domain.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public interface PedidoGateway {

    Pedido save(Pedido pedido);

    Pedido findById(Long id, UnaryOperator<Pedido> op);

    void findByIdAndStatusAguardandoPagamento(Long id, Consumer<Pedido> op);

    Page<Pedido> findAllByIdUsuario(Long id, Pageable pageable);

    Page<Pedido> findAllByStatus(Integer status, Pageable pageable);

}
