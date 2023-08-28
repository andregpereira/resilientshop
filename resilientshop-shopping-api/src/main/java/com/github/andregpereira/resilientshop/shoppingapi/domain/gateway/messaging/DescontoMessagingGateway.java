package com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.messaging;

import com.github.andregpereira.resilientshop.shoppingapi.domain.model.Pedido;

public interface DescontoMessagingGateway {

    void send(Pedido pedido);

}
